package design_patterns_beauty._16.perfect;

/**
 * 描述:
 * api告警处理器
 *
 * @author Noah
 * @create 2020-03-09 14:06
 */
public abstract class AlertHandler {


    protected AlertRule rule;

    protected Notification notification;

    public AlertHandler() {
    }

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiStatInfo apiStatInfo);
}
