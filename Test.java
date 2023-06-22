package Day04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day04\\Day04_input.txt";

        Scanner input1 = new Scanner(new FileInputStream(path));
        int count1 = 0;
        int count2 = 0;
        while(input1.hasNextLine()){
            String str = input1.nextLine();

            String[] all = str.split("\\,");
            element[] elements = new element[all.length];
            for(int i = 0 ; i < elements.length; i++){
                String[] strings = all[i].split("\\-");
                int first = Integer.parseInt(strings[0]);
                int last = Integer.parseInt(strings[1]);
                elements[i] = new element(first,last);
            }

            //Part 1
            if(elements[0].compareTo(elements[1])==1 || elements[1].compareTo(elements[0])== 1){
                count1 +=1;
            }

            //Part 2
            if(elements[0].overLap(elements[1]) == 1){
                count2 += 1;
            }
        }
        System.out.println("Part 1. Fully contain: " + count1);
        System.out.println("Part 2. Overlap: " + count2);
    }
}
