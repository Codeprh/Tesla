import loadBalancingArithmetic.WheelTrainingAlgorithm;

public class Main {

    public static void main(String[] args) {

        WheelTrainingAlgorithm algorithm = new WheelTrainingAlgorithm();
        for (int i = 0; i < 100; i++) {
            System.out.println(algorithm.getServer());
        }
    }
}
