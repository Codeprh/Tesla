package swordFingerOffer;

/**
 * 描述:
 * 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * @author Noah
 * @create 2020-08-19 7:16 上午
 */
public class d6_RotateArrayMinValue {

        public int minNumberInRotateArray(int [] array) {
            if(array.length == 0) return 0;
            int hi = array.length-1, lo = 0;
            while(lo <= hi){
                int mid = lo + (hi - lo) / 2;
                if(array[mid] > array[hi]){
                    if(array[mid] > array[mid+1])
                        return array[mid+1];
                    lo = mid + 1;
                }
                //else if(array[mid] == array[lo])
                else if(array[mid] < array[lo]){
                    if(array[mid] < array[mid-1])
                        return array[mid];
                    hi = mid - 1;
                }
            }
            return 0;
        }

}
