package designPatternsBeauty._16.perfect;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author Noah
 * @create 2020-03-09 12:58
 */
public class Alert {

    private List<AlertHandler> alertHandlerList = new ArrayList<>();

    public void addAlertHandler(AlertHandler alertHandler) {
        alertHandlerList.add(alertHandler);
    }

    public void check(ApiStatInfo apiStatInfo) {
        for (AlertHandler handler : alertHandlerList) {
            handler.check(apiStatInfo);
        }

    }
}
