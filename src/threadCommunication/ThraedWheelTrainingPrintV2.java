package threadCommunication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 描述:
 * 第二版本实现打印
 * * 描述:
 * * 用于线程轮训打印
 * * 题目：
 * * 编写两个线程，一个线程打印1~25，另一个线程打印字母A~Z，打印顺序为12A34B56C……5152Z，要求使用线程间的通信。
 *
 * @author codingprh
 * @create 2019-01-11 11:27 AM
 */
public class ThraedWheelTrainingPrintV2 {

    private final int ALPHABET_START = 65;

    private int lock = 2;


    /**
     * 提前准备好最大数字集合
     *
     * @param maxDigital 最大数字
     * @return
     */
    public List<Integer> getDigital(Integer maxDigital) {
        return Arrays.stream(IntStream.rangeClosed(1, maxDigital).toArray()).boxed().collect(Collectors.toList());
    }

    /**
     * 提前准备好字母
     * 原理：利用char特性：65字母:a
     *
     * @param maxAlphabet 最大值26个字母
     * @return
     */
    public List<String> getAlphabet(Integer maxAlphabet) {
        List<String> alphabetList = new ArrayList<>();
        IntStream.rangeClosed(ALPHABET_START, ALPHABET_START + maxAlphabet).forEach((alphabet) -> {
            alphabetList.add(String.valueOf((char) (alphabet)));
        });

        return alphabetList;
    }

    public void print(Object... po) {
        for (Object obj : po) {
            System.out.print(obj.toString());
        }

    }

    public <T> void printList(List<T> list) {
        for (T t : list) {
            System.out.print(t.toString());
        }
    }

    public void go(List<Integer> digitalList, List<String> alphabetList) {
        //SingletonThreadPoolEnum executorServicePool = SingletonThreadPoolEnum.INSTANCE;
        DoubleCheckedSingthonThreadPool executorServicePool = DoubleCheckedSingthonThreadPool.getInstallce();
        executorServicePool.run(() -> {
            try {
                synchronized (this) {
                    for (int i = 0; i < digitalList.size(); i = i + 2) {
                        if (Objects.equals(this.lock, 1)) {
                            this.wait();
                        }
                        printList(digitalList.subList(i, i + 2));
                        this.lock = 1;
                        this.notify();
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        executorServicePool.run(() -> {
            try {
                synchronized (this) {
                    for (int i = 0; i < alphabetList.size(); i++) {
                        if (Objects.equals(this.lock, 2)) {
                            this.wait();
                        }
                        printList(alphabetList.subList(i, i + 1));
                        this.lock = 2;
                        this.notify();
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
    }

    public static void main(String[] args) {
        ThraedWheelTrainingPrintV2 printV2 = new ThraedWheelTrainingPrintV2();

        List<String> alphabetList = printV2.getAlphabet(25);
        List<Integer> digitalList = printV2.getDigital(52);

        printV2.go(digitalList, alphabetList);


    }

}