package designPatternsBeauty._16.perfect;

/**
 * 描述:
 * api接口监控的指标参数
 *
 * @author Noah
 * @create 2020-03-09 12:58
 */
public class ApiStatInfo {

    /**
     * 接口名称
     */
    private String api;

    /**
     * TPS指标：某个指标的最大值
     */
    private long requestCount;

    /**
     * 错误监控
     */
    private long errorCount;

    /**
     * 持续秒数
     */
    private long durationOfSeconds;

    public ApiStatInfo() {
    }

    public ApiStatInfo(String api, long requestCount, long errorCount, long durationOfSeconds) {
        this.api = api;
        this.requestCount = requestCount;
        this.errorCount = errorCount;
        this.durationOfSeconds = durationOfSeconds;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(long requestCount) {
        this.requestCount = requestCount;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

    public long getDurationOfSeconds() {
        return durationOfSeconds;
    }

    public void setDurationOfSeconds(long durationOfSeconds) {
        this.durationOfSeconds = durationOfSeconds;
    }
}
