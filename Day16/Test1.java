package Day16;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Test1 {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day16\\Day16_input.txt";

        Scanner input = new Scanner(new FileInputStream(path));
        int findTotalValve = 0;
        while (input.hasNext()) {
            String str = input.nextLine();
            String[] all = str.split("\\s+");
            if (all[0].equals("Valve")) {
                findTotalValve++;
            }
        }
        Valve[] valves = new Valve[findTotalValve];

        Scanner input1 = new Scanner(new FileInputStream(path));
        int count = 0;
        int notZeroRate = 0;
        while (input1.hasNextLine()) {
            String str = input1.nextLine();
            String[] all = str.split("\\s+");
            String[] num = all[4].replaceAll("\\D", ",").split("\\,+");

            String name = all[1];
            int rate = Integer.parseInt(num[1]);
            if(rate > 0){
                notZeroRate++;
            }
            valves[count] = new Valve(name, rate);

            for (int i = 9; i < all.length; i++) {
                all[i] = all[i].replaceAll("\\,", "").replaceAll("\\s+", "");
                valves[count].add(all[i]);
            }
            count++;
        }

        Map[] nonEmpty = new Map[notZeroRate+1];
        LinkedList<String> nonEmpty1 = new LinkedList<>();
        int count1 = 0;
        for(int i = 0 ; i < valves.length ; i++){
            if(!valves[i].getName().equals("AA") && valves[i].getRate() == 0){
                continue;
            }
            if(!valves[i].getName().equals("AA")){
                nonEmpty1.add(valves[i].getName());
            }


            nonEmpty[count1] = new Map(valves[i].getName());
            for(int j = 0 ; j < valves.length ; j++ ){
                if(valves[j].getRate()> 0){
                    nonEmpty[count1].add(valves[j].getName(), 0);
                }
            }
            LinkedList<String> visited = new LinkedList<>();
            visited.add(valves[i].getName());

            LinkedList<Day16.count> queue = new LinkedList<>();
            queue.addLast(new count(0,valves[i].getName()));

            while(queue.size() > 0){
                int distance = queue.peekFirst().getDistance();
                String position = queue.peekFirst().getPosition();
                queue.removeFirst();

                String[] neighbor = new String[1];
                for(int j = 0 ; j < valves.length;j++){
                    if(valves[j].getName().equals(position)){
                        neighbor = new String[valves[j].size()];
                        for(int k = 0 ; k < valves[j].size() ; k++){
                            neighbor[k] = valves[j].get(k);
                        }
                        break;
                    }
                }

                for (int j = 0 ; j < neighbor.length;j++){
                    if(visited.contains(neighbor[j])){
                        continue;
                    }
                    visited.add(neighbor[j]);
                    for(int k = 0 ; k < valves.length; k++){
                        if(valves[k].getName().equals(neighbor[j])){
                            if(valves[k].getRate() > 0){
                                for(int l = 0 ; l < nonEmpty[count1].size(); l++){
                                    if(nonEmpty[count1].getValve(l).equals(neighbor[j])){
                                        nonEmpty[count1].setValveDist(l,distance+1);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    queue.add(new count(distance +1, neighbor[j]));
                }
            }
            for(int j = 0 ; j <nonEmpty[count1].size();j++){
                if(nonEmpty[count1].getValve(j).equals(nonEmpty[count1].getName())){
                    nonEmpty[count1].remove(j);
                }
            }
            count1++;
        }

        Dictionary<String, Integer> indices = new Hashtable<>();
        for(int i = 0 ; i < nonEmpty1.size();i++){
            indices.put(nonEmpty1.get(i),i );
        }

        HashMap<String, Integer>cache = new HashMap<>();

        //Part 1
        System.out.println("Part 1.");
        System.out.println("Work out the steps to release the most pressure in 30 minutes. What is the most pressure you can release?");
        System.out.println(dfs(30,"AA",0,nonEmpty,valves,indices, cache));

        System.out.println();

        int m = 0;
        int b = (1<< nonEmpty1.size())-1;
        for( int i = 0 ; i < (b+1)/2 ; i ++){
            int max =  dfs(26,"AA",i,nonEmpty,valves,indices, cache) + dfs(26,"AA",b^i,nonEmpty,valves,indices, cache);
            m = m > max? m:max;
        }
        System.out.println("Part 2.");
        System.out.println("With you and an elephant working together for 26 minutes, what is the most pressure you could release?");
        System.out.println(m);

    }
    public static int dfs(int time, String valve, int bitmask, Map[] nonEmpty, Valve[] valves, Dictionary<String, Integer> indices, HashMap<String, Integer>cache){
        String str = time+","+valve+","+bitmask;
        if(cache.containsKey(str)){
            return cache.get(str);
        }
        int maxval = 0;
        for(int i = 0 ; i < nonEmpty.length; i++){
            if(nonEmpty[i].getName().equals(valve)){
                for(int j = 0 ; j < nonEmpty[i].size();j++){
                    int bit = 1<< indices.get(nonEmpty[i].getValve(j));
                    if((bitmask & bit) != 0){
                        continue;
                    }
                    int remainTime = time - nonEmpty[i].getValveDist(j)-1;

                    if(remainTime <= 0){
                        continue;
                    }
                    int rate = 0;
                    for(int k = 0 ; k < valves.length ; k++){
                        if(valves[k].getName().equals(nonEmpty[i].getValve(j))){
                            rate = valves[k].getRate();
                            break;
                        }
                    }
                    int max = dfs(remainTime, nonEmpty[i].getValve(j), bitmask | bit, nonEmpty, valves, indices,cache)+rate*remainTime;
                    maxval = maxval> max? maxval: max;
                }
                break;
            }
        }
        cache.put(str, maxval);
        return maxval;
    }
}
