package javaOffer._1;

/**
 * 描述:
 * java内存模型
 *
 * @author Noah
 * @create 2020-02-23 15:50
 */
public class ModelSample {

    private String name;

    public void sayHello() {
        System.out.println("hello," + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        int a = 1;
        ModelSample ms = new ModelSample();
        ms.setName("aa");
        ms.sayHello();
    }
}
