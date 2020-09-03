package mine.leetcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSONArray;

import mine.knowledge.seg_tree_binary_indexed_tree.SegmentTree2;

/**
 * https://leetcode-cn.com/problems/max-value-of-equation/
 */
public class MaxValueOfEquation {
    private int[][] points;
    private int k;

    public int findMaxValueOfEquation(int[][] points, int k) {
        this.points = points;
        this.k = k;
        return second();
    }

    /**
     * 等式具备的条件：
     * <ol>
     * <li>区间查询
     * <li>
     * </ol>
     * res_(l, r) = {@link #points}[r][0] - {@link #points}[l][0] +
     * {@link #points}[r][1] + {@link #points}[l][1] mid = (l + r) / 2 res_(l, mid)
     * =
     */
    private int second() {
        Integer[] vals = new Integer[points.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = points[i][1] - points[i][0];
        }
        SegmentTree2<Integer> tree = new SegmentTree2<>(vals, (x, y) -> {
            return x > y ? x : y;
        });
        int max = Integer.MIN_VALUE;
        int max_diff = points[points.length - 1][0] - points[0][0];
        for (int i = 0; i < vals.length - 1; i++) {
            int j = i + 1;
            if (max_diff <= k)
                j = vals.length;
            else
                while (j < vals.length && points[j][0] - points[i][0] <= k)
                    ++j;
            max = Math.max(max, points[j - 1][0] + points[j - 1][1] + tree.rangeQuery(i, j));
        }
        return max;
    }

    /*
     * private class SegmentTreeForPointVal { private int[][] points;
     * 
     * class Node { int l, r; int v; Node leftChild, rightChild;
     * 
     * Node(int l, int r) { this.l = l; this.r = r; v = -1; leftChild = null;
     * rightChild = null; } }
     * 
     * private Node root;
     * 
     * SegmentTreeForPointVal(int[][] points) { this.points = points; root = new
     * Node(0, points.length); build(root); }
     * 
     * private void build(Node node) { if (node.l + 1 == node.r) { node.v =
     * points[node.l][1] - points[node.l][0]; return; } int mid = (node.l + node.r)
     * >> 1; Node leftChild = new Node(node.l, mid); Node rightChild = new Node(mid,
     * node.r); build(leftChild); build(rightChild); node.v = Math.max(leftChild.v,
     * rightChild.v); }
     * 
     * }
     */

    private int first() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < points.length - 1; i++) {
            max = Math.max(max, firstHelper(i));
        }
        return max;
    }

    /**
     * j > i => x_j > x_i
     * <p>
     * res_i,j = x_j - x_i + y_j + y_i
     * <p>
     * res_i,j+1 = x_j+1 + y_j+1 + y_i - x_i
     * 
     * = res_i,j + (x_j+1 + y_j+1) - (x_j + y_j)
     */
    public int firstHelper(int i) {
        int max = Integer.MIN_VALUE;
        int j = i + 1;
        while (j < points.length) {
            int abs = points[j][0] - points[i][0];
            if (abs <= k)
                max = Math.max(max, abs + points[j][1] + points[i][1]);
            ++j;
        }
        return max;
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static int[][] stringToInt2dArray(String input) {
        // JsonArray jsonArray = JsonArray.readFrom(input);
        JSONArray jsonArray = JSONArray.parseArray(input);
        if (jsonArray.size() == 0) {
            return new int[0][0];
        }

        int[][] arr = new int[jsonArray.size()][];
        for (int i = 0; i < arr.length; i++) {
            JSONArray cols = jsonArray.getJSONArray(i);
            arr[i] = stringToIntegerArray(cols.toString());
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File("testCase.txt"))));
        String line;
        while ((line = in.readLine()) != null) {
            int[][] points = stringToInt2dArray(line);
            line = in.readLine();
            int k = Integer.parseInt(line);
            long start = System.currentTimeMillis();
            int ret = new MaxValueOfEquation().findMaxValueOfEquation(points, k);

            String out = String.valueOf(ret);

            System.out.println(out);
            System.out.println("Duration : " + (System.currentTimeMillis() - start) + " ms");
        }
    }

}