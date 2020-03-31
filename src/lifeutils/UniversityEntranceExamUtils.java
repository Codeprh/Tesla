package lifeutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 描述:
 * 计算高考倒计时工具
 *
 * @author Noah
 * @create 2020-03-15 22:14
 */
public class UniversityEntranceExamUtils {
    public static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) {
        System.out.println("距离高考还有多长时间=" + getHaveDay());
    }

    public static String getCurYear() {
        String gk_time2 = calendar.get(Calendar.YEAR) + "/6/8 00:00:00";
        long current_mills = System.currentTimeMillis();
        long gk_time2_mills;
        int year = 0;
        try {
            gk_time2_mills = sdf.parse(gk_time2).getTime();
            if (current_mills <= gk_time2_mills) {
                year = calendar.get(Calendar.YEAR);
            } else if (current_mills > gk_time2_mills) {
                year = calendar.get(Calendar.YEAR) + 1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return year + "";
    }

    public static ArrayList<Integer> getHaveDay() {
        String gk_time1 = calendar.get(Calendar.YEAR) + "/6/7 00:00:00";
        String gk_time2 = calendar.get(Calendar.YEAR) + "/6/8 00:00:00";
        String gk_time3 = (calendar.get(Calendar.YEAR) + 1) + "/6/7 00:00:00";
        List<Integer> list = new ArrayList<Integer>();
        long gk_time1_mills;
        long gk_time2_mills;
        long gk_time3_mills;
        long current_mills = System.currentTimeMillis();
        int days = 0;
        long dayMills = 1000 * 60 * 60 * 24;
        try {
            gk_time1_mills = sdf.parse(gk_time1).getTime();
            gk_time2_mills = sdf.parse(gk_time2).getTime();
            gk_time3_mills = sdf.parse(gk_time3).getTime();
            if (current_mills < gk_time1_mills) {
                days = (int) ((gk_time1_mills - current_mills) / dayMills);
            } else if (current_mills >= gk_time1_mills
                    && current_mills <= gk_time2_mills) {
                days = 0;
            } else if (current_mills > gk_time2_mills) {
                days = (int) ((gk_time3_mills - current_mills) / dayMills);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (days < 10) {
            list.add(days);// 个
            list.add(0);// 十
            list.add(0);// 百
        } else if (days >= 10 && days < 100) {
            list.add(days % 10);
            list.add(days / 10 % 10);
            list.add(0);
        } else if (days >= 100) {
            list.add(days % 10);
            list.add(days / 10 % 10);
            list.add((days / 100) % 10);
        }
        return (ArrayList<Integer>) list;
    }
}
