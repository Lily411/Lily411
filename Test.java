package Day05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day05\\Day05_input.txt";
        Scanner input = new Scanner(new FileInputStream(path));
        Scanner input1 = new Scanner(new FileInputStream(path));//Part 1
        Scanner input2 = new Scanner(new FileInputStream(path));//Part 2

        //find the total of stacks
        int totalOfStacks = 0;
        NewStack[] stacks = new NewStack[totalOfStacks];//Part 1
        NewStack[] stacks2 = new NewStack[totalOfStacks];//Part 2
        while (input.hasNextLine()){
            String findNum = input.nextLine();
            if(findNum.contains("1")){
                String[] all = findNum.split("\\s+");
                totalOfStacks = all.length-1;

                //Part 1
                stacks = new NewStack[totalOfStacks];
                for(int i = 0 ; i < totalOfStacks ; i++){
                    stacks[i] = new NewStack();
                }

                //Part 2
                stacks2 = new NewStack[totalOfStacks];
                for(int i = 0 ; i < totalOfStacks ; i++){
                    stacks2[i] = new NewStack();
                }
                break;
            }
        }

        //Part 1
        //add the initialization crates in different stacks
        String len;
        while(input1.hasNextLine()){
            if(!(len=input1.nextLine()).equals("")){
                if(len.contains("1")){
                   break;
                }
                String str = len;
                char[] c = str.toCharArray();
                for(int i = 0,j=0 ; i < c.length ; i=i+4,j++){
                    stacks[j].push(c[i+1]);
                }
            }
        }
        for (NewStack s:stacks){
            s.reverse();
        }

        // Part 1
        // move the crates
       while(input1.hasNextLine()){
            if(!(len=input1.nextLine()).equals("")) {
                String str = len.replaceAll("\\D",",");
                String[] all =str.split("\\,+");
                int quantityOfCrate = Integer.parseInt(all[1]);
                int outStack = Integer.parseInt(all[2])-1;
                int inStack = Integer.parseInt(all[3])-1;
                for(int i = 0 ; i < quantityOfCrate ; i++){
                    if(stacks[outStack].size()!=0){
                        char crateOfName = stacks[outStack].peek();
                        stacks[outStack].pop();
                        stacks[inStack].push(crateOfName);
                    }
                    else{
                        break;
                    }
                }
            }
        }
        System.out.print("Part 1. ");
        for (NewStack s:stacks){
            System.out.print(s.peek() +" ");
        }

        //Part 2
        //add the initialization crates in different stacks
        String len1;
        while(input2.hasNextLine()){
            if(!(len1=input2.nextLine()).equals("")){
                if(len1.contains("1")){
                    break;
                }
                String str = len1;
                char[] c = str.toCharArray();
                for(int i = 0,j=0 ; i < c.length ; i=i+4,j++){
                    stacks2[j].push(c[i+1]);
                }
            }
        }
        for (NewStack s:stacks2){
            s.reverse();
        }

        //Part 2
        // move the crates
        while(input2.hasNextLine()){
            if(!(len1=input2.nextLine()).equals("")) {
                String str = len1.replaceAll("\\D",",");
                String[] all =str.split("\\,+");
                int quantityOfCrate = Integer.parseInt(all[1]);
                int outStack = Integer.parseInt(all[2])-1;
                int inStack = Integer.parseInt(all[3])-1;

                int sizeOfCrates = stacks2[outStack].size()>quantityOfCrate? quantityOfCrate:stacks2[outStack].size();
                char[] crates = new char[sizeOfCrates];
                for(int i = 0 ; i < sizeOfCrates ; i++){
                    if(stacks2[outStack].size()!=0){
                        crates[i] = stacks2[outStack].peek();
                        stacks2[outStack].pop();
                    }
                    else{
                        break;
                    }
                }
                for (int i = crates.length-1 ; i >=0 ; i--){
                    stacks2[inStack].push(crates[i]);

                }
            }
        }
        System.out.println();
        System.out.print("Part 2. ");
        for (NewStack s:stacks2){
            System.out.print(s.peek() +" ");
        }

    }
}
