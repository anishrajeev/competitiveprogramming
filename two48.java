import java.util.*;
import java.io.*;
public class two48 {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("248.in"));
        PrintWriter pw = new PrintWriter("248.out");
        int N = Integer.parseInt(bf.readLine());
        int[] num = new int[N];
        int[][] dp = new int[N][N];
        for(int i = 0; i < N; i++){
            num[i]=Integer.parseInt(bf.readLine());
        }
        for(int len = 1; len <= N; len++){
            for(int i = 0; i + len <= N; i++){
                int j = i+len-1;
                dp[i][j]=-1;
                if(len == 1)dp[i][j]=num[i];
                for(int k = 0; k < j; k++){
                    if(dp[i][k]==dp[k+1][j]&&dp[i][k]>0)
                        dp[i][j]=Math.max(dp[i][j], dp[i][k]+1);
                }
            }
        }
        int answer = 0;
        for(int[] i:dp){
            for(int j:i){
                answer = Math.max(answer, j);
            }
        }
        pw.println(answer);
        pw.close();
    }
}
