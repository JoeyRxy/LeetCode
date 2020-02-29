package mine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import mine.knowledge.LUP;
import mine.knowledge.Matrix;
import mine.knowledge.dynamic_programming.intro_to_algo.CutRod;
import mine.knowledge.dynamic_programming.intro_to_algo.MatrixMultiplyParens;

/**
 * Test
 */
public class BasicTest {

    @Test
    public void testCast() {
        List<Integer>[] lists = new List[10];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
            lists[i].add((int) (Math.random() * 100));
            lists[i].add((int) (Math.random() * 100));
            lists[i].add((int) (Math.random() * 100));
        }
        for (int i = 0; i < lists.length; i++) {
            System.out.println(lists[i]);
        }
    }

    @Test
    public void bitwiseOperation() {
        for (int i = 0; i < 20; i++) {
            System.out.println(i + " : " + (i & (-i)));
        }
    }

    @Test
    public void testMatrixMultiplyParens() throws IOException {
        int bound = 100;
        int muliple = 1;
        Matrix[] matrices = new Matrix[100];
        Random r = new Random(System.currentTimeMillis());
        matrices[0] = new Matrix(r.nextInt(bound) + 1, r.nextInt(bound) + 1);
        matrices[0].randomInit(muliple);
        for (int i = 1; i < matrices.length; i++) {
            matrices[i] = new Matrix(matrices[i - 1].size()[1], r.nextInt(bound) + 1);
            matrices[i].randomInit(muliple);
        }
        // Matrix[] matrices = new Matrix[6];
        // matrices[0] = new Matrix(30, 35);
        // matrices[0].randomInit(3);
        // matrices[1] = new Matrix(35, 15);
        // matrices[1].randomInit(3);
        // matrices[2] = new Matrix(15, 5);
        // matrices[2].randomInit(3);
        // matrices[3] = new Matrix(5, 10);
        // matrices[3].randomInit(3);
        // matrices[4] = new Matrix(10, 20);
        // matrices[4].randomInit(3);
        // matrices[5] = new Matrix(20, 15);
        // matrices[5].randomInit(3);
        String file = Matrix.class.getClassLoader().getResource("").getFile() + "matrixParenthesis.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)));
        writer.write("共有 " + matrices.length + " 个矩阵\n\n");
        for (int i = 0; i < matrices.length; i++) {
            writer.write(
                    "========== matrices[" + i + "]'s size : " + Arrays.toString(matrices[i].size()) + " ==========\n");
            // writer.write(matrices[i].toString() + "\n\n");
        }

        long start, end;
        start = System.currentTimeMillis();
        MatrixMultiplyParens t = new MatrixMultiplyParens(matrices);
        long dpRes = t.dpAns();
        end = System.currentTimeMillis();
        System.out.println(dpRes);
        System.out.println("dp time : " + (end - start) + " ms");
        String parenRes = t.parenRes();
        System.out.println(parenRes);
        writer.write("\n\n======================\nParenRes : \n" + parenRes);
        writer.write("\n\n\n======================\nMultiRes : \n" + t.multiRes());

        // System.out.println("\n\n\n\n");
        // start = System.currentTimeMillis();
        // long dfsRes = t.dfs(0, matrices.length - 1);
        // end = System.currentTimeMillis();
        // System.out.println(dfsRes);
        // System.out.println("dfs time : " + (end - start) + " ms");
        writer.close();

    }

    @Test
    public void testCutRod() {
        CutRod t = new CutRod(new int[] { 1, 2, 5, 7, 11, 13, 17, 19, 23, 25 },
                new double[] { 1.0, 2.05, 4.8, 7.35, 11.5, 13.65, 17.7, 19.7, 23.9, 25.5 });
        int n = 99;
        long start, end;
        // start = System.currentTimeMillis();
        // double res = t.recursive(n);
        // end = System.currentTimeMillis();
        // System.out.println("recursive : " + res + ", duration : " + (end - start) +
        // "ms.");
        System.out.println(t.recurSolution(n));
        start = System.currentTimeMillis();
        double res2 = t.memo(n);
        end = System.currentTimeMillis();
        System.out.println("memo recursive : " + res2 + ", duration : " + (end - start) + " ms.");
        System.out.println(t.memoSolution(n));
        start = System.currentTimeMillis();
        double res3 = t.dynamicProgramming(n);
        end = System.currentTimeMillis();
        System.out.println("dynamic programming : " + res3 + ", duration : " + (end - start) + " ms.");
        System.out.println(t.dpSolution(n));
    }

    @Test
    public void testLUP() {
        Matrix A = new Matrix(5, 8);
        Matrix B = new Matrix(5, 5);
        A.randomInit(100);
        B.randomInit(100);
        System.out.println(A);
        System.out.println("===============");
        System.out.println(B);
        LUP lup = new LUP(A, B);
        System.out.println("==============");
        System.out.println(lup);
    }
}
