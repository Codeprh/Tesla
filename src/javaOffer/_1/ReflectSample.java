package javaOffer._1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 描述:
 * Java反射栗子
 *
 * @author Noah
 * @create 2020-02-23 09:17
 */
public class ReflectSample {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        //获取字节码
        Class rc = Class.forName("javaOffer._1.ReflectRobot");
        ReflectRobot r = (ReflectRobot) rc.newInstance();

        //java.lang.Class.getDeclaredMethod，可以获取本类的所有方法（public、private、static）。但是不能获取继承的方法和实现接口的方法
        Method method = rc.getDeclaredMethod("throwHello", String.class);
        method.setAccessible(true);
        Object o = method.invoke(r, "bob");
        System.out.println("通过反射调用实例私有方法=" + o);

        //获取和设置私有属性
        Field declaredField = rc.getDeclaredField("name");
        declaredField.setAccessible(true);
        declaredField.set(r, "noah");

        //java.lang.Class.getMethod，只能获取本类共有的方法，也可以获取继承或者实现的共有方法
        Method sayHi = rc.getMethod("sayHi", String.class);
        sayHi.invoke(r, "welcome");

        System.out.println("class name is=" + rc.getName());
    }
}
