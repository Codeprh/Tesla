package leetcode;

/**
 * 买卖股票的最佳时机
 *
 * @author Noah
 * @create 2019-07-30 09:58
 */
//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
//
// 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
//
// 注意你不能在买入股票前卖出股票。
//
// 示例 1:
//
// 输入: [7,1,5,3,6,4]
//输出: 5
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
//
//
// 示例 2:
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
//
//
public class _121 {
    /**
     * 暴力法
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {

        int tempval = 0;

        for (int i = 0; i < prices.length; i++) {
            int a = prices[i];
            //买入价格:a

            for (int j = i + 1; j < prices.length; j++) {
                //卖出价格
                if (prices[j] <= a) {
                    continue;
                }

                if (prices[j] - a > tempval) {
                    tempval = prices[j] - a;
                }
            }
        }

        return tempval;
    }

    /**
     * 一次迭代
     *
     * @param prices
     * @return
     */
    public int maxProfit_v2(int[] prices) {

        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;

        for (int i = 0; i < prices.length; i++) {

            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        _121 app = new _121();
        int[] arrs = new int[]{2, 12, 1, 10};
        System.out.println(app.maxProfit_v2(arrs));
    }
}
