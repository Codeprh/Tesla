package design_patterns_beauty._16.perfect;

/**
 * 描述:
 * TPS-api告警处理器
 *
 * @author Noah
 * @create 2020-03-09 21:12
 */
public class TpsAlertHandler extends AlertHandler {

    public TpsAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {

        long tps = apiStatInfo.getRequestCount() / apiStatInfo.getDurationOfSeconds();


    }
}
