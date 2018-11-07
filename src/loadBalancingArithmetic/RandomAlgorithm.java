package loadBalancingArithmetic;

import java.util.*;

/**
 * 描述:
 * 随机算法
 *
 * @author codingprh
 * @create 2018-11-07 9:15 AM
 */
public class RandomAlgorithm {
    private Map<String, Integer> serverMap = new HashMap<>();
    private Integer pos = 0;

    public String getServer() {

        serverMap.putAll(ServerWeightMapTables.getServerWeightMap());

        List<String> ipList = new ArrayList<>();
        ipList.addAll(serverMap.keySet());

        String server = null;
        Random random = new Random();
        pos = random.nextInt(ipList.size());

        server = ipList.get(pos);
        return server;

    }
}