package designPatternsBeauty._16.perfect;

/**
 * 描述:
 * 报错次数处理器
 *
 * @author Noah
 * @create 2020-03-09 21:32
 */
public class ErrorAlertHandler extends AlertHandler {

    public ErrorAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {


    }
}
