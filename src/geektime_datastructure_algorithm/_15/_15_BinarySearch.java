package geektime_datastructure_algorithm._15;

import java.util.Random;

/**
 * 描述:
 * 二分查找：应用
 * <p>
 * 15讲二分查找（上）：如何用最省内存的方式实现快速查找功能：
 * <p>
 * 如何在1000万个整数中快速查找某个整数? 1000w整数消耗内存80MB
 *
 * @author Noah
 * @create 2019-10-17 15:54
 */
public class _15_BinarySearch {

    public static final int big1 = 1000 * 10000;

    int[] r = new int[big1];

    /**
     * 二分查找：返回数组下标
     *
     * @return
     */
    public int binarySearch(int value) {
        int low = 0;
        int high = r.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (r[mid] == value) {
                return mid;
            } else if (r[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;

    }

    /**
     * 排序1000w的数组：快速排序
     */
    public void sort() {
        quickSortInternally(r, 0, r.length - 1);
    }

    private void quickSortInternally(int[] a, int p, int r) {
        if (p >= r) return;

        int q = partition(a, p, r); // 获取分区点
        quickSortInternally(a, p, q - 1);
        quickSortInternally(a, q + 1, r);
    }

    private int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for (int j = p; j < r; ++j) {
            if (a[j] < pivot) {
                if (i == j) {
                    ++i;
                } else {
                    int tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                }
            }
        }

        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;
        return i;
    }

    /**
     * 生成随机1000w数字，可重复
     */
    public int[] productRandomNum() {

        Random random = new Random();

        for (int i = 0; i < big1; i++) {
            //生成0-100w的数字，存在长度为1000w的数组中。乱序
            r[i] = random.nextInt(big1 / 10);
        }

        return r;
    }

    //根据坐标获取值
    public int getVal(int index) {
        if (index > 0) {
            return r[index];
        }
        throw new RuntimeException("没有找到这个数字&&下标不能<0");
    }

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        _15_BinarySearch app = new _15_BinarySearch();

        app.productRandomNum();
        app.sort();
        int index = app.binarySearch(90000);
        System.out.println("在位置=" + index + "，找到数值=" + app.getVal(index));
        System.out.println("耗时" + (System.currentTimeMillis() - start) / 1000 + "s");


    }
}
