import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);
        String str = x.nextLine();
        char[] arrs = str.toCharArray();
        System.out.println((char) (arrs[0] + 3));
    }
}
