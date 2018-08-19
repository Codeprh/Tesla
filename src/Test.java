public class Test {

    public static void main(String[] args) {
        String st2 = "1java";
        String str1 = new StringBuilder("1").append("java").toString();
        System.out.println(str1.intern() == str1);

    }
}
