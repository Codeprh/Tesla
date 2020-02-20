package noahjdk;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author Noah
 * @create 2020-02-04 15:22
 */
public class Test {

    public static void main(String[] args) {

        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<Integer, Integer>();
        Map<Integer, Integer> map = new HashMap<>();


        for (int i = 0; i < 10; i++) {
            linkedHashMap.put(i, i);
            map.put(i, i);
        }

        linkedHashMap.forEach((Object o1, Object o2) -> {
            System.out.println("key=" + o1.toString() + "value=" + o2.toString());
        });
        System.out.println("hashmap");
        map.forEach((Object o1, Object o2) -> {
            System.out.println("key=" + o1.toString() + "value=" + o2.toString());
        });
    }
}
