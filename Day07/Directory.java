package Day07;

public class Directory implements Comparable{
    private String name;
    private int size;
    private int inCount;

    public Directory(String name, int size, int inCount) {
        this.name = name;
        this.size = size;
        this.inCount = inCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getInCount() {
        return inCount;
    }

    public void setInCount(int inCount) {
        this.inCount = inCount;
    }

    @Override
    public int compareTo(Object o) {
        Directory d = (Directory) o;
        int result = this.getSize() - d.getSize();
        return result >= 0 ? 1 : -1;
    }
}
