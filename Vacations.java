import java.util.Arrays;
import java.util.Scanner;

public class Vacations {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
       int N = scanner.nextInt();
        int[] a = new int[N];
       int answer;
        for(int i = 0; i < N; i++){
            a[i] = scanner.nextInt();
        }
        int[][] dp = new int[3][N];
        for(int i = 0; i < 3; i++){
            for(int z = 0; z < N; z++){
                dp[i][z] = 1000;
            }
        }
        dp[0][0] = 1;
        if(a[0] == 3 || a[0] == 1){
            dp[1][0] = 0;
        }
        if(a[0] == 3 || a[0] == 2){
            dp[2][0] = 0;
        }
        for(int i = 1; i < N; i++){
            dp[0][i] = Math.min(Math.min(dp[0][i-1], dp[1][i-1]), dp[2][i-1])+1;
            if(a[i] == 3 || a[i] == 1){
                dp[1][i] = Math.min(dp[0][i-1], dp[2][i-1]);
            }
            if(a[i] == 3 || a[i] == 2){
                dp[2][i] = Math.min(dp[0][i-1], dp[1][i-1]);
            }
        }
        answer = Math.min(dp[0][N-1], Math.min(dp[1][N-1], dp[2][N-1]));
        System.out.println(answer);
    }
    public static void print2D(int mat[][])
    {
        for (int[] row : mat)
            System.out.println(Arrays.toString(row));
    }
}
