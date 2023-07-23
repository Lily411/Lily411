package Day17;

import java.util.Arrays;

public class Key {
    private long ji;
    private long ri;
    private long[] x;

    public Key(long ji, long ri, long[] x) {
        this.ji = ji;
        this.ri = ri;
        this.x = x;
    }

    public long getJi() {
        return ji;
    }

    public void setJi(int ji) {
        this.ji = ji;
    }

    public long getRi() {
        return ri;
    }

    public void setRi(int ri) {
        this.ri = ri;
    }

    public long[] getX() {
        return x;
    }

    public void setX(long[] x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Key{" +
                "ji=" + ji +
                ", ri=" + ri +
                ", x=" + Arrays.toString(x) +
                '}';
    }
}
