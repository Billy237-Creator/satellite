
import java.util.Scanner;

public class Main {

    static int[][] readImage(Scanner sc, int n, int m) {
        int[][] img = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) img[i][j] = sc.nextInt();
        return img;
    }

    static boolean equalLine(int[][] a, int[][] b, boolean rows, int k) {
        int len = rows ? a[0].length : a.length;
        for (int i = 0; i < len; i++) {
            int r = rows ? k : i, c = rows ? i : k;
            if (a[r][c] != b[r][c]) return false;
        }
        return true;
    }

    static int findBoundary(int[][] a, int[][] b, boolean rows, boolean reverse) {
        int limit = rows ? a.length : a[0].length;
        int k = reverse ? limit - 1 : 0, step = reverse ? -1 : 1;
        while (k >= 0 && k < limit) {
            if (!equalLine(a, b, rows, k)) return k;
            k += step;
        }
        return -1;
    }

    static int[] findAllBoundaries(int[][] a, int[][] b) {
        return new int[]{findBoundary(a,b,true,false), findBoundary(a,b,true,true),
                         findBoundary(a,b,false,false), findBoundary(a,b,false,true)};
    }

    static void printResult(int[] p) {
        if (p[0] == -1) System.out.println("Images are identical");
        else System.out.println("X1=" + p[2] + " X2=" + p[3] +
                                " Y1=" + p[0] + " Y2=" + p[1]);
    }

    static void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[][] oldImg = readImage(sc, n, m);
        int[][] newImg = readImage(sc, n, m);
        printResult(findAllBoundaries(oldImg, newImg));
        sc.close();
    }

    public static void main(String[] args) {
        solve();
    }
}