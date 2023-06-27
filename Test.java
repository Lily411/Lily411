package Day09;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day09\\Day09_input.txt";
        Scanner input = new Scanner(new FileInputStream(path));

        int[] positionH = new int[2];
        positionH[0] = 0;
        positionH[1] = 0;
        int[] positionT = new int[2];
        positionT[0] = 0;
        positionT[1] = 0;
        int[] positionS = new int[2];
        positionS[0] = 0;
        positionS[1] = 0;

        //Part 1
        HashSet<String> set = new HashSet<>();
        String intToStr = positionS[0] + "," +positionS[1];
        set.add(intToStr);
        while (input.hasNextLine()) {
            String str = input.nextLine();
            String[] all = str.split("\\s+");
            int moveStep = Integer.parseInt(all[1]);
            String moveDirection = all[0];

            for (int i = 0; i < moveStep; i++) {
                switch (moveDirection) {
                    case "R":
                        positionH[1]++;
                        break;
                    case "L":
                        positionH[1]--;
                        break;
                    case "U":
                        positionH[0]--;
                        break;
                    case "D":
                        positionH[0]++;
                        break;
                }
                int diffX = positionH[0] - positionT[0];
                int diffY = positionH[1] - positionT[1];

                if(Math.abs(diffX) > 1 || Math.abs(diffY) > 1){
                    if (diffX == 0){
                        positionT[1] += diffY/2;
                    }
                    else if(diffY == 0){
                        positionT[0] +=diffX/2;
                    }
                    else{
                        if(diffX>0){
                            positionT[0] +=1;
                        }
                        if (diffX < 0){
                            positionT[0] += -1;
                        }
                        if(diffY > 0){
                            positionT[1] += 1;
                        }
                        if(diffY < 0){
                            positionT[1] += -1;
                        }

                    }
                }
                intToStr = positionT[0] + "," +positionT[1];
                set.add(intToStr);
            }
        }
        System.out.println("How many positions does the tail of the rope visit at least once? ANS: "+ set.size());

        //Part 2
        Scanner input1 = new Scanner(new FileInputStream(path));

        int[] positionH1 = new int[2];
        positionH[0] = 0;
        positionH[1] = 0;
        int[][] positionT1 = new int[9][2];
        for(int i = 0 ;i<positionT1.length;i++){
            for (int j = 0 ; j < positionT1[i].length ; j++){
                positionT1[i][j] = 0;
            }
        }
        int[] positionS1 = new int[2];
        positionS1[0] = 0;
        positionS1[1] = 0;

        HashSet<String> set_nine = new HashSet<>();
        String intToStr1 = positionS1[0] + "," +positionS1[1];
        set.add(intToStr1);
        while (input1.hasNextLine()) {
            String str = input1.nextLine();
            String[] all = str.split("\\s+");
            int moveStep = Integer.parseInt(all[1]);
            String moveDirection = all[0];

            for (int i = 0; i < moveStep; i++) {
                switch (moveDirection) {
                    case "R":
                        positionH1[1]++;
                        break;
                    case "L":
                        positionH1[1]--;
                        break;
                    case "U":
                        positionH1[0]--;
                        break;
                    case "D":
                        positionH1[0]++;
                        break;
                }

                int diffX = 0;
                int diffY = 0;
                for(int k = 0 ; k < 9 ; k++){
                    if (k == 0){
                        diffX = positionH1[0] - positionT1[0][0];
                        diffY = positionH1[1] - positionT1[0][1];
                    }
                    if (k > 0){
                        diffX = positionT1[k-1][0] - positionT1[k][0];
                        diffY = positionT1[k-1][1] - positionT1[k][1];
                    }

                    if(Math.abs(diffX) > 1 || Math.abs(diffY) > 1){
                        if (diffX == 0){
                            positionT1[k][1] += diffY/2;
                        }
                        else if(diffY == 0){
                            positionT1[k][0] +=diffX/2;
                        }
                        else{
                            if(diffX>0){
                                positionT1[k][0] +=1;
                            }
                            if (diffX < 0){
                                positionT1[k][0] += -1;
                            }
                            if(diffY > 0){
                                positionT1[k][1] += 1;
                            }
                            if(diffY < 0){
                                positionT1[k][1] += -1;
                            }
                        }
                    }
                }
                intToStr = positionT1[8][0] + "," +positionT1[8][1];
                set_nine.add(intToStr);
            }
        }
        System.out.println("How many positions does the tail of the rope visit at least once? ANS: " + set_nine.size());
    }
}
