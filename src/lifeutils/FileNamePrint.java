package lifeutils;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 * 描述:
 * 打印文件夹下面所有文件名
 *
 * @author Noah
 * @create 2020-02-01 08:30
 */
public class FileNamePrint {

    public static void main(String[] args) {
        printName(null);
    }

    /**
     * 只匹配pdf&&数字开头的文件名
     *
     * @param path
     * @return
     */
    public static List<String> printName(String path) {

        File f = new File("/Users/codingprh/Documents/mac /百度云资料/46-Linux性能优化实战（完结）/pdf/");
        File[] listFiles = f.listFiles();

        String regular = "[0-9]*";

        TreeMap<Integer, String> map = new TreeMap<Integer, String>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        for (File ff : listFiles) {
            String name = ff.getName().trim();

            if (name.endsWith(".pdf") && Pattern.matches(regular, name.charAt(0) + "")) {
                map.put(Integer.parseInt(name.substring(0, 2)), name);

            }
        }
        for (String name : map.values()) {
            System.out.println(name);
        }
        return null;
    }
}
