package concurrent._1;

/**
 * 描述:
 * 有序性：导致的并发问题，双重检查举例
 *
 * @author Noah
 * @create 2019-09-23 09:35
 */
public class _1_Order {

    private static _1_Order instace;

    private _1_Order() {
    }

    public static _1_Order getInstance() {

        if (instace == null) {

            synchronized (_1_Order.class) {

                if (instace == null) {
                    instace = new _1_Order();
                }
            }

        }
        return instace;
    }




}
