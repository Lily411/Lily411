package Day08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day08\\Day08_input.txt";

        Scanner input = new Scanner(new FileInputStream(path));
        String str = input.nextLine();
        int getRowLength = str.length();
        int countColLength = 1;

        //Part 1
        while (input.hasNextLine()){
            String str1 = input.nextLine();
            if(str1.equals("")){
                break;
            }
            countColLength +=1;
        }

        int[][] tree = new int[countColLength][getRowLength];
        int[][] countSee = new int[countColLength][getRowLength];
        int countCol = 0;

        Scanner input1 = new Scanner(new FileInputStream(path));
        while(input1.hasNextLine()){
            String str2 = input1.nextLine();
            char[] c = str2.toCharArray();
            for(int i = 0; i < c.length; i++){
                tree[countCol][i] = Integer.parseInt(String.valueOf(c[i]));
                countSee[countCol][i] = 0;
            }
            countCol ++;
        }

        int[] topMax = new int[getRowLength];
        int[] bottomMax = new int[getRowLength];
        boolean[] tbstop = new boolean[getRowLength];
        for(int i = 0; i < topMax.length ; i ++){
            topMax[i] = tree[0][i];
            bottomMax[i] = tree[topMax.length-1][i];
            tbstop[i]=true;
        }
        int[] leftMax = new int[countColLength];
        int[] rightMax = new int[countColLength];
        boolean[] lrstop = new boolean[countColLength];
        for(int i = 0 ; i < leftMax.length;i++){
            leftMax[i] = tree[i][0];
            rightMax[i] = tree [i][leftMax.length-1];
            lrstop[i] = true;
        }

        int totalSee = 0;
        //top
        for(int i = 0; i < tree.length-2 ; i++){
            for(int j = 1 ; j < tree[i].length-1;j++){
                if(topMax[j] < tree[i+1][j]){
                    if(countSee[i+1][j]==0){
                        countSee[i+1][j] = 1;
                        totalSee ++;
                    }
                    topMax[j] = tree[i+1][j];
                }
            }
        }

        //bottom
        for(int i = tree.length-1; i > 1 ; i--){
            for(int j = tree[i].length-2 ; j >0;j--){
                if(bottomMax[j] < tree[i-1][j]){
                    if(countSee[i-1][j]==0){
                        countSee[i-1][j] = 1;
                        totalSee ++;
                    }
                    bottomMax[j] = tree[i-1][j];
                }
            }
        }

        //left
        for(int i = 1; i < tree[i].length-1 ; i++){
            for(int j = 0 ; j < tree.length-2;j++){
                if(leftMax[i] < tree[i][j+1]){
                    if(countSee[i][j+1]==0){
                        countSee[i][j+1] = 1;
                        totalSee ++;
                    }
                    leftMax[i] = tree[i][j+1];
                }
            }
        }

        //right
        for(int i = tree[0].length-2; i > 0 ; i--){
            for(int j = tree.length-1 ; j >1;j--){
                if(rightMax[i] < tree[i][j-1]){
                    if(countSee[i][j-1]==0){
                        countSee[i][j-1] = 1;
                        totalSee ++;
                    }
                    rightMax[i] = tree[i][j-1];
                }
            }
        }

        totalSee += getRowLength*2 + countColLength*2 -4;
        System.out.println("Part 1. How many trees are visible from outside the grid? ANS: " + totalSee);

        //Part 2
        int maxScore = 0;
        for(int i = 1 ; i < tree.length-1;i++){
            for(int j = 1 ; j < tree[i].length-1 ; j ++){
                int topSee = 0;
                int max = tree[i][j];
                boolean sameHeightTree = true;
                int bottomSee = 0;
                int leftSee = 0;
                int rightSee = 0;
                //top
                for(int top  = i - 1; top >= 0 ; top --){
                    if(max < tree[top][j]){
                        topSee++;
                        max = tree[top][j];
                    }
                    if(tree[i][j] > tree[top][j] && max == tree[i][j]&& sameHeightTree == true){
                        topSee++;
                    }
                    if(tree[i][j] == tree[top][j] && max == tree[i][j] && sameHeightTree == true){
                        topSee++;
                        sameHeightTree = false;
                    }
                }

                //bottom
                max = tree[i][j];
                sameHeightTree = true;
                for(int bottom  = i + 1; bottom < tree.length; bottom ++){
                    if(max < tree[bottom][j]){
                        bottomSee++;
                        max = tree[bottom][j];
                    }
                    if(tree[i][j] > tree[bottom][j] && max == tree[i][j]&& sameHeightTree == true){
                        bottomSee++;
                    }
                    if(tree[i][j] == tree[bottom][j] && max == tree[i][j] && sameHeightTree == true){
                        bottomSee++;
                        sameHeightTree = false;
                    }
                }

                //left
                max = tree[i][j];
                sameHeightTree = true;
                for(int left  = j - 1; left >= 0 ; left --){
                    if(max < tree[i][left]){
                        leftSee++;
                        max = tree[i][left];
                    }
                    if(tree[i][j] > tree[i][left] && max == tree[i][j] && sameHeightTree == true){
                        leftSee++;
                    }
                    if(tree[i][j] == tree[i][left] && max == tree[i][j] && sameHeightTree == true){
                        leftSee++;
                        sameHeightTree = false;
                    }
                }

                //right
                max = tree[i][j];
                sameHeightTree = true;
                for(int right  = j + 1; right < tree[i].length ; right ++){
                    if(max < tree[i][right]){
                        rightSee++;
                        max = tree[i][right];
                    }
                    if(tree[i][j] > tree[i][right] && max == tree[i][j] && sameHeightTree == true){
                        rightSee++;
                    }
                    if(tree[i][j] == tree[i][right] && max == tree[i][j] && sameHeightTree == true){
                        rightSee++;
                        sameHeightTree = false;
                    }
                }

                int sumMax = topSee * bottomSee * leftSee * rightSee;
                if(maxScore < sumMax){
                    maxScore = sumMax;
                }
            }
        }
        System.out.println("Part 2. What is the highest scenic score possible for any tree? ANS: " + maxScore);
    }
}
