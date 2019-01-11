package threadCommunication;

/**
 * 描述:
 * 用于线程轮训打印
 * 题目：
 * 编写两个线程，一个线程打印1~25，另一个线程打印字母A~Z，打印顺序为12A34B56C……5152Z，要求使用线程间的通信。
 *
 * @author codingprh
 * @create 2018-12-20 8:41 AM
 */
public class ThraedWheelTrainingPrint {
    /**
     * 打印数字
     *
     * @param max
     * @return
     */
    public static String[] buildNoArr(int max) {
        String[] noArr = new String[max];
        for (int i = 0; i < max; i++) {
            noArr[i] = Integer.toString(i + 1);
        }
        return noArr;
    }

    /**
     * 打印字符
     *
     * @param max
     * @return
     */
    public static String[] buildCharArr(int max) {
        String[] charArr = new String[max];
        int tmp = 65;
        for (int i = 0; i < max; i++) {
            charArr[i] = String.valueOf((char) (tmp + i));
        }
        return charArr;
    }

    /**
     * 打印方法
     *
     * @param input
     */
    public static void print(String... input) {
        if (input == null) {
            return;
        }
        for (String each : input) {
            System.out.print(each);
        }
    }

    static class ThreadToGo {
        int value = 1;
    }

    public static void main(String[] args) {

        ThreadToGo threadToGo = new ThreadToGo();
        //打印数字
        SingletonThreadPoolEnum.INSTANCE.run(() -> {
            try {
                String[] arr = buildNoArr(52);
                for (int i = 0; i < arr.length; i = i + 2) {
                    synchronized (threadToGo) {
                        while (threadToGo.value == 2) {
                            threadToGo.wait();
                        }
                        print(arr[i], arr[i + 1]);
                        threadToGo.value = 2;
                        threadToGo.notify();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Oops...");
            }
        });
        //打印字母
        SingletonThreadPoolEnum.INSTANCE.run(() -> {
            try {
                String[] arr = buildCharArr(26);
                for (int i = 0; i < arr.length; i++) {
                    synchronized (threadToGo) {
                        while (threadToGo.value == 1) {
                            threadToGo.wait();
                        }
                        print(arr[i]);
                        threadToGo.value = 1;
                        threadToGo.notify();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Oops...");
            }

        });

    }
}