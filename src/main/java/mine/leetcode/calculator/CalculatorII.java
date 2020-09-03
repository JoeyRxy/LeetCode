package mine.leetcode.calculator;

/**
 * + - * /
 */
public class CalculatorII {

    public int calculate(String s) {
        char c, prev;
        for (int i = 0; i < s.length(); ++i) {
            c = s.charAt(i);
            if (Character.isDigit(c)) {
                int x = 0;
                do {
                    x = x * 10 + Character.digit(c, 10);
                    c = s.charAt(i++);
                } while (Character.isDigit(c));
                --i;
                // todo
            } else {
                switch (c) {
                    case '+':
                    case '-':
                }
            }

        }
    }

}