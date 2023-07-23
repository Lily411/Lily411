package Day17;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day17\\Day17_input.txt";
        Scanner input = new Scanner(new FileInputStream(path));

        String str = input.next();
        String[] all = str.split("");
        int[] jets = new int[all.length];
        for(int i = 0 ; i < jets.length ; i++){
            if(all[i].equals("<")){
                jets[i] = -1;
            }
            if(all[i].equals(">")){
                jets[i] = 1;
            }
        }

        Complex[][] rocks =
                {
                        {new Complex(0,0), new Complex(1,0), new Complex(2,0), new Complex(3,0)},
                        {new Complex(1,0), new Complex(0,1), new Complex(1,1), new Complex(2,1), new Complex(1,2)},
                        {new Complex(0,0), new Complex(1,0), new Complex(2,0), new Complex(2,1), new Complex(2,2)},
                        {new Complex(0,0), new Complex(0,1), new Complex(0,2), new Complex(0,3)},
                        {new Complex(0,0), new Complex(1,0), new Complex(0,1), new Complex(1,1)}
                };
        LinkedList<String> solid = new LinkedList<>();
        ArrayList<Long> imag = new ArrayList<>();
        imag.add(-1L);

        for(int i = 0 ; i < 7 ; i++){
            String str1 = i + "," +"-1";
            solid.add(str1);
        }
        long h = 0;
        long h1 = 0;

        long rc = 0;
        int ri = 0;

        LinkedList<Complex> rock = new LinkedList<>();
        LinkedList<String> rockStr = new LinkedList<>();
        for(int i = 0; i < rocks[ri].length; i++){
            Complex begin = new Complex(2,h+3);
            Complex result = begin.sum(rocks[ri][i], begin);
            rock.add(result);
            String str3 = result.getReal()+","+result.getImag();
            rockStr.add(str3);
        }

        Set<String> compare = new HashSet<>();
        Dictionary<Key, long[]> seen = new Hashtable<>();
        long offset = 0;
        long t =  1000000000000L;
        while (rc < t){
            for(int i = 0 ; i < jets.length ; i++){
                LinkedList<Complex> moved = new LinkedList<>();
                LinkedList<String> movedStr = new LinkedList<>();

                int flag = 0;
                for(int j = 0 ; j < rock.size();j++){
                    Complex jet = new Complex(jets[i], 0);
                    Complex result = jet.sum(jet, rock.get(j));
                    moved.add(result);
                    String str2 = result.getReal()+","+result.getImag();
                    movedStr.add(str2);
                    if(result.getReal() < 0){
                        flag = 1;
                    }
                    if(result.getReal()>6){
                        flag = 1;
                    }
                }

                compare.clear();
                compare.addAll(solid);
                compare.retainAll(movedStr);

                if(flag == 0 && compare.size()==0){
                    rock.removeAll(rock);
                    rock.addAll(moved);
                    rockStr.removeAll(rockStr);
                    rockStr.addAll(movedStr);
                }
                moved.removeAll(moved);
                movedStr.removeAll(movedStr);
                for(int j = 0 ;j < rock.size() ; j++){
                    Complex c1 = new Complex(0,-1);
                    Complex result = c1.sum(rock.get(j), c1);
                    moved.add(result);
                    String str2 = result.real+","+result.imag;
                    movedStr.add(str2);
                }

                compare.clear();
                compare.addAll(solid);
                compare.retainAll(movedStr);

                if(compare.size() > 0){
                    solid.addAll(rockStr);
                    int max = 0;
                    for(int j = 0 ; j < rock.size(); j++){
                        imag.add(rock.get(j).imag);
                    }
                    imag.sort(Comparator.reverseOrder());
                    h = imag.get(0)+1;
                    rc ++;
                    System.out.println(rc);
                    if(rc == 2022){
                        h1 = h;
                    }
                    if(rc >=t){
                        break;
                    }
                    ri = (ri+1) %5;
                    rock.removeAll(rock);
                    rockStr.removeAll(rockStr);
                    for(int j = 0; j < rocks[ri].length; j++){
                        Complex result = new Complex(rocks[ri][j].real+2,rocks[ri][j].imag+3+h);
                        rock.add(result);
                        String str3 = result.real + "," + result.imag;
                        rockStr.add(str3);
                    }
                    Key key = new Key(i, ri, summarize(solid));
                    Enumeration enu = seen.keys();
                    while(enu.hasMoreElements()){
                        Key key1 = (Key) enu.nextElement();
                        if(key1.toString().equals(key.toString())){
                            long lrc = seen.get(key1)[0];
                            long lh = seen.get(key1)[1];
                            long rem = t - rc;
                            long rep = rem / (rc - lrc);
                            offset = rep * (h - lh);
                            rc += rep * (rc - lrc);
                            Enumeration enu1 = seen.keys();
                            while (enu1.hasMoreElements()){
                                Key key2 = (Key) enu1.nextElement();
                                seen.remove(key2);
                            }
                        }
                        break;
                    }
                    long[] a = {rc,h};
                    seen.put(key,a);
                }
                else{
                    rock.removeAll(rock);
                    rock.addAll(moved);
                    rockStr.removeAll(rockStr);
                    rockStr.addAll(movedStr);
                }
            }
        }

        System.out.println("Part 1.");
        System.out.println("How many units tall will the tower of rocks be after 2022 rocks have stopped falling?");
        System.out.println("ANS:" + h1);
        System.out.println();
        System.out.println("Part 2.");
        System.out.println("How tall will the tower be after 1000000000000 rocks have stopped?");
        System.out.println("ANS: " + (h+offset));

    }

    public static long[] summarize(LinkedList<String> solid){
        long[] o = {-10,-10,-10,-10,-10,-10,-10};

        for(int j = 0 ; j < solid.size(); j++){
            String str = solid.get(j);
            String[] all = str.split("\\,");
            long r = Long.parseLong(all[0]);
            long i = Long.parseLong(all[1]);
            o[(int) r] = o[(int) r]> i ? o[(int) r] : i;
        }

        Arrays.sort(o);
        long top = o[o.length-1];

        long[] x = new long[o.length];
        for(int j = 0 ; j < x.length ; j++){
            x[j] = o[j] - top;
        }
        return x;
    }
}
