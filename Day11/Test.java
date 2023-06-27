package Day11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day11\\Day11_input.txt";

        //find total of monkey
        Scanner input = new Scanner(new FileInputStream(path));
        int findTotalMonkey = 0;
        while(input.hasNext()){
            String str = input.nextLine();
            String[] all = str.split("\\s+");
            if(all[0].equals("Monkey")){
                findTotalMonkey++;
            }
        }
        Monkey[] monkeys = new Monkey[findTotalMonkey];

        //read the input file
        Scanner input1= new Scanner(new FileInputStream(path));
        int countMonkey = 0;
        String len;
        while(input1.hasNextLine()){
           if(!(len = input1.nextLine()).equals("")){
               String str = input1.nextLine();
               str = str.replaceAll("\\D",",");
               String[] firstRow = str.split("\\,+");

               str = input1.nextLine();
               String[] secondRow = str.split("\\s+");
               String operator = secondRow[5];
               int number = 0;
               if(secondRow[6].equals("old")){
                    number = -1;
               }
               else{
                    number = Integer.parseInt(secondRow[6]);

               }

               str = input1.nextLine();
               String[] thirdRow = str.split("\\s+");
               int divisible = Integer.parseInt(thirdRow[4]);

               str = input1.nextLine();
               str = str.replaceAll("\\D",",");
               String[] fourthRow = str.split("\\,+");
               int ifTrue = Integer.parseInt(fourthRow[1]);

               str = input1.nextLine();
               str = str.replaceAll("\\D",",");
               String[] fifthRow = str.split("\\,+");
               int ifFalse = Integer.parseInt(fifthRow[1]);

               monkeys[countMonkey] = new Monkey(operator, number,divisible,ifTrue, ifFalse);
               for(int i = 1 ; i < firstRow.length ; i++){
                   monkeys[countMonkey].addLast(Integer.parseInt(firstRow[i]));
               }
               countMonkey ++;
           }
        }

        //Part 1
        int[] countInspect = new int[findTotalMonkey];
        for(int i = 0 ; i < countInspect.length ; i++){
            countInspect[i] = 0;
        }

        for(int i = 0 ; i < 20 ; i++){
            for (int j = 0 ; j < monkeys.length ; j++){
                countInspect[j] += monkeys[j].size();
                while(monkeys[j].size()> 0){
                    long worryLevel = monkeys[j].worrylevelPart1(monkeys[j].getFirst());
                    int thrownTo = monkeys[j].thrownTo(worryLevel);
                    monkeys[thrownTo].addLast(worryLevel);
                    monkeys[j].removeFirst();
                }
            }
        }

        Arrays.sort(countInspect);
        System.out.println("Part 1.What is the level of monkey business after 20 rounds of stuff-slinging simian shenanigans? ");
        System.out.println("ANS: " + (countInspect[countInspect.length-2] * countInspect[countInspect.length-1]));
        System.out.println();

        
        //Part 2.
        for(int i = 0 ; i < countInspect.length ; i++){
            countInspect[i] = 0;
        }

        int lcm = 1;
        for(int i = 0; i < monkeys.length ; i ++){
            lcm *= monkeys[i].getDivisible();
        }
        for(int i = 0 ; i < 10000 ; i++){
            for (int j = 0 ; j < monkeys.length ; j++){
                countInspect[j] += monkeys[j].size();
                while(monkeys[j].size()> 0){
                    long worryLevel = monkeys[j].worrylevelPart2(monkeys[j].getFirst(),lcm);
                    int thrownTo = monkeys[j].thrownTo(worryLevel);
                    monkeys[thrownTo].addLast(worryLevel);
                    monkeys[j].removeFirst();
                }
            }
        }

        Arrays.sort(countInspect);
        System.out.println("Part 2. what is the level of monkey business after 10000 rounds? \nIt is because the answer is too large. Please multiple by yourself:\n"+
                            countInspect[countInspect.length-2] + " * " + countInspect[countInspect.length-1]);
    }
}
