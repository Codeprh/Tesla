package geektime_datastructure_algorithm._29;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 描述:
 * <p>
 * 使用堆求解topk问题
 * <p>
 * 动态数据:在100个数据当中求top 10大的数据
 * <p>
 * 静态数据也使用该方法求解
 *
 * @author Noah
 * @create 2019-11-18 14:07
 */
public class _29_TopK {

    public static final int MAX_HEAP = 5;

    public static int[] arr = new int[MAX_HEAP + 1];
    public static int count = 0;


    public static void main(String[] args) {

        Random random = new Random();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {

            int a = random.nextInt(100);
            list.add(a);
            buildHeap(a);
        }
        Object[] aa = list.toArray();
        Arrays.sort(aa);
        System.out.println(Arrays.toString(aa));
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 构建一个大小${MAX_HEAP}的小顶堆，数组从下标1开始存储数据
     */
    public static void buildHeap(int a) {

        //堆中没有数据
        if (arr == null || count < MAX_HEAP) {
            insert(a);
            return;
        }

        if (arr[1] >= a) {
            return;
        } else {
            //1、移除堆顶元素。2、插入元素
            removeMin();
            insert(a);

        }

    }

    public static void insert(int a) {
        ++count;
        arr[count] = a;

        //自下而上堆化
        int i = count;
        while (i / 2 > 0 && arr[i] < arr[i / 2]) { // 自下往上堆化
            swap(arr, i, i / 2); // swap()函数作用:交换下标为i和i/2的两个元素
            i = i / 2;
        }

    }

    /**
     * 移除堆顶元素
     *
     * @return
     */
    public static int removeMin() {

        int temp = arr[1];

        //把最后元素移动到堆顶，然后自上而下堆化
        arr[1] = arr[count];
        //arr[count] = 0;
        --count;

        heapfiy();
        return temp;
    }

    /**
     * 自上而下堆化
     */
    public static void heapfiy() {

        int i = 1;
        while (true) {

            int minPos = i;

            if (i * 2 <= count && arr[minPos] > arr[i * 2]) {
                minPos = i * 2;
            }

            if (i * 2 + 1 <= count && arr[minPos] > arr[i * 2 + 1]) {
                minPos = i * 2 + 1;
            }

            if (minPos == i) {
                break;
            }

            swap(arr, minPos, i);
            i = minPos;

        }
    }


    public static void swap(int[] arr, int i, int pi) {
        int temp = arr[i];
        arr[i] = arr[pi];
        arr[pi] = temp;
    }
}
