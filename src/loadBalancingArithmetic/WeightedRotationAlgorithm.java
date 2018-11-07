package loadBalancingArithmetic;

import java.util.*;

/**
 * 描述:
 * 加权轮训算法
 *
 * @author codingprh
 * @create 2018-11-07 2:25 PM
 */
public class WeightedRotationAlgorithm {
    private Map<String, Integer> serverMap = new HashMap<>();
    private Integer pos = 0;

    public String getServer() {

        serverMap.putAll(ServerWeightMapTables.getServerWeightMap());

        Set<String> keySets = serverMap.keySet();
        Iterator<String> iterable = keySets.iterator();

        List<String> keyList = new ArrayList<>();

        while (iterable.hasNext()) {
            String server = iterable.next();

            Integer weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) {
                keyList.add(server);
            }
        }

        String resultServer = null;
        synchronized (pos) {
            if (pos >= keyList.size()) {
                pos = 0;
            }
            resultServer = keyList.get(pos);
            pos++;
        }
        return resultServer;

    }

}