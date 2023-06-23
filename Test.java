package Day06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day06\\Day06_input.txt";

        //Part 1
        Scanner input = new Scanner(new FileInputStream(path));
        String str = input.nextLine();
        int count = 0;

        while(count < str.length()){
            boolean flag = true;

            String fourLetter = str.substring(count,count+4);
            char[] fourLetterChar = fourLetter.toCharArray();
            for (Character c:fourLetterChar) {
                if(fourLetter.indexOf(c+"") != fourLetter.lastIndexOf(c+"")){
                    flag = false;
                    break;
                }
            }

            if(flag == false){
                count +=1;
            }
            else{
                System.out.println(fourLetter);
                break;
            }
        }
        System.out.println("Part 1. Number of character is " + (count+4) );

        //Part 2
        Scanner input1 = new Scanner(new FileInputStream(path));
        String str1 = input1.nextLine();
        int count1 = 0;

        while(count1 < str1.length()){
            boolean flag = true;

            String fourtheenLetter = str1.substring(count1,count1+14);
            char[] fourtheenLetterChar = fourtheenLetter.toCharArray();
            for (Character c:fourtheenLetterChar) {
                if(fourtheenLetter.indexOf(c+"") != fourtheenLetter.lastIndexOf(c+"")){
                    flag = false;
                    break;
                }
            }

            if(flag == false){
                count1 +=1;
            }
            else{
                System.out.println(fourtheenLetter);
                break;
            }
        }
        System.out.println("Part 2. Number of character is " + (count1+14) );
    }
}
