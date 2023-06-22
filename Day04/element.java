package Day04;

public class element implements Comparable{
    private int first;
    private int last;

    public element(int first, int last) {
        this.first = first;
        this.last = last;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    @Override
    public int compareTo(Object o) {
        element ele = (element) o;
        int result1 = this.getFirst() - ele.getFirst();
        int result2 = this.getLast() - ele.getLast();
        return result1 <=0 && result2 >=0? 1:-1;
    }

    public int overLap(element e){
        int result1 = this.getLast() - e.getFirst();
        int result2 = this.getFirst() - e.getLast();
        return result1 >=0 && result2 <=0? 1:-1;
    }
}
