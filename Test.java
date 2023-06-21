package Day03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day03\\Day03_input.txt";

        //Part 1
        Scanner input1 = new Scanner(new FileInputStream(path));
        int sum1 = 0;
        while(input1.hasNextLine()){
            String str = input1.nextLine();
            String str1 = str.substring(0,str.length()/2);
            String str2 = str.substring(str.length()/2, str.length());
            char[] toCharStr2 = str2.toCharArray();

            for(int i = 0; i < toCharStr2.length;i++){
                if (str1.contains(toCharStr2[i]+"")) {
                    int n = toCharStr2[i];
                    if(n>=65 && n <=90){
                        sum1 += (n-38);
                    }
                    else{
                        sum1 += (n-96);
                    }
                    break;
                }
            }
        }
        System.out.println(sum1);

        //Part 2
        Scanner input2 = new Scanner(new FileInputStream(path));
        int sum2 = 0;
        while(input2.hasNextLine()){
            String str1 = input2.nextLine();
            String str2 = input2.nextLine();
            String str3 = input2.nextLine();
            char[] toCharStr2 = str2.toCharArray();

            for(int i = 0; i < toCharStr2.length;i++){
                if (str1.contains(toCharStr2[i]+"")) {
                    if(str3.contains(toCharStr2[i]+"")){
                        int n = toCharStr2[i];
                        if(n>=65 && n <=90){
                            sum2 += (n-38);
                        }
                        else{
                            sum2 += (n-96);
                        }
                        break;
                    }
                }
            }
        }
        System.out.println(sum2);
    }
}
