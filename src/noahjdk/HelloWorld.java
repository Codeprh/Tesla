package noahjdk;

/**
 * 描述:
 *
 * @author Noah
 * @create 2020-02-15 23:22
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Noah：" + "Pheobe，你想学些代码");
        System.out.println("Pheobe：" + "yes");
        System.out.println("第一课：使用递归求解【斐波那契数列】1+1+2+3+5+8+13+21");
        System.out.println("Noah：" + "请求输入n="+Fibonacci1(15));
    }

    public static int Fibonacci1(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return Fibonacci1(n - 1) + Fibonacci1(n - 2);
    }
}
