package geektime_datastructure_algorithm._38;

/**
 * 描述:
 * 逆序度个数求解
 *
 * @author Noah
 * @create 2019-11-21 09:39
 */
public class _38_ReverseOrder {

    private int num = 0;

    public static void main(String[] args) {

        _38_ReverseOrder app = new _38_ReverseOrder();
        int[] a = new int[]{2, 4, 3, 1, 5, 6};
        System.out.println("逆序对的个数=" + app.count(a, a.length));
    }


    public int count(int[] a, int n) {
        num = 0;
        mergeSortCounting(a, 0, n - 1);
        return num;
    }

    private void mergeSortCounting(int[] a, int p, int r) {
        if (p >= r) return;
        int q = (p + r) / 2;
        mergeSortCounting(a, p, q);
        mergeSortCounting(a, q + 1, r);
        merge(a, p, q, r);
    }

    private void merge(int[] a, int p, int q, int r) {
        int i = p, j = q + 1, k = 0;
        int[] tmp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                // 统计p-q之间，比a[j]大的元素个数
                num += (q - i + 1);
                tmp[k++] = a[j++];
            }
        }
        while (i <= q) {
            // 处理剩下的
            tmp[k++] = a[i++];
        }
        while (j <= r) {
            // 处理剩下的
            tmp[k++] = a[j++];
        }
        for (i = 0; i <= r - p; ++i) {
            // 从tmp拷⻉回a
            a[p + i] = tmp[i];
        }
    }
}
