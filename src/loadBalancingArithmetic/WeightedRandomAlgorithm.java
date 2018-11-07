package loadBalancingArithmetic;

import java.util.*;

/**
 * 描述:
 * 加权随机算法
 *
 * @author codingprh
 * @create 2018-11-07 2:48 PM
 */
public class WeightedRandomAlgorithm {
    private Map<String, Integer> serverMap = new HashMap<>();
    private Integer pos = 0;

    public String getServer() {

        serverMap.putAll(ServerWeightMapTables.getServerWeightMap());

        Set<String> keySets = serverMap.keySet();
        List<String> ipList = new ArrayList<>();
        Iterator<String> iterator = keySets.iterator();
        while (iterator.hasNext()) {

            String server = iterator.next();
            Integer weight = serverMap.get(server);

            for (int i = 0; i < weight; i++) {
                ipList.add(server);
            }
        }
        String server = null;
        Random random = new Random();
        pos = random.nextInt(ipList.size());

        server = ipList.get(pos);
        return server;

    }

}