import loadBalancingArithmetic.SourceAddressHashAlgorithm;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException {

        SourceAddressHashAlgorithm algorithm = new SourceAddressHashAlgorithm();

        InetAddress addr = InetAddress.getLocalHost();
        for (int i = 0; i < 100; i++) {
            System.out.println(algorithm.getServer(addr.getHostAddress()));
        }
    }
}
