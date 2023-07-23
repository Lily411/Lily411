package Day17;

public class Complex {
    long real, imag;

    public Complex(long real, long imag) {
        this.real = real;
        this.imag = imag;
    }

    public Complex sum(Complex c1, Complex c2){
        Complex temp = new Complex(0,0);
        temp.real = c1.real + c2.real;
        temp.imag = c1.imag + c2.imag;
        return temp;
    }

    public long getReal() {
        return real;
    }

    public long getImag() {
        return imag;
    }

    @Override
    public String toString() {
        return "Complex{" + real  + "+" + imag + "i" + '}';
    }
}
