package loadBalancingArithmetic;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 服务地址配置表
 *
 * @author codingprh
 * @create 2018-11-06 3:53 PM
 */
public class ServerWeightMapTables {
    private static Map<String, Integer> serverWeightMap = new HashMap<>();

    static {
        serverWeightMap.put("192.168.1.0", 2);
        serverWeightMap.put("192.168.1.1", 2);
        serverWeightMap.put("192.168.1.2", 2);
        serverWeightMap.put("192.168.1.3", 1);

    }

    public static Map<String, Integer> getServerWeightMap() {

        return serverWeightMap;
    }

}