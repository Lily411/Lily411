package Day15;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day15\\Day15_input.txt";
        Scanner input = new Scanner(new FileInputStream(path));

        //Part 1
        LinkedHashSet<Integer> cannot = new LinkedHashSet<>();
        LinkedHashSet<Integer> known = new LinkedHashSet<>();
        int Y = 2000000;
        while(input.hasNextLine()){
            String str = input.nextLine();
            str = str.replaceAll("\\p{Alpha}",",");
            str = str.replaceAll("\\=",",");
            str = str.replaceAll("\\:",",");
            str = str.replaceAll("\\s+",",");
            String[] all = str.split("\\,+");
            int sx = Integer.parseInt(all[1]);
            int sy = Integer.parseInt(all[2]);
            int bx = Integer.parseInt(all[3]);
            int by = Integer.parseInt(all[4]);

            int d = Math.abs(sx-bx) + Math.abs(sy-by);
            int o = d - Math.abs(sy - Y);

            if(o< 0){
                continue;
            }

            int lx = sx - o;
            int hx = sx + o;

            for (int i = lx ; i < hx+1 ; i++){
                cannot.add(i);
            }

            if(by==Y){
                known.add(bx);
            }
        }
        int answer = cannot.size() - known.size();
        System.out.println("Part 1.  In the row where y=2000000, how many positions cannot contain a beacon?");
        System.out.println("ANS: " + answer);
        System.out.println();


        //Part 2
        Scanner input1 = new Scanner(new FileInputStream(path));
        ArrayList<Integer> sxAll = new ArrayList<>();
        ArrayList<Integer> syAll = new ArrayList<>();
        ArrayList<Integer> bxAll = new ArrayList<>();
        ArrayList<Integer> byAll = new ArrayList<>();

        while(input1.hasNextLine()) {
            String str = input1.nextLine();
            str = str.replaceAll("\\p{Alpha}", ",");
            str = str.replaceAll("\\=", ",");
            str = str.replaceAll("\\:", ",");
            str = str.replaceAll("\\s+", ",");
            String[] all = str.split("\\,+");
            sxAll.add(Integer.parseInt(all[1]));
            syAll.add(Integer.parseInt(all[2]));
            bxAll.add(Integer.parseInt(all[3]));
            byAll.add(Integer.parseInt(all[4]));
        }

        int M = 4000000;
        for(int i = 0 ; i <= M ; i ++){

            ArrayList<Integer> interval = new ArrayList<>();
            ArrayList<Integer> q = new ArrayList<>();

            for(int j = 0 ;j < sxAll.size() ; j++){
                int sx = sxAll.get(j);
                int sy = syAll.get(j);
                int bx = bxAll.get(j);
                int by = byAll.get(j);

                int d = Math.abs(sx-bx) + Math.abs(sy-by);
                int o = d - Math.abs(sy - i);

                if(o< 0){
                    continue;
                }

                int lx = sx - o;
                int hx = sx + o;

                interval.add(lx);
                interval.add(hx);
            }
            int[][] sortInterval = new int[interval.size()/2][2];
            for(int a = 0 , l = 0 ;a < sortInterval.length ; a++, l+=2){
                sortInterval[a][0] = interval.get(l);
                sortInterval[a][1] = interval.get(l+1);
            }
            Arrays.sort(sortInterval, Comparator.comparingInt(o -> o[0]));

            q.add(sortInterval[0][0]);
            q.add(sortInterval[0][1]);
            for(int k = 0 ; k < sortInterval.length; k++) {
                int lower = sortInterval[k][0];
                int high = sortInterval[k][1];

                if(lower > q.get(q.size()-1)+1){
                    q.add(lower);
                    q.add(high);
                    continue;
                }
                int max = high > q.get(q.size()-1) ? high : q.get(q.size()-1);
                q.set(q.size()-1 , max);
            }
            if(q.size() > 2){
                long x = q.get(1)+1;
                long result = x*4000000 + i;
                System.out.println("Part 2. What is its tuning frequency?");
                System.out.println("ANS: " + result);
                System.exit(0);
            }
        }
    }
}
