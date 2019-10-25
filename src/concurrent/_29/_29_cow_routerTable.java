package concurrent._29;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 描述:
 * copy-on-write模式：实现路由表
 *
 * @author Noah
 * @create 2019-10-24 10:15
 */
public class _29_cow_routerTable {
    Map<String, CopyOnWriteArrayList<router>> map = new ConcurrentHashMap<>();

}

/**
 * 路由
 */
class router {

    private String ip;
    private String status;
    private Integer port;

    public router(String ip, String status, Integer port) {
        this.ip = ip;
        this.status = status;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
