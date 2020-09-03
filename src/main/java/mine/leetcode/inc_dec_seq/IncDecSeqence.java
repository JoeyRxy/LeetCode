package mine.leetcode.inc_dec_seq;

import java.util.Scanner;

/**
 * <div class="main-martor main-martor-content" data-field-name="content">
 * <div class="section-martor">
 * <div class="ui bottom attached tab active martor-preview" data-tab=
 * "preview-tab-content">
 * <p>
 * 给定一个长度为
 * <span class="MathJax_Preview" style="color: inherit;"></span><span class=
 * "MathJax" id="MathJax-Element-1-Frame" tabindex="0" style="position:
 * relative;" data-mathml="<math
 * xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><mi>n</mi></math>" role=
 * "presentation"><nobr aria-hidden="true"><span class="math" id=
 * "MathJax-Span-1" role="math" style="width: 0.784em; display:
 * inline-block;"><span style="display: inline-block; position: relative; width:
 * 0.628em; height: 0px; font-size: 120%;"><span style="position: absolute;
 * clip: rect(1.565em, 1000.63em, 2.294em, -999.997em); top: -2.133em; left:
 * 0.003em;"><span class="mrow" id="MathJax-Span-2"><span class="mi" id=
 * "MathJax-Span-3" style="font-family:
 * MathJax_Math-italic;">n</span></span><span style="display: inline-block;
 * width: 0px; height: 2.138em;"></span></span></span><span style="display:
 * inline-block; overflow: hidden; vertical-align: -0.059em; border-left: 0px
 * solid; width: 0px; height: 0.691em;"></span></span></nobr><span class=
 * "MJX_Assistive_MathML" role="presentation"><math xmlns=
 * "http://www.w3.org/1998/Math/MathML"><mi>n</mi></math></span></span><script
 * type="math/tex" id="MathJax-Element-1">n</script> 的数列
 * <span class="MathJax_Preview" style="color: inherit;"></span><span class=
 * "MathJax" id="MathJax-Element-2-Frame" tabindex="0" style="position:
 * relative;" data-mathml="<math
 * xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><mrow
 * class=&quot;MJX-TeXAtom-ORD&quot;><msub><mi>a</mi><mn>1</mn></msub><mo>,</mo><msub><mi>a</mi><mn>2</mn></msub><mo>,</mo><mo>&amp;#x2026;</mo><mo>,</mo><msub><mi>a</mi><mi>n</mi></msub></mrow></math>"
 * role="presentation"><nobr aria-hidden="true"><span class="math" id=
 * "MathJax-Span-4" role="math" style="width: 6.721em; display:
 * inline-block;"><span style="display: inline-block; position: relative; width:
 * 5.576em; height: 0px; font-size: 120%;"><span style="position: absolute;
 * clip: rect(1.617em, 1005.58em, 2.555em, -999.997em); top: -2.185em; left:
 * 0.003em;"><span class="mrow" id="MathJax-Span-5"><span class="texatom" id=
 * "MathJax-Span-6"><span class="mrow" id="MathJax-Span-7"><span class="msubsup"
 * id="MathJax-Span-8"><span style="display: inline-block; position: relative;
 * width: 0.94em; height: 0px;"><span style="position: absolute; clip:
 * rect(3.44em, 1000.52em, 4.169em, -999.997em); top: -4.008em; left:
 * 0.003em;"><span class="mi" id="MathJax-Span-9" style="font-family:
 * MathJax_Math-italic;">a</span><span style="display: inline-block; width: 0px;
 * height: 4.013em;"></span></span><span style="position: absolute; top:
 * -3.852em; left: 0.523em;"><span class="mn" id="MathJax-Span-10" style=
 * "font-size: 70.7%; font-family: MathJax_Main;">1</span><span style="display:
 * inline-block; width: 0px; height: 4.013em;"></span></span></span></span><span
 * class="mo" id="MathJax-Span-11" style="font-family:
 * MathJax_Main;">,</span><span class="msubsup" id="MathJax-Span-12" style=
 * "padding-left: 0.159em;"><span style="display: inline-block; position:
 * relative; width: 0.94em; height: 0px;"><span style="position: absolute; clip:
 * rect(3.44em, 1000.52em, 4.169em, -999.997em); top: -4.008em; left:
 * 0.003em;"><span class="mi" id="MathJax-Span-13" style="font-family:
 * MathJax_Math-italic;">a</span><span style="display: inline-block; width: 0px;
 * height: 4.013em;"></span></span><span style="position: absolute; top:
 * -3.852em; left: 0.523em;"><span class="mn" id="MathJax-Span-14" style=
 * "font-size: 70.7%; font-family: MathJax_Main;">2</span><span style="display:
 * inline-block; width: 0px; height: 4.013em;"></span></span></span></span><span
 * class="mo" id="MathJax-Span-15" style="font-family:
 * MathJax_Main;">,</span><span class="mo" id="MathJax-Span-16" style=
 * "font-family: MathJax_Main; padding-left: 0.159em;">…</span><span class="mo"
 * id="MathJax-Span-17" style="font-family: MathJax_Main; padding-left:
 * 0.159em;">,</span><span class="msubsup" id="MathJax-Span-18" style=
 * "padding-left: 0.159em;"><span style="display: inline-block; position:
 * relative; width: 1.044em; height: 0px;"><span style="position: absolute;
 * clip: rect(3.44em, 1000.52em, 4.169em, -999.997em); top: -4.008em; left:
 * 0.003em;"><span class="mi" id="MathJax-Span-19" style="font-family:
 * MathJax_Math-italic;">a</span><span style="display: inline-block; width: 0px;
 * height: 4.013em;"></span></span><span style="position: absolute; top:
 * -3.852em; left: 0.523em;"><span class="mi" id="MathJax-Span-20" style=
 * "font-size: 70.7%; font-family: MathJax_Math-italic;">n</span><span style=
 * "display: inline-block; width: 0px; height:
 * 4.013em;"></span></span></span></span></span></span></span><span style=
 * "display: inline-block; width: 0px; height:
 * 2.19em;"></span></span></span><span style="display: inline-block; overflow:
 * hidden; vertical-align: -0.309em; border-left: 0px solid; width: 0px; height:
 * 0.878em;"></span></span></nobr><span class="MJX_Assistive_MathML" role=
 * "presentation"><math xmlns="http://www.w3.org/1998/Math/MathML"><mrow class=
 * "MJX-TeXAtom-ORD"><msub><mi>a</mi><mn>1</mn></msub><mo>,</mo><msub><mi>a</mi><mn>2</mn></msub><mo>,</mo><mo>…</mo><mo>,</mo><msub><mi>a</mi><mi>n</mi></msub></mrow></math></span></span><script
 * type="math/tex" id="MathJax-Element-2">{a_1,a_2,…,a_n}</script>，每次可以选择一个区间
 * [l,r]，使下标在这个区间内的数都加一或者都减一。
 * </p>
 * <p>
 * 求至少需要多少次操作才能使数列中的所有数都一样，并求出在保证最少次数的前提下，最终得到的数列可能有多少种。
 * </p>
 * <h4>输入格式</h4>
 * <p>
 * 第一行输入正整数<span class="MathJax_Preview" style="color: inherit;"></span><span
 * class="MathJax" id="MathJax-Element-3-Frame" tabindex="0" style="position:
 * relative;" data-mathml="<math
 * xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><mi>n</mi></math>" role=
 * "presentation"><nobr aria-hidden="true"><span class="math" id=
 * "MathJax-Span-21" role="math" style="width: 0.784em; display:
 * inline-block;"><span style="display: inline-block; position: relative; width:
 * 0.628em; height: 0px; font-size: 120%;"><span style="position: absolute;
 * clip: rect(1.565em, 1000.63em, 2.294em, -999.997em); top: -2.133em; left:
 * 0.003em;"><span class="mrow" id="MathJax-Span-22"><span class="mi" id=
 * "MathJax-Span-23" style="font-family:
 * MathJax_Math-italic;">n</span></span><span style="display: inline-block;
 * width: 0px; height: 2.138em;"></span></span></span><span style="display:
 * inline-block; overflow: hidden; vertical-align: -0.059em; border-left: 0px
 * solid; width: 0px; height: 0.691em;"></span></span></nobr><span class=
 * "MJX_Assistive_MathML" role="presentation"><math xmlns=
 * "http://www.w3.org/1998/Math/MathML"><mi>n</mi></math></span></span><script
 * type="math/tex" id="MathJax-Element-3">n</script>。
 * </p>
 * <p>
 * 接下来<span class="MathJax_Preview" style="color: inherit;"></span><span class=
 * "MathJax" id="MathJax-Element-4-Frame" tabindex="0" style="position:
 * relative;" data-mathml="<math
 * xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><mi>n</mi></math>" role=
 * "presentation"><nobr aria-hidden="true"><span class="math" id=
 * "MathJax-Span-24" role="math" style="width: 0.784em; display:
 * inline-block;"><span style="display: inline-block; position: relative; width:
 * 0.628em; height: 0px; font-size: 120%;"><span style="position: absolute;
 * clip: rect(1.565em, 1000.63em, 2.294em, -999.997em); top: -2.133em; left:
 * 0.003em;"><span class="mrow" id="MathJax-Span-25"><span class="mi" id=
 * "MathJax-Span-26" style="font-family:
 * MathJax_Math-italic;">n</span></span><span style="display: inline-block;
 * width: 0px; height: 2.138em;"></span></span></span><span style="display:
 * inline-block; overflow: hidden; vertical-align: -0.059em; border-left: 0px
 * solid; width: 0px; height: 0.691em;"></span></span></nobr><span class=
 * "MJX_Assistive_MathML" role="presentation"><math xmlns=
 * "http://www.w3.org/1998/Math/MathML"><mi>n</mi></math></span></span><script
 * type="math/tex" id="MathJax-Element-4">n</script>行，每行输入一个整数，第i+1行的整数代表<span
 * class="MathJax_Preview" style="color: inherit;"></span><span class="MathJax"
 * id="MathJax-Element-5-Frame" tabindex="0" style="position: relative;"
 * data-mathml="<math
 * xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><msub><mi>a</mi><mi>i</mi></msub></math>"
 * role="presentation"><nobr aria-hidden="true"><span class="math" id=
 * "MathJax-Span-27" role="math" style="width: 1.044em; display:
 * inline-block;"><span style="display: inline-block; position: relative; width:
 * 0.836em; height: 0px; font-size: 120%;"><span style="position: absolute;
 * clip: rect(1.565em, 1000.84em, 2.451em, -999.997em); top: -2.133em; left:
 * 0.003em;"><span class="mrow" id="MathJax-Span-28"><span class="msubsup" id=
 * "MathJax-Span-29"><span style="display: inline-block; position: relative;
 * width: 0.836em; height: 0px;"><span style="position: absolute; clip:
 * rect(3.44em, 1000.52em, 4.169em, -999.997em); top: -4.008em; left:
 * 0.003em;"><span class="mi" id="MathJax-Span-30" style="font-family:
 * MathJax_Math-italic;">a</span><span style="display: inline-block; width: 0px;
 * height: 4.013em;"></span></span><span style="position: absolute; top:
 * -3.852em; left: 0.523em;"><span class="mi" id="MathJax-Span-31" style=
 * "font-size: 70.7%; font-family: MathJax_Math-italic;">i</span><span style=
 * "display: inline-block; width: 0px; height:
 * 4.013em;"></span></span></span></span></span><span style="display:
 * inline-block; width: 0px; height: 2.138em;"></span></span></span><span style=
 * "display: inline-block; overflow: hidden; vertical-align: -0.247em;
 * border-left: 0px solid; width: 0px; height:
 * 0.816em;"></span></span></nobr><span class="MJX_Assistive_MathML" role=
 * "presentation"><math xmlns=
 * "http://www.w3.org/1998/Math/MathML"><msub><mi>a</mi><mi>i</mi></msub></math></span></span><script
 * type="math/tex" id="MathJax-Element-5">a_i</script>。
 * </p>
 * <h4>输出格式</h4>
 * <p>
 * 第一行输出最少操作次数。
 * </p>
 * <p>
 * 第二行输出最终能得到多少种结果。
 * </p>
 * <h4>数据范围</h4>
 * <p>
 * <span class="MathJax_Preview" style="color: inherit;"></span><span class=
 * "MathJax" id="MathJax-Element-6-Frame" tabindex="0" style="position:
 * relative;" data-mathml="<math
 * xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><mn>0</mn><mo>&amp;lt;</mo><mi>n</mi><mo>&amp;#x2264;</mo><msup><mn>10</mn><mn>5</mn></msup></math>"
 * role="presentation"><nobr aria-hidden="true"><span class="math" id=
 * "MathJax-Span-32" role="math" style="width: 6.201em; display:
 * inline-block;"><span style="display: inline-block; position: relative; width:
 * 5.159em; height: 0px; font-size: 120%;"><span style="position: absolute;
 * clip: rect(1.148em, 1005.16em, 2.503em, -999.997em); top: -2.185em; left:
 * 0.003em;"><span class="mrow" id="MathJax-Span-33"><span class="mn" id=
 * "MathJax-Span-34" style="font-family: MathJax_Main;">0</span><span class="mo"
 * id="MathJax-Span-35" style="font-family: MathJax_Main; padding-left:
 * 0.263em;">&lt;</span><span class="mi" id="MathJax-Span-36" style=
 * "font-family: MathJax_Math-italic; padding-left: 0.263em;">n</span><span
 * class="mo" id="MathJax-Span-37" style="font-family: MathJax_Main;
 * padding-left: 0.263em;">≤</span><span class="msubsup" id="MathJax-Span-38"
 * style="padding-left: 0.263em;"><span style="display: inline-block; position:
 * relative; width: 1.409em; height: 0px;"><span style="position: absolute;
 * clip: rect(3.18em, 1000.94em, 4.169em, -999.997em); top: -4.008em; left:
 * 0.003em;"><span class="mn" id="MathJax-Span-39" style="font-family:
 * MathJax_Main;">10</span><span style="display: inline-block; width: 0px;
 * height: 4.013em;"></span></span><span style="position: absolute; top:
 * -4.424em; left: 0.992em;"><span class="mn" id="MathJax-Span-40" style=
 * "font-size: 70.7%; font-family: MathJax_Main;">5</span><span style="display:
 * inline-block; width: 0px; height:
 * 4.013em;"></span></span></span></span></span><span style="display:
 * inline-block; width: 0px; height: 2.19em;"></span></span></span><span style=
 * "display: inline-block; overflow: hidden; vertical-align: -0.247em;
 * border-left: 0px solid; width: 0px; height:
 * 1.316em;"></span></span></nobr><span class="MJX_Assistive_MathML" role=
 * "presentation"><math xmlns=
 * "http://www.w3.org/1998/Math/MathML"><mn>0</mn><mo>&lt;</mo><mi>n</mi><mo>≤</mo><msup><mn>10</mn><mn>5</mn></msup></math></span></span><script
 * type="math/tex" id="MathJax-Element-6">0 < n \le 10^5</script>,<br>
 * <span class="MathJax_Preview" style="color: inherit;"></span><span class=
 * "MathJax" id="MathJax-Element-7-Frame" tabindex="0" style="position:
 * relative;" data-mathml="<math
 * xmlns=&quot;http://www.w3.org/1998/Math/MathML&quot;><mn>0</mn><mo>&amp;#x2264;</mo><msub><mi>a</mi><mi>i</mi></msub><mo>&amp;lt;</mo><mn>2147483648</mn></math>"
 * role="presentation"><nobr aria-hidden="true"><span class="math" id=
 * "MathJax-Span-41" role="math" style="width: 10.784em; display:
 * inline-block;"><span style="display: inline-block; position: relative; width:
 * 8.961em; height: 0px; font-size: 120%;"><span style="position: absolute;
 * clip: rect(1.357em, 1008.91em, 2.503em, -999.997em); top: -2.185em; left:
 * 0.003em;"><span class="mrow" id="MathJax-Span-42"><span class="mn" id=
 * "MathJax-Span-43" style="font-family: MathJax_Main;">0</span><span class="mo"
 * id="MathJax-Span-44" style="font-family: MathJax_Main; padding-left:
 * 0.263em;">≤</span><span class="msubsup" id="MathJax-Span-45" style=
 * "padding-left: 0.263em;"><span style="display: inline-block; position:
 * relative; width: 0.836em; height: 0px;"><span style="position: absolute;
 * clip: rect(3.44em, 1000.52em, 4.169em, -999.997em); top: -4.008em; left:
 * 0.003em;"><span class="mi" id="MathJax-Span-46" style="font-family:
 * MathJax_Math-italic;">a</span><span style="display: inline-block; width: 0px;
 * height: 4.013em;"></span></span><span style="position: absolute; top:
 * -3.852em; left: 0.523em;"><span class="mi" id="MathJax-Span-47" style=
 * "font-size: 70.7%; font-family: MathJax_Math-italic;">i</span><span style=
 * "display: inline-block; width: 0px; height:
 * 4.013em;"></span></span></span></span><span class="mo" id="MathJax-Span-48"
 * style="font-family: MathJax_Main; padding-left: 0.263em;">&lt;</span><span
 * class="mn" id="MathJax-Span-49" style="font-family: MathJax_Main;
 * padding-left: 0.263em;">2147483648</span></span><span style="display:
 * inline-block; width: 0px; height: 2.19em;"></span></span></span><span style=
 * "display: inline-block; overflow: hidden; vertical-align: -0.247em;
 * border-left: 0px solid; width: 0px; height:
 * 1.128em;"></span></span></nobr><span class="MJX_Assistive_MathML" role=
 * "presentation"><math xmlns=
 * "http://www.w3.org/1998/Math/MathML"><mn>0</mn><mo>≤</mo><msub><mi>a</mi><mi>i</mi></msub><mo>&lt;</mo><mn>2147483648</mn></math></span></span><script
 * type="math/tex" id="MathJax-Element-7">0 \le a_i <2147483648</script>
 * </p>
 * <h4>输入样例：</h4>
 * 
 * <pre class="hljs">
 * <code>4
1
1
2
2
</code>
 * </pre>
 * 
 * <h4>输出样例：</h4>
 * 
 * <pre class="hljs">
 * <code>1
2
</code>
 * </pre>
 * 
 * </div> </div> </div>
 */
