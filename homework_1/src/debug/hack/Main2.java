package debug.hack;

import java.util.concurrent.BrokenBarrierException;

/**
 * Created by wenke on 2016/9/16.
 */
public class Main2 {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        Matrix a = Matrix.read("data/sample_A.txt");
        Matrix b = Matrix.read("data/sample_B.txt");
        Matrix c = a.quickMultiply(b);
        Matrix.write("data/C.txt", c);
    }
}
