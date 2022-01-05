import java.util.*;
import java.io.*;
public class taming {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("taming.in"));
        PrintWriter pw = new PrintWriter("taming.out");
        int N = Integer.parseInt(bf.readLine());
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int[] input = new int[N];
        for(int i = 0; i < N; i++) input[i] = Integer.parseInt(stk.nextToken());
        int[][][] dp = new int[100][101][100];
        for(int i = 0; i < N; i++){
            for(int j = 0; j <= N; j++){
                for(int k = 0; k < N; k++){
                    dp[i][j][k] = Integer.MAX_VALUE-10000;
                }
            }
        }
        if(input[0]==0)dp[0][1][0] = 0;
        else dp[0][1][0] = 1;
        for(int i = 1; i < N; i++){
            for(int j = 1; j < N+1; j++){
                for(int k = 0; k < N; k++){
                    if(k==0){
                        for(int k2 = 0; k2 < N; k2++){
                            dp[i][j][k] = Math.min(dp[i][j][k], dp[i-1][j-1][k2]);
                        }
                        if(input[i]!=k)dp[i][j][k]++;
                    }
                    else{
                        dp[i][j][k] = dp[i-1][j][k-1];
                        if(input[i]!=k)dp[i][j][k]++;
                    }
                }
            }
        }
        for(int j = 1; j <= N; j++){
            int ans = Integer.MAX_VALUE-10000;
            for(int k = 0; k < N; k++){
                ans = Math.min(ans, dp[N-1][j][k]);
            }
            pw.println(ans);
        }
        pw.close();
    }
}
