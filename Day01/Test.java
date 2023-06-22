package Day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Test {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day01\\Day01_input.txt";
        BufferedReader br = new BufferedReader(new FileReader(path));
        try{
            String line;
            ArrayList<Integer> list = new ArrayList<>();
            ArrayList<Integer> sum = new ArrayList<>();

            while((line=br.readLine())!=null){
                if(!line.equals("") ) {
                    int food = Integer.parseInt(line);
                    list.add(food);
                }
                else{
                    int total = 0;
                    for (Integer l:list) {
                        total = total + l;
                    }
                    sum.add(total);
                    list.removeAll(list);
                }
            }
            int total = 0;
            for (Integer l:list) {
                total = total + l;
            }
            sum.add(total);
            list.removeAll(list);

            Collections.sort(sum);
            Collections.reverse(sum);

            int max = sum.get(0);
            int top3 = sum.get(0) + sum.get(1) + sum.get(2);

            System.out.println("Total Calories: " + max);
            System.out.println("Sum of the top 3 Calories: " + top3);
        }finally {
            br.close();
        }

    }
}
