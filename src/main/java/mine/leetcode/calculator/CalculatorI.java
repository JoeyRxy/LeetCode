package mine.leetcode.calculator;

/**
 * + - ()
 */
public class CalculatorI {

    public int calculate(String s) {
        s = "(" + s + ")";
        return helper(s.substring(1)).res;
    }

    class Pair {
        Pair(int res, int len) {
            this.res = res;
            this.len = len;
        }

        int res;
        int len;
    }

    private Pair helper(String s) {
        char c;
        int res = 0;
        char op = '+';
        int i = 0;
        for (; (c = s.charAt(i)) != ')'; ++i) {
            if (c == ' ')
                continue;
            if (Character.isDigit(c)) {
                int x = 0;
                do {
                    x = x * 10 + Character.digit(s.charAt(i++), 10);
                } while (Character.isDigit(s.charAt(i)));
                if (op == '+')
                    res += x;
                else
                    res -= x;
                --i;
            } else if (c == '(') {
                Pair pair = helper(s.substring(i + 1));
                if (op == '+')
                    res += pair.res;
                else
                    res -= pair.res;
                i += pair.len;
            } else
                op = c;
        }
        return new Pair(res, i + 1);
    }

    public static void main(String[] args) {
        String s = "(1+(4+5+2)-3)+(6+8)";
        int res = new CalculatorI().calculate(s);
        System.out.println(res);
    }
}