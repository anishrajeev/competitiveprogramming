import java.util.*;
import java.io.*;
public class nocross {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("nocross.in"));
        PrintWriter pw = new PrintWriter("nocross.out");
        int N = Integer.parseInt(bf.readLine());
        int[] S = new int[N+1];
        int[] T = new int[N+1];
        int[][] dp = new int[N+1][N+1];
        for (int i=1; i<=N; i++) S[i] = Integer.parseInt(bf.readLine());
        for (int i=1; i<=N; i++) T[i] = Integer.parseInt(bf.readLine());
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                int num = 0;
                if(Math.abs(S[i]-T[j])<=4)num=1;
                dp[i][j]=Math.max(dp[i-1][j],Math.max(dp[i][j-1], num+dp[i-1][j-1]));
            }
        }
        pw.println(dp[N][N]);
        pw.close();
    }
}
