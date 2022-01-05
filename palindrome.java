import java.util.*;
import java.io.*;
public class palindrome {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());
        String[] a = bf.readLine().split("");
        int[][] dp = new int[N][N];
        for(int i = 0; i < N; i++)Arrays.fill(dp[i], Integer.MAX_VALUE);
        for(int i = 0; i < N; i++)dp[i][i] = 0;
        for(int i = 1; i < N; i++)dp[i][i-1] = 0;

        for(int length = 1; length < N; length++){
            for(int i = 0; i + length < N; i++){
                int j = i + length;
                dp[i][j] = Math.min(dp[i][j], dp[i+1][j]+1);
                dp[i][j] = Math.min(dp[i][j], dp[i][j-1]+1);
                if(a[i].equals(a[j]))dp[i][j] = Math.min(dp[i][j], dp[i+1][j-1]);
                //System.out.println("(" + i + ", " + j  + ")" + " = " + dp[i][j]);
            }
        }
        pw.println(dp[0][N-1]);
        pw.close();
    }
}