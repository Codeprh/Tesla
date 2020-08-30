package swordFingerOffer.book;

/**
 * 描述:
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
 *
 * @author Noah
 * @create 2020-08-30 4:03 下午
 */
public class p58_reverseWords {

    public static void main(String[] args) {

        String str = new String("");

        p58_reverseWords app = new p58_reverseWords();
        System.out.println(app.reverseWords(str));
    }

    /**
     * 也是使用api版本
     *
     * @param s
     * @return
     */
    public String reverseWords_right(String s) {
        String[] strs = s.trim().split(" "); // 删除首尾空格，分割字符串
        StringBuilder res = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) { // 倒序遍历单词列表
            if (strs[i].equals("")) continue; // 遇到空单词则跳过
            res.append(strs[i] + " "); // 将单词拼接至 StringBuilder
        }
        return res.toString().trim(); // 转化为字符串，删除尾部空格，并返回
    }


    /**
     * noah版本：api版本
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {

        if (s.equals("")) {
            return s.trim();
        }

        String[] split = s.replace("  ", " ").split(" ");
        StringBuffer sb = new StringBuffer();

        if (split == null || split.length <= 0) {
            return "";
        }

        for (int i = split.length - 1; i >= 0; i--) {
            if (split[i].equals("")) {
                continue;
            }
            sb.append(split[i].trim()).append(" ");
        }

        return sb.substring(0, sb.lastIndexOf(" "));
    }

}
