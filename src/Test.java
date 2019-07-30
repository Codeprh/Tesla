public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.test());
    }

    public int test() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        } finally {
        }
        return 0;
    }
}
