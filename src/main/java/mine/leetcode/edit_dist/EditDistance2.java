package mine.leetcode.edit_dist;

/**
 * 前i个和前j个，这种思考方式好像很常见？！
 * <p>
 * word1的前i个变成word2的前j个最少的步骤设为f[i,j].
 * <p>
 * if word1[i] == word2[j] : f[i,j] = f[i-1,j-1];
 * <p>
 * else min{ f[i-1,j-1]+1,//更改i f[i-1,j]+1,//删除i f[i,j-1]+1//增加i }
 */
public class EditDistance2 {

    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if(len1 * len2 == 0) return len1 + len2;
        byte[] b1 = word1.getBytes();
        byte[] b2 = word2.getBytes();
        int[][] f = new int[len1+1][len2+1];
        for (int i = 0; i <= len1; i++) {
            f[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            f[0][j] = j;
        }
        for (int i = 1; i <= len1; i++)
            for (int j = 1; j <= len2; j++)
                f[i][j] = Math.min(
                    f[i - 1][j - 1] + (b1[i - 1] == b2[j - 1] ? 0 : 1), //update b1[i] to b2[j]
                    Math.min(
                        f[i - 1][j], //add i in b1 with val b2[j]
                        f[i][j - 1] // del i in b1
                        ) + 1);
        return f[len1][len2];
    }

    public static void main(String[] args) {
        String word1 = "horse", word2 = "ros";
        // String word1 = "intention", word2 = "execution";
        int attemption = new EditDistance2().minDistance(word1, word2);
        System.out.println(attemption);
    }
}