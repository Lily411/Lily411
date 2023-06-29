package Day12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day12\\Day12_input.txt";
        Scanner input = new Scanner(new FileInputStream(path));

        String str = input.nextLine();
        int row = str.length();
        int col = 1;

        while (input.hasNextLine()){
            String str1 = input.nextLine();
            if(str1.equals("")){
                break;
            }
            col +=1;
        }

        char[][] map = new char[col][row];
        int[][] visited = new int[col][row];
        int countCol = 0;
        Scanner input1 = new Scanner(new FileInputStream(path));
        while(input1.hasNextLine()){
            String str2 = input1.nextLine();
            char[] c = str2.toCharArray();
            for(int i = 0; i < c.length; i++){
                map[countCol][i] =c[i];
                visited[countCol][i] = 0;
            }
            countCol ++;
        }

        //Part 1
        int er = 0;
        int ec = 0;
        LinkedList<node> quene = new LinkedList<>();
        for(int i = 0 ; i < map.length; i ++){
            for(int j = 0 ; j < map[i].length;j++){
                if(map[i][j] == 'S'){
                    int sc = i;
                    int sr = j;
                    map[sc][sr] = 'a';
                    quene.addFirst(new node(0,sc,sr));
                    visited[sc][sr] = 1;
                }
                if(map[i][j] == 'E'){
                    ec = i;
                    er = j;
                    map[ec][er] = 'z';
                }
            }
        }

        while(quene.size() > 0){
            int d = quene.peekFirst().getDestination();
            int c = quene.peekFirst().getCol();
            int r = quene.peekFirst().getRow();
            quene.removeFirst();

            int[][] next = {{c+1,r},{c-1,r},{c,r+1},{c,r-1}};
            for(int i = 0 ; i < next.length ; i ++){
                for(int j = 0; j < next[i].length -1 ; j++){
                    int nc = next[i][j];
                    int nr = next[i][j+1];
                    if (nc < 0 || nr < 0 || nc>=map.length || nr >= map[0].length){
                        continue;
                    }
                    if(visited[nc][nr] == 1){
                        continue;
                    }
                    if(map[nc][nr] - map[c][r]>1){
                        continue;
                    }
                    if(nr==er && nc==ec){
                        System.out.println("Part 1.");
                        System.out.println("What is the fewest steps required to move from your current position to the location that should get the best signal?");
                        System.out.println("ANS: " + (d+1));
                        System.out.println();
                    }
                    visited[nc][nr] = 1;
                    quene.addLast(new node(d+1,nc,nr));
                }
            }
        }

        //Part 2
        ArrayList<Integer> set = new ArrayList<>();
        LinkedList<node> quene1 = new LinkedList<>();
        for(int k = 0 ; k< map.length; k ++){
            for(int l = 0 ; l < map[k].length;l++){
                if(map[k][l] == 'a'){
                    int sc = k;
                    int sr = l;
                    quene1.addFirst(new node(0,sc,sr));

                    for(int i = 0; i < visited.length;i++){
                        for(int j = 0 ; j < visited[i].length ; j++){
                            visited[i][j] = 0;
                        }
                    }
                    visited[sc][sr] = 1;

                    while(quene1.size() > 0){
                        int d = quene1.peekFirst().getDestination();
                        int c = quene1.peekFirst().getCol();
                        int r = quene1.peekFirst().getRow();
                        quene1.removeFirst();

                        int[][] next = {{c+1,r},{c-1,r},{c,r+1},{c,r-1}};
                        for(int i = 0 ; i < next.length ; i ++){
                            for(int j = 0; j < next[i].length -1 ; j++){
                                int nc = next[i][j];
                                int nr = next[i][j+1];
                                if (nc < 0 || nr < 0 || nc>=map.length || nr >= map[0].length){
                                    continue;
                                }
                                if(visited[nc][nr] == 1){
                                    continue;
                                }
                                if(map[nc][nr] - map[c][r]>1){
                                    continue;
                                }
                                if(nr==er && nc==ec){
                                    set.add(d+1);
                                    quene1.removeAll(quene1);
                                }
                                visited[nc][nr] = 1;
                                quene1.addLast(new node(d+1,nc,nr));
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(set);
        System.out.println("Part 2.");
        System.out.println("What is the fewest steps required to move starting from any square with elevation a to the location that should get the best signal?");
        System.out.println("ANS: " + set.get(0));
    }
}
