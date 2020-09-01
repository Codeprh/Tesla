package swordFingerOffer.book;

/**
 * 描述:
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 *
 * @author Noah
 * @create 2020-09-01 7:59 上午
 */
public class p11_minArray {
    public static void main(String[] args) {

    }

    /**
     * 官方解法：二分查找
     *
     * @param numbers
     * @return
     */
    public int minArray_right(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];
    }

    /**
     * noah版本：查找到下一个元素，小于当前元素。没有理解到题目的意思
     *
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {

        return 0;
    }
}