public class IncDecSeqence {

    private int[] a;
    private int val;

    public IncDecSeqence(int[] a) {
        this.a = a;
    }

    /**
     * dp[i, j, k] :[i, j] -> k所需的最少步数
     * <p>
     * k = [m...M]
     * <p>
     * dp[i, j, k] = dp(i+1, j, l) + abs(dict[k] - dict[l]) | dp(i, j-1, l) + abs(k
     * - l) -> min
     * <p>
     * TODO:WRONG
     * <p>
     * 
     */
    public int minOpt() {
        int m = Integer.MAX_VALUE, M = Integer.MIN_VALUE;
        for (int i : a) {
            if (m > i)
                m = i;
            if (M < i)
                M = i;
        }
        if (m == M) {
            val = 1;
            return 0;
        }
        int[] dict = new int[1 + (M - m)];
        for (int i = 0; i < dict.length; i++)
            dict[i] = m++;

        int[][][] dp = new int[a.length][a.length][dict.length];
        // init
        for (int i = 0; i < dp.length; i++)
            for (int k = 0; k < dict.length; k++)
                dp[i][i][k] = Math.abs(dict[k] - a[i]);
        // calc
        int _i, _j;
        for (int d = 1; d < dp.length; d++) {
            _i = 0;
            _j = d;
            while (_j < dp.length) {
                for (int k = 0; k < dict.length; k++) {
                    int min = Integer.MAX_VALUE;
                    for (int l = 0; l < dict.length && min > 0; l++)
                        min = Math.min(min,
                                Math.min(dp[_i + 1][_j][l], dp[_i][_j - 1][l]) + Math.abs(dict[k] - dict[l]));
                    dp[_i + 1][_j][k] = min;
                }
                _i++;
                _j++;
            }
        }
        int minopt = Integer.MAX_VALUE;
        for (int i = 0; i < dict.length; i++)
            if (minopt > dp[0][a.length - 1][i])
                minopt = dp[0][a.length - 1][i];
        for (int i = 0; i < dict.length; i++)
            if (minopt == dp[0][a.length - 1][i])
                val++;
        return minopt;
    }

    /**
     * @return the val
     */
    public int getVal() {
        return val;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        scanner.close();
        IncDecSeqence instance = new IncDecSeqence(a);
        System.out.println(instance.minOpt());
        System.out.println(instance.getVal());
    }
}