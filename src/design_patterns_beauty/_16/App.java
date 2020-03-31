package design_patterns_beauty._16;

import design_patterns_beauty._16.perfect.ApiStatInfo;
import design_patterns_beauty._16.perfect.ApplicationContext;

/**
 * 描述:
 * 设计原则：开闭原则栗子。
 * <p>
 * API接口监控栗子：
 * AlertRule 存储告警规则，可以自由设置。
 * Notification 是告警通知类，支持邮件、 短信、微信、手机等多种通知渠道。
 * NotificationEmergencyLevel 表示通知的紧急程度， 包括 SEVERE(严重)、URGENCY(紧急)、NORMAL(普通)、TRIVIAL(无关紧 要)，不同的紧急程度对应不同的发送渠道。
 *
 * @author Noah
 * @create 2020-03-09 12:49
 */
public class App {

    public static void main(String[] args) {

        ApiStatInfo apiStatInfo = new ApiStatInfo();

        ApplicationContext.getInstance().getAlert().check(apiStatInfo);
    }
}
