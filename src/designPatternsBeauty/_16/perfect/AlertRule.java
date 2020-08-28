package designPatternsBeauty._16.perfect;

/**
 * 描述:
 * api告警规则
 *
 * @author Noah
 * @create 2020-03-09 14:10
 */
public class AlertRule {

    //通用告警规则
    private String api;
    private AlertRuleEnum alertRuleEnum;

    //tps
    private long maxTPSLimit;


    public AlertRule getMatchedRule(String apiName) {

        if (apiName.equals(api)) {

        }
        return null;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

}
