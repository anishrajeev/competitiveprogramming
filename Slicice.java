import java.io.*;
import java.util.*;

public class Slicice {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("tester.in"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int[] P = new int[N];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < N; i++){
            P[i] = Integer.parseInt(stk.nextToken());
        }
        int[] B = new int[M+1];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < M+1; i++){
            B[i] = Integer.parseInt(stk.nextToken());
        }
        int[][] dp = new int[N][K+1];
        for(int i = 0; i < N; i++){
            for(int c = 0; c <= K; c++){
                //d = how many u drafted before team i
                for(int d = 0; d <= c; d++){
                    if(i!=0)dp[i][c] = Math.max(dp[i][c], dp[i-1][d] + B[Math.min(P[i] + (c-d), M)]);
                    else dp[i][c] = Math.max(dp[i][c], B[Math.min(P[i] + (c-d), M)]);
                }
            }
        }
        pw.println(dp[N-1][K]);
        pw.close();
    }
}