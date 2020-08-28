package lifeutils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * 描述:
 * 工具类
 *
 * @author Noah
 * @create 2020-08-05 5:55 下午
 */
public class FileUTisl {
    public static void main(String[] args) {

        try {
            String path = "/Users/codingprh/data/irms-log/report-service/08-03/sqlconsole.log";
            try (Stream<String> lines = Files.lines(Paths.get(path))) {

                List<String> list = lines.collect(Collectors.toList());

                boolean start = false;
                String oldLine = new String();

                for (String l : list) {

                    if (l.contains("insert into report_rider_merge_order_summary_new (assigned_order_count, city,")) {
                        l = l.substring(l.indexOf("insert"));
                        start = true;

                    }
                    if (l.contains(" {FAILED after")) {
                        start = false;
                        Files.write(Paths.get("large.txt"), Arrays.asList(" ; " + System.getProperty("line.separator")), UTF_8, CREATE, StandardOpenOption.APPEND);
                    }


                    if (start) {
                        Files.write(Paths.get("large.txt"), Arrays.asList(l), UTF_8, CREATE, StandardOpenOption.APPEND);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
