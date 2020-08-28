package designPatternsBeauty._16.perfect;

/**
 * 描述:
 * 单例类：负责创建Alert，组装（alertRule和notification），初始化handlers
 *
 * @author Noah
 * @create 2020-03-11 15:27
 */
public class ApplicationContext {

    private AlertRule alertRule;

    private Notification notification;

    private Alert alert;

    public void init() {
        alertRule = new AlertRule();
        notification = new Notification();
        alert = new Alert();
        alert.addAlertHandler(new TpsAlertHandler(null,null));
    }

    public static final ApplicationContext instance = new ApplicationContext();

    private ApplicationContext() {
        instance.init();
    }

    public static ApplicationContext getInstance() {
        return instance;
    }

    public Alert getAlert() {
        return alert;
    }

}
