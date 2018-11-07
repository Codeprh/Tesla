import loadBalancingArithmetic.WeightedRandomAlgorithm;
import loadBalancingArithmetic.WeightedRotationAlgorithm;

import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException {

        WeightedRandomAlgorithm algorithm = new WeightedRandomAlgorithm();

        for (int i = 0; i < 100; i++) {
            System.out.println(algorithm.getServer());
        }
    }
}
