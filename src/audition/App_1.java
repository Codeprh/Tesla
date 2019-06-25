package audition;

/**
 * @author codingprh
 * @create 2019-06-25 09:44
 */
public class App_1 {
    public static void main(String[] args) {
        boolean isNull = true;
        System.out.println("12" + (isNull ? getValue(isNull) : 0));
    }

    private static Integer getValue(boolean isNull) {
        return isNull ? null : 0;
    }
}
