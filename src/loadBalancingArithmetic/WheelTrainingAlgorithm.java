package loadBalancingArithmetic;

import java.util.*;

/**
 * 描述:
 * 轮训算法
 *
 * @author codingprh
 * @create 2018-11-06 3:18 PM
 */
public class WheelTrainingAlgorithm {

    private Map<String, Integer> serverWeightMap = new HashMap<>();

    private static Integer pos = 0;

    public String getServer() {

        serverWeightMap.putAll(ServerWeightMapTables.getServerWeightMap());
        Set<String> keysSet = serverWeightMap.keySet();

        List<String> keysList = new ArrayList<>();
        keysList.addAll(keysSet);

        String server = null;

        synchronized (pos) {
            if (pos >= keysList.size()) {
                pos = 0;
            }
            server = keysList.get(pos);
            pos++;
        }
        return server;
    }


}