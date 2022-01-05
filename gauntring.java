import java.util.Arrays;
import java.util.Scanner;

public class gauntring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       int N = scanner.nextInt();
       long P = scanner.nextInt();
       long Q = scanner.nextInt();
       long R = scanner.nextInt();
        long[] dp = new long[N];
        long[] dp1 = new long[N];
        long[] dp2 = new long[N];
        long[] a = new long[N];
        for(int i = 0; i < N; i++){
            a[i] = scanner.nextInt();
        }
        dp[0] = P*a[0];
        for(int i = 1; i < N; i++){
            dp[i] = Math.max(dp[i-1], a[i]*P);
        }
        dp1[0] = dp[0] + a[0]*Q;
        for(int i = 1; i < N; i++){
            dp1[i] = Math.max(dp1[i-1], dp[i] + a[i]*Q);
        }
        dp2[0] = dp1[0] + a[0]*R;
        for(int i = 1; i < N; i++){
            dp2[i] = Math.max(dp2[i-1], dp1[i] + a[i] * R);
        }
        System.out.println(dp2[N-1]);
    }
}
