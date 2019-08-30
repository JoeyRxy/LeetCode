package mine.pack9;

import java.util.Scanner;

/**
 * DependentPack
 */
public class DependentPack {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N, V;
        N = scanner.nextInt();
        V = scanner.nextInt();
        int[] v = new int[N];// 体积
        int[] w = new int[N];// 价值
        int[] p = new int[N];// 依赖

        for (int i = 0; i < N; i++) {
            v[i] = scanner.nextInt();
            w[i] = scanner.nextInt();
            p[i] = scanner.nextInt();
        }

        scanner.close();
    }
}