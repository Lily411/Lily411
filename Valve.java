package Day16;

import java.util.ArrayList;

public class Valve {
    private String name;
    private int rate;
    ArrayList<String> valves = new ArrayList<>();

    public Valve(String name, int rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public ArrayList<String> getValves() {
        return valves;
    }

    public void setValves(ArrayList<String> valves) {
        this.valves = valves;
    }

    @Override
    public String toString() {
        return "Valve{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", values=" + valves +
                '}';
    }

    public void add(String s) {
        valves.add(s);
    }

    public int size() {
        return valves.size();
    }

    public String get(int i) {
        return valves.get(i);
    }
}
