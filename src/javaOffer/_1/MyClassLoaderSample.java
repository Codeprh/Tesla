package javaOffer._1;

import java.io.*;

/**
 * 描述:
 * 自定义classLoader
 *
 * @author Noah
 * @create 2020-02-23 10:19
 */
public class MyClassLoaderSample extends ClassLoader {

    private String path;
    private String classLoaderName;

    public MyClassLoaderSample(String path, String classLoaderName) {
        this.path = path;
        this.classLoaderName = classLoaderName;
    }

    /**
     * 查找字节码(.class)
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        return defineClass(b, 0, b.length);
    }

    /**
     * 加载字节码，转换为byte[]
     *
     * @param name
     * @return
     */
    private byte[] loadClassData(String name) {

        name = path + name + ".class";

        InputStream in = null;
        ByteArrayOutputStream out = null;

        try {

            in = new FileInputStream(new File(name));
            out = new ByteArrayOutputStream();

            int i = -1;
            while ((i = in.read()) != -1) {
                out.write(i);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return out.toByteArray();

    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        MyClassLoaderSample classLoaderSample = new MyClassLoaderSample("/Users/codingprh/Desktop/jdk/", "noahClassLoader");

        Class<?> wali = classLoaderSample.loadClass("Wali");
        wali.newInstance();

        System.out.println(wali.getClassLoader());

    }
}
