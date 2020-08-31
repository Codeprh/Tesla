package swordFingerOffer.book;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 描述:
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 *
 * @author Noah
 * @create 2020-08-30 8:15 下午
 */
public class p61_isStraight {

    public static void main(String[] args) {

        p61_isStraight app = new p61_isStraight();

        int[] arrs = new int[]{0, 0, 1, 2, 5};
        System.out.println(app.isStraight_right2(arrs));

    }

    /**
     * 排序+遍历
     * @param nums
     * @return
     */
    public boolean isStraight_right2(int[] nums) {
        int joker = 0;
        Arrays.sort(nums); // 数组排序
        for (int i = 0; i < 4; i++) {
            if (nums[i] == 0) joker++; // 统计大小王数量
            else if (nums[i] == nums[i + 1]) return false; // 若有重复，提前返回 false
        }
        return nums[4] - nums[joker] < 5; // 最大牌 - 最小牌 < 5 则可构成顺子

    }

    /**
     * 1、除大小王外，所有牌 无重复 ；
     * 2、max−min<5
     *
     * @param nums
     * @return
     */
    public boolean isStraight_right(int[] nums) {

        Set<Integer> repeat = new HashSet<>();
        int max = 0, min = 14;

        for (int num : nums) {

            if (num == 0) {
                // 跳过大小王
                continue;
            }
            // 最大牌
            max = Math.max(max, num);
            // 最小牌
            min = Math.min(min, num);

            if (repeat.contains(num)) {
                // 若有重复，提前返回 false
                return false;
            }

            // 添加此牌至 Set
            repeat.add(num);
        }

        // 最大牌 - 最小牌 < 5 则可构成顺子
        return max - min < 5;
    }


    /**
     * noah版本：判断是不是递增数组
     *
     * @param nums
     * @return
     */
    public boolean isStraight(int[] nums) {

        boolean r = true;

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] < nums[i - 1]) {
                r = false;
            }
        }

        return r;
    }
}
