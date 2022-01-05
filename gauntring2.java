import java.util.Arrays;
import java.util.Scanner;

public class gauntring2 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
       int N = scanner.nextInt();
       long P = scanner.nextInt();
       long Q = scanner.nextInt();
       long R = scanner.nextInt();
        long[][] dp = new long[N][3];
        long[] a = new long[N];
        System.out.println();
        for(int i = 0; i < N; i++){
            a[i] = scanner.nextInt();
        }
        for(int i = 0; i < N; i++){
            dp[i][0] = a[i]*R;
        }
        for(int i = 0; i < N; i++){
            long max = 0;
            for(int z = N-1; z >= i; z--){
                if(dp[z][0] > max) max = dp[z][0];
            }
            dp[i][1] = max + a[i]*Q;
        }
        for(int i = 0; i < N; i++){
           long max = 0;
            for(int z = N-1; z >= i; z--){
                if(dp[z][1] > max) max = dp[z][1];
            }
            dp[i][2] = max + a[i]*P;
        }
       long max = 0;
        for(int i = 0; i < N; i++){
            if(dp[i][2] > max) max = dp[i][2];
        }
        System.out.println(max);
    }
    public static void print2D(int mat[][])
    {
        for (int[] row : mat)
            System.out.println(Arrays.toString(row));
    }
}