package swordFingerOffer;

/**
 * 数值的整数次方
 * <p>
 * 题目描述：给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent，求 base 的 exponent 次方。
 * <p>
 * 解决方案：下面的讨论中 x 代表 base，n 代表 exponent。
 * 因为 (x*x)n/2 可以通过递归求解，并且每次递归 n 都减小一半，因此整个算法的时间复杂度为 O(logN)。
 */
public class d12_NumericalIntegerPower {
    /**
     * 标准答案
     *
     * @param base
     * @param exponent
     * @return
     */
    public double Power(double base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;
        boolean isNegative = false;
        if (exponent < 0) {
            exponent = -exponent;
            isNegative = true;
        }
        double pow = Power(base * base, exponent / 2);
        if (exponent % 2 != 0)
            pow = pow * base;
        return isNegative ? 1 / pow : pow;
    }

    public double getBaseUponExponent(double base, int exponent) {
        if (base == 0) {
            return 0;
        }
        if (exponent == 1) {
            return base;
        }
        if (exponent == 0) {
            return 1;
        }
        boolean isNegative = false;
        if (exponent < 0) {
            exponent = -exponent;
            isNegative = true;
        }
        double pom = getBaseUponExponent(base * base, exponent / 2);
        if (exponent % 2 != 0) {//奇数次
            pom = pom * base;
        }
        return isNegative ? 1 / pom : pom;
    }

    public static void main(String[] args) {
        d12_NumericalIntegerPower app = new d12_NumericalIntegerPower();
        System.out.println(app.getBaseUponExponent(2, 5));

    }

}
