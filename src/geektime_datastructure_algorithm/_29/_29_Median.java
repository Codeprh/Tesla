package geektime_datastructure_algorithm._29;

/**
 * 描述:
 * <p>
 * 中位数
 * <p>
 * 静态数据：直接排序然后n/2
 * <p>
 * 动态数据求解：维护两个堆，大顶堆(n/2+1~n)和小顶堆（n/2），但是小顶堆的元素都大于大顶堆的元素。中位数就是大顶堆的堆顶
 * n为奇数：大顶堆元素个数多一个。
 * n为偶数：大小顶堆相等
 *
 * @author Noah
 * @create 2019-11-18 16:09
 */
public class _29_Median {

    public static void main(String[] args) {
        int[] a = new int[]{2, 1, 3, 4, 6, 7};


    }

}
