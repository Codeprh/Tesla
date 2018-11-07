package loadBalancingArithmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 * 源地址哈希算法
 *
 * @author codingprh
 * @create 2018-11-07 10:11 AM
 */
public class SourceAddressHashAlgorithm {
    private Map<String, Integer> serverMap = new HashMap<>();
    private Integer pos = 0;

    public String getServer(String ipAddress) {

        serverMap.putAll(ServerWeightMapTables.getServerWeightMap());
        List<String> ipList = new ArrayList<>();
        ipList.addAll(serverMap.keySet());

        String server = null;

        Integer ipVal = ipAddress.hashCode();
        Integer pos = Math.abs(ipVal % ipList.size());
        return ipList.get(pos);
    }
}