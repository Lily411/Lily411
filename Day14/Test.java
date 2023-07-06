package Day14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day14\\Day14_input.txt";
        Scanner input = new Scanner(new FileInputStream(path));

        LinkedHashSet<String> block = new LinkedHashSet<>();
        LinkedHashSet<String> block1 = new LinkedHashSet<>();
        int abyss = 0;
        while(input.hasNextLine()){
            String str = input.nextLine();
            String[] all = str.split("\\->");
            for (int i = 0; i < all.length-1 ; i++){
                String[] str1 = all[i].split("\\,");
                int x1 = Integer.parseInt(str1[0].trim());
                int y1 = Integer.parseInt(str1[1].trim());
                String[] str2 = all[i+1].split("\\,");
                int x2 = Integer.parseInt(str2[0].trim());
                int y2 = Integer.parseInt(str2[1].trim());

                int maxX = x1 > x2 ? x1:x2;
                int maxY = y1 > y2 ? y1:y2;

                for (int x = x1 > x2 ? x2:x1; x < maxX+1 ; x++ ){
                    for(int y = y1 > y2 ? y2:y1; y < maxY+1 ;y++){
                        String c = x + "," +y;
                        block.add(c);
                        block1.add(c);
                        abyss = abyss > y+1? abyss:y+1;
                    }
                }
            }
        }
        int t = 0;

        //Part 1
        boolean flag = true;
        while (flag){
            int s = 500;
            int y = 0;
            while(true){
                if (y>=abyss){
                    System.out.println("Part 1. How many units of sand come to rest before sand starts flowing into the abyss below?");
                    System.out.println("ANS: " + t);
                    System.out.println();
                    flag = false;
                    break;
                }
                if(!block.contains(s+","+(y+1))){
                    //System.out.println("1");
                    y++;
                    continue;
                }
                if(!block.contains((s-1)+","+(y+1))){
                    //System.out.println("2");
                    s--;
                    continue;
                }
                if(!block.contains((s+1)+","+(y+1))){
                    //System.out.println("3");
                    s++;
                    continue;
                }
                block.add(s+","+y);
                t++;
                break;
            }
        }

        //Part 2
        int t1 = 0;
        while (!block1.contains(500+","+0)){
            int s = 500;
            int y = 0;
            while(true){
                if (y>=abyss){
                    break;
                }
                if(!block1.contains(s+","+(y+1))){
                    //System.out.println("1");
                    y++;
                    continue;
                }
                if(!block1.contains((s-1)+","+(y+1))){
                    //System.out.println("2");
                    s--;
                    continue;
                }
                if(!block1.contains((s+1)+","+(y+1))){
                    //System.out.println("3");
                    s++;
                    continue;
                }
                break;
            }
            block1.add(s+","+y);
            t1++;
        }
        System.out.println("Part 2. simulate the falling sand until the source of the sand becomes blocked. How many units of sand come to rest?");
        System.out.println("ANS: " + t1);
    }
}
