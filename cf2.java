import java.io.*;
import java.util.*;
public class cf2{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int M = 10005;
        int n = Integer.parseInt(stk.nextToken());
        long W = Long.parseLong(stk.nextToken());
        long B = Long.parseLong(stk.nextToken());
        long X = Long.parseLong(stk.nextToken());
        stk = new StringTokenizer(bf.readLine());
        long[] c = new long[n];
        for(int i = 0; i < n; i++){
            c[i] = Long.parseLong(stk.nextToken());
        }
        stk = new StringTokenizer(bf.readLine());
        long[] cost = new long[n];
        for(int i = 0; i < n; i++){
            cost[i] = Long.parseLong(stk.nextToken());
        }
        long[][] dp = new long[1005][M];
        for(int i = 0; i < 1005; i++)Arrays.fill(dp[i], -1);
        dp[0][0] = W;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < M && dp[i][j]!=-1; j++){
                for(int k = 0; k <= c[i] && dp[i][j]-k*cost[i]>=0; k++){
                    long newmana = dp[i][j]-k*cost[i];
                    long cap = W+(j+k)*B;
                    dp[i+1][k+j] = Math.max(dp[i + 1][k + j], Math.min(newmana+X, cap));
                }
            }
        }
        long answer = 0;
        for(int i = 0; i < M; i++){
            if (dp[n][i] != -1){
                answer = Math.max(answer, i);
            }
        }
        pw.println(answer);
        pw.close();
    }
}
