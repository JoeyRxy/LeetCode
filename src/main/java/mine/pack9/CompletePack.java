package mine.pack9;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * CompletePack
 */
public class CompletePack {

    public static void main(String[] args) {
        int N, V;
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        V = scanner.nextInt();
        int v[] = new int[N];
        int p[] = new int[N];
        for (int i = 0; i < N; i++) {
            v[i] = scanner.nextInt();
            p[i] = scanner.nextInt();
        }

        ArrayList<Integer> v2, p2;
        v2 = new ArrayList<>();
        p2 = new ArrayList<>();

        int Vt, Pt;
        for (int i = 0; i < N; i++) {
            Vt = v[i];
            Pt = p[i];
            while (Vt < V) {
                v2.add(Vt);
                p2.add(Pt);
                Vt <<= 1;
                Pt <<= 1;
            }
        }

        int size = v2.size();
        // Begin cal
        int dp[] = new int[V + 1];
        int idx;
        for (int i = 0; i < size; i++) {
            idx = v2.get(i);
            for (int j = V; j >= idx; j--)
                dp[j] = Math.max(dp[j], dp[j - idx] + p2.get(i));
        }
        System.out.println(dp[V]);
    }
}