package Day12;

public class node {
    private int destination;
    private int col;
    private int row;

    public node(int destination, int col, int row) {
        this.destination = destination;
        this.col = col;
        this.row = row;
    }

    public int getDestination() {
        return destination;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
