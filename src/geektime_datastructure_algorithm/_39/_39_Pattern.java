package geektime_datastructure_algorithm._39;

/**
 * 描述:
 * 利用回溯算法实现正则表达式匹配
 * <p>
 * “*”匹配任意多个(大于等于0个)任意字 符，“?”匹配零个或者一个任意字符
 *
 * @author Noah
 * @create 2019-11-25 09:14
 */
public class _39_Pattern {
    private boolean matched = false;
    private char[] pattern; // 正则表达式
    private int plen; // 正则表达式⻓度

    public _39_Pattern(char[] pattern, int plen) {
        this.pattern = pattern;
        this.plen = plen;
    }

    public boolean match(char[] text, int tlen) {
        matched = false;

        rmatch(0, 0, text, tlen);
        return matched;
    }

    private void rmatch(int ti, int pj, char[] text, int tlen) {
        if (matched) return; // 如果已经匹配了，就不要继续递归了
        if (pj == plen) { // 正则表达式到结尾了
            if (ti == tlen) matched = true; // 文本串也到结尾了
            return;
        }
        if (pattern[pj] == '*') { // *匹配任意个字符
            for (int k = 0; k <= tlen - ti; ++k) {
                rmatch(ti + k, pj + 1, text, tlen);
            }
        } else if (pattern[pj] == '?') { // ?匹配0个或者1个字符
            rmatch(ti, pj + 1, text, tlen);
            rmatch(ti + 1, pj + 1, text, tlen);
        } else if (ti < tlen && pattern[pj] == text[ti]) { // 纯字符匹配才行
            rmatch(ti + 1, pj + 1, text, tlen);
        }
    }

}
