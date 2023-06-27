package Day10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day10\\Day10_input.txt";

        //Part 1.
        Scanner input = new Scanner(new FileInputStream(path));

        LinkedList<Integer>list = new LinkedList<>();
        int round = 20;
        int count = 1;
        int x = 1;
        while(input.hasNextLine()){
            String str = input.nextLine();
            String[] all = str.split("\\s+");

            if(count == round && round <= 220){
                int signal = round * x;
                list.add(signal);
                round += 40;
            }
            if(all[0].equals("noop")){
                count ++;
            }
            if(all[0].equals("addx")){
                count++ ;
                if(count == round  && round <= 220){
                    int signal = round * x;
                    list.add(signal);
                    round += 40;
                }
                int v = Integer.parseInt(all[1]);
                x += v;
                count++;
            }
        }
        int sum = 0;
        for (Integer l:list) {
            sum += l;
        }
        System.out.println("Part 1. \n"+
                           "Find the signal strength during the 20th, 60th, 100th, 140th, 180th, and 220th cycles. \n" +
                           "What is the sum of these six signal strengths? \n" +
                           "ANS: " + sum);

        //Part 2
        String[][] map = new String[6][40];
        String[] sprite = new String[40];
        for(int i = 0; i < sprite.length ; i++){
            sprite[i] = " ";
        }
        sprite[0] = "#";
        sprite[1] = "#";
        sprite[2] = "#";

        int cycle = 0;
        int round1 = 40;
        int row = 0;
        int col = 0;
        x = 0;

        Scanner input1 = new Scanner(new FileInputStream(path));
        while (input1.hasNextLine()){
            String str = input1.nextLine();
            String[] all = str.split("\\s+");
            if(cycle == 240){
                break;
            }

            if(cycle == round1 && round1 <= 239){
                round1 += 40;
                row = 0;
                col ++;
            }
            if(all[0].equals("noop")){
                map[col][row] = sprite[row];
                row ++;
                cycle ++;
            }
            if(all[0].equals("addx")){
                cycle++ ;
                map[col][row] = sprite[row];
                row++;
                if(cycle == round1 && round1 <= 239){
                    round1 += 40;
                    row = 0;
                    col ++;
                }
                map[col][row] = sprite[row];
                row++;
                int v = Integer.parseInt(all[1]);
                x += v;
                for(int i = 0; i < sprite.length ; i++){
                    sprite[i] = " ";
                }
                if(x<0){
                    if(x==-1){
                        sprite[x+1] = "#";
                        sprite[x+2] = "#";
                    }
                    if(x==-2){
                        sprite[x+2] = "#";
                    }
                }
                else{
                    if(x<=37){
                        sprite[x] = "#";
                        sprite[x+1] = "#";
                        sprite[x+2] = "#";
                    }
                    if(x==38){
                        sprite[x] = "#";
                        sprite[x+1] = "#";
                    }
                    if(x==39){
                        sprite[x] = "#";
                    }
                }
                cycle++;
            }
        }

        System.out.println();
        System.out.println("Part 2. What eight capital letters appear on your CRT?" );
        for(int i = 0;i<map.length;i++){
            for(int j = 0; j < map[i].length;j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
