package concurrent._21;

/**
 * 描述:
 * cpu指令：cas的本质
 *
 * @author Noah
 * @create 2019-10-16 09:41
 */
public class _21_CAS {

    volatile int count;

    /**
     * cas+自旋
     */
    void addOne() {
        int newVal;

        do {
            newVal = count + 1;
        } while (count != cas(count, newVal));


    }

    /**
     * 模拟cas指令
     *
     * @param expect
     * @param newVal
     * @return
     */
    public int cas(int expect, int newVal) {

        int curVal = count;

        if (curVal == expect) {
            count = newVal;
        }
        //返回写入前的值
        return curVal;
    }

    public static void main(String[] args) {
        _21_CAS app = new _21_CAS();
        app.addOne();
    }
}
