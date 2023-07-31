package Day18;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\Win\\Desktop\\Java_IJ\\adventofcode\\src\\Day18\\Day18_input.txt";
        Scanner input = new Scanner(new FileInputStream(path));

        Dictionary<Cube, Integer> faces = new Hashtable<>();
        LinkedHashSet<String> droplet = new LinkedHashSet<>();

        Cube offsets[] =
                {
                        new Cube(0, 0, 0.5),
                        new Cube(0, 0.5, 0),
                        new Cube(0.5, 0, 0),
                        new Cube(0, 0, -0.5),
                        new Cube(0, -0.5, 0),
                        new Cube(-0.5, 0, 0),
                };

        double mx = Integer.MAX_VALUE;
        double my = Integer.MAX_VALUE;
        double mz = Integer.MAX_VALUE;
        double Mx = Integer.MIN_VALUE;
        double My = Integer.MIN_VALUE;
        double Mz = Integer.MIN_VALUE;

        while (input.hasNextLine()) {
            String str = input.nextLine();
            String[] all = str.split("\\,");

            double x = Integer.parseInt(all[0]);
            double y = Integer.parseInt(all[1]);
            double z = Integer.parseInt(all[2]);

            String str1 = x + "," + y + "," + z;
            droplet.add(str1);

            mx = mx < x ? mx : x;
            my = my < y ? my : y;
            mz = mz < z ? mz : z;

            Mx = Mx > x ? Mx : x;
            My = My > y ? My : y;
            Mz = Mz > z ? Mz : z;

            for (int i = 0; i < offsets.length; i++) {
                double dx = x + offsets[i].getX();
                double dy = y + offsets[i].getY();
                double dz = z + offsets[i].getZ();

                Cube c = new Cube(dx, dy, dz);
                Enumeration enu = faces.keys();
                boolean flag = true;
                while (enu.hasMoreElements()) {
                    Cube c1 = (Cube) enu.nextElement();
                    if (c1.toString().equals(c.toString())) {
                        flag = false;
                        int count = faces.get(c1) + 1;
                        faces.remove(c1);
                        faces.put(c1, count);
                    }
                }
                if (flag) {
                    faces.put(c, 1);
                }
            }
        }
        int count = 0;
        Enumeration enu = faces.keys();
        while (enu.hasMoreElements()) {
            Cube c1 = (Cube) enu.nextElement();
            if (faces.get(c1) == 1) {
                count++;
            }
        }
        //Part 1.
        System.out.println("Part 1.");
        System.out.println("What is the surface area of your scanned lava droplet?");
        System.out.println(count);

        //Part 2.
        mx -= 1;
        my -= 1;
        mz -= 1;

        Mx += 1;
        My += 1;
        Mz += 1;

        LinkedList<Cube> queue = new LinkedList<>();
        queue.add(new Cube(mx, my, mz));
        LinkedList<Cube> air = new LinkedList<>();
        queue.add(new Cube(mx, my, mz));

        while (queue.size() > 0) {
            double x = queue.peekFirst().getX();
            double y = queue.peekFirst().getY();
            double z = queue.peekFirst().getZ();
            queue.removeFirst();

            for (int i = 0; i < offsets.length; i++) {
                double nx = x + offsets[i].getX() * 2;
                double ny = y + offsets[i].getY() * 2;
                double nz = z + offsets[i].getZ() * 2;

                Cube k = new Cube(nx, ny, nz);
                if (!(mx <= nx && nx <= Mx && my <= ny && ny <= My && mz <= nz && nz <= Mz)) {
                    continue;
                }
                int flag = 0;
                for(int j = 0; j < air.size(); j++){
                    if(air.get(j).toString().equals(k.toString())){
                        flag = 1;
                        break;
                    }
                }
                if(flag == 1){
                    continue;
                }
                Iterator it = droplet.iterator();
                while(it.hasNext()){
                    String str2 = nx + "," + ny + "," + nz;
                    if(it.next().equals(str2)){
                        flag = 1;
                        break;
                    }
                }
                if(flag == 1){
                    continue;
                }
                air.add(k);
                queue.add(k);
            }
        }

        LinkedList<Cube> free = new LinkedList<>();
        for(int i = 0 ; i < air.size() ; i++){
            double x = air.get(i).getX();
            double y = air.get(i).getY();
            double z = air.get(i).getZ();

            for(int j = 0 ; j < offsets.length;j++){
                double nx = x + offsets[j].getX();
                double ny = y + offsets[j].getY();
                double nz = z + offsets[j].getZ();

                Cube k = new Cube(nx,ny,nz);
                free.add(k);
            }
        }

        Enumeration enu1 = faces.keys();
        Set<String> compare = new HashSet<>();
        while (enu1.hasMoreElements()) {
            Cube c1 = (Cube) enu1.nextElement();
            compare.add(c1.toString());
        }
        LinkedList<String> freeStr = new LinkedList<>();
        for(int i = 0 ; i < free.size() ; i ++){
            freeStr.add(free.get(i).toString());
        }
        compare.retainAll(freeStr);
        System.out.println("Part 2.");
        System.out.println("What is the exterior surface area of your scanned lava droplet?");
        System.out.println(compare.size());
    }
}
