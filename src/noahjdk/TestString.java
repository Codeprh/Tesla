package noahjdk;

/**
 * 描述:
 * ceshi
 *
 * @author Noah
 * @create 2020-02-13 18:52
 */
public class TestString {

    public static void main(String[] args) {
        String a = "a";
        for (int i = 0; i < 1000; i++) {
            a = a + i;
        }

        System.out.println(a);
    }


}
