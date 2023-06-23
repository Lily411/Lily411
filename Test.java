package Day07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day07\\Day07_input.txt";

        //Part 1
        Scanner input1 = new Scanner(new FileInputStream(path));
        ArrayList<Directory> listDir = new ArrayList<>();
        while(input1.hasNextLine()){
            String str = input1.nextLine();
            String[] all = str.split("\\s");

            if(all[1].equals("cd") && !str.contains("..") && !str.contains("/")){
                String dirName = all[2];
                Directory dir = new Directory(dirName,0,1);
                listDir.add(dir);
                for(Directory l:listDir){
                    if(l.getInCount()!=0 && l.getName()!=dirName){
                        l.setInCount(l.getInCount()+1);
                    }
                }
            }

            else if(str.matches(".*\\d+.*")){
                int size = Integer.parseInt(all[0]);
                for(Directory l:listDir){
                    if(l.getInCount()!=0)
                        l.setSize(l.getSize()+size);
                }
            }

            else if(str.contains("..")){
                for(Directory l:listDir){
                    if(l.getInCount()!=0)
                        l.setInCount(l.getInCount()-1);
                }
            }
        }
        int sumSizesOfSmallerThan100000 = 0;
        for (Directory l:listDir) {
            if(l.getSize()<=100000){
                sumSizesOfSmallerThan100000 += l.getSize();
            }
        }
        System.out.println("Part 1. a total size of those directories at most 100000 is " + sumSizesOfSmallerThan100000);

        //Part 2
        Scanner input2 = new Scanner(new FileInputStream(path));
        int usedSpace = 0;
        while(input2.hasNextLine()){
            String str = input2.nextLine();
            String[] all = str.split("\\s");
            if(str.matches(".*\\d+.*")){
                int size = Integer.parseInt(all[0]);
                usedSpace += size;
            }
        }
        int totalSize = 70000000;
        int needUnusedSpace = 30000000;
        int UnusedSpace = totalSize - usedSpace;
        int needToRelease = needUnusedSpace - UnusedSpace;

        Collections.sort(listDir);
        for (Directory l:listDir ){
            if(l.getSize()>=needToRelease){
                System.out.println("Part 2. Need to release space:  " + needToRelease);
                System.out.println("======> So, the smallest directory to free up the space: " + l.getSize());
                break;
            }
        }
    }
}
