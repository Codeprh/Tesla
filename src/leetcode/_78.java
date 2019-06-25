package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author codingprh
 * @create 2019-06-22 07:02
 */
public class _78 {
    /**
     * 迭代法1
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        ans.add(new ArrayList<Integer>());
        res.add(new ArrayList<Integer>());
        int n = nums.length;
        // 第一层循环，子数组长度从 1 到 n
        for (int i = 1; i <= n; i++) {
            // 第二层循环，遍历上次的所有结果
            List<List<Integer>> tmp = new ArrayList<List<Integer>>();
            for (List<Integer> list : res) {
                // 第三次循环，对每个结果进行扩展
                for (int m = 0; m < n; m++) {
                    //只添加比末尾数字大的数字，防止重复
                    if (list.size() > 0 && list.get(list.size() - 1) >= nums[m]) {
                        continue;
                    }
                    List<Integer> newList = new ArrayList<Integer>(list);
                    newList.add(nums[m]);
                    tmp.add(newList);
                    ans.add(newList);
                    System.out.println(ans.toString());
                }
            }
            res = tmp;
        }
        return ans;
    }

    /**
     * 迭代法2
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets_v2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<Integer>());//初始化空数组
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> ans_tmp = new ArrayList<>();
            //遍历之前的所有结果
            for (List<Integer> list : ans) {
                List<Integer> tmp = new ArrayList<>(list);
                tmp.add(nums[i]); //加入新增数字
                ans_tmp.add(tmp);
                System.out.println(ans_tmp);
            }
            ans.addAll(ans_tmp);
        }
        return ans;
    }

    /**
     * 回溯法3
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets_v3(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        getAns(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private static void getAns(int[] nums, int start, ArrayList<Integer> temp, List<List<Integer>> ans) {

        ans.add(new ArrayList<>(temp));
        System.out.println(ans.toString());
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            getAns(nums, i + 1, temp, ans);
            System.out.println("第" + i + "层递归中");
            temp.remove(temp.size() - 1);
        }

    }

    /**
     * 位计算
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets_v4(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int bit_nums = nums.length;
        int ans_nums = 1 << bit_nums; //执行 2 的 n 次方
        for (int i = 0; i < ans_nums; i++) {
            List<Integer> tmp = new ArrayList<>();
            int count = 0; //记录当前对应数组的哪一位
            int i_copy = i; //用来移位
            while (i_copy != 0) {
                if ((i_copy & 1) == 1) { //判断当前位是否是 1
                    tmp.add(nums[count]);
                }
                count++;
                i_copy = i_copy >> 1;//右移一位
            }
            ans.add(tmp);
            System.out.println(tmp);

        }
        return ans;
    }


    public static void main(String[] args) {
        int[] testArr = {3, 2, 4, 1};
        subsets_v4(testArr);
    }
}
