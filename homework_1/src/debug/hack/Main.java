package debug.hack;

import java.util.concurrent.BrokenBarrierException;

public class Main {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        Matrix a = Matrix.read("data/sample_A.txt");
        Matrix b = Matrix.read("data/sample_B.txt");
        Matrix c = a.multiply(b);
        Matrix.write("data/C.txt", c);
    }
}
