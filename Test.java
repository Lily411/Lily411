package Day02;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day02\\Day02_input.txt";
        Scanner input = new Scanner(new FileInputStream(path));
        char opponent;
        char you;
        int score = 0;
        int count=0;
        while(input.hasNextLine()){
            opponent = input.next().charAt(0);
            input.hasNextLine();
            you = input.next().charAt(0);
            switch (opponent){
                case 'A':
                    switch(you){
                        case 'X':
                            score += 4;
                            break;
                        case 'Y':
                            score += 8;
                            break;
                        case 'Z':
                            score += 3;
                            break;
                    }
                    break;
                case 'B':
                    switch(you){
                        case 'X':
                            score += 1;
                            break;
                        case 'Y':
                            score += 5;
                            break;
                        case 'Z':
                            score += 9;
                            break;
                    }
                    break;
                case 'C':
                    switch(you){
                        case 'X':
                            score += 7;
                            break;
                        case 'Y':
                            score += 2;
                            break;
                        case 'Z':
                            score += 6;
                            break;
                    }
                    break;
            }
        }
        System.out.println("final score = " + score);

        //Part 2
        Scanner input2 = new Scanner(new FileInputStream(path));
        char opponent2;
        char you2;
        int score2 = 0;
        while(input2.hasNextLine()){
            opponent2 = input2.next().charAt(0);
            input2.hasNextLine();
            you2 = input2.next().charAt(0);
            switch (opponent2){
                case 'A':
                    switch(you2){
                        case 'X':
                            score2 += 3;
                            break;
                        case 'Y':
                            score2 += 4;
                            break;
                        case 'Z':
                            score2 += 8;
                            break;
                    }
                    break;
                case 'B':
                    switch(you2){
                        case 'X':
                            score2 += 1;
                            break;
                        case 'Y':
                            score2 += 5;
                            break;
                        case 'Z':
                            score2 += 9;
                            break;
                    }
                    break;
                case 'C':
                    switch(you2){
                        case 'X':
                            score2 += 2;
                            break;
                        case 'Y':
                            score2 += 6;
                            break;
                        case 'Z':
                            score2 += 7;
                            break;
                    }
                    break;
            }
        }
        System.out.println("final score = " + score2);
    }
}
