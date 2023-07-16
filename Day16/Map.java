package Day16;

import java.util.LinkedList;

public class Map {
    private String name;
    LinkedList<String> valve = new LinkedList<>();
    LinkedList<Integer> valveDist = new LinkedList<>();

    public Map(String name) {
        this.name = name;
    }

    public void add(String s, int i) {
        valve.add(s);
        valveDist.add(i);
    }

    public int size() {
        return valve.size();
    }

    public String getName() {
        return name;
    }

    public String getValve(int i) {
        return valve.get(i);
    }

    public int getValveDist(int i) {
        return valveDist.get(i);
    }

    public void setValveDist(int i, int dist) {
        this.valveDist.set(i, dist);
    }

    @Override
    public String toString() {
        return "Map{" +
                "name='" + name + '\'' +
                ", valve=" + valve +
                ", valveDist=" + valveDist +
                '}';
    }

    public void remove(int j) {
        valve.remove(j);
        valveDist.remove(j);
    }
}
