import java.util.*;
import java.io.*;
public class teamwork {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("teamwork.in"));
        PrintWriter pw = new PrintWriter("teamwork.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());
        int[] cows = new int[n];
        for(int i = 0; i < n; i++) cows[i] = Integer.parseInt(bf.readLine());
        int[] dp = new int[n];
        dp[0] = cows[0];
        for(int i = 1; i < n; i++){
            int max = cows[i];
            for(int j = i; j >=0 && i-j+1 <= k; j--){
                max = Math.max(max, cows[j]);
                if(j == 0)dp[i] = Math.max(dp[i], max*(i-j+1));
                else dp[i] = Math.max(dp[i], dp[j-1]+max*(i-j+1));
            }
        }
        pw.println(dp[n-1]);;
        pw.close();
    }
}
