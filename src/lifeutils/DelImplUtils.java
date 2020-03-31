package lifeutils;

import java.io.File;

/**
 * 描述:
 * 删除impl文件工具类
 *
 * @author Noah
 * @create 2020-03-31 15:23
 */
public class DelImplUtils {
    public static void main(String[] args) {

        File root = new File("/Users/codingprh/IdeaProjects/iRMS_V2/");

        String s1 = "git rm --cached --force ";
        String s2 = "rm ";

        File[] files = root.listFiles((dir, name) -> {

            if (name.contains("irms")) {

                return true;
            } else {
                return false;
            }

        });

        for (File f : files) {

            f.listFiles((d2, n2) -> {

                if (n2.contains(".iml")) {

                    String c = d2.getName() + "/" + n2;
                    System.out.println(s1 + c);
                    System.out.println(s2 + c);
                    return true;
                }
                return false;
            });
        }
    }
}
