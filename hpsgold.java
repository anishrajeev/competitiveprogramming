import java.util.*;
import java.io.*;
public class hpsgold {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter("hps.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());
        int[] moves = new int[n];
        int[][][] dp = new int[n+1][k+1][3];
        for(int i = 0; i < n; i++){
            Character move = bf.readLine().charAt(0);
            if(move=='H')moves[i]=0;
            if(move=='P')moves[i]=1;
            if(move=='S')moves[i]=2;
        }
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= k; j++){
                for(int s = 0; s < 3; s++){
                    int state1 = (s+1)%3;
                    int state2 = (s+2)%3;
                    if(i==0)dp[i][j][s]=0;
                    else{
                        if(j==0){
                            if(s == moves[i-1]){
                                dp[i][j][s] = dp[i-1][j][s] + 1;
                            }
                            else dp[i][j][s] = dp[i-1][j][s];
                        }
                        else{
                            dp[i][j][s] = Math.max(Math.max(dp[i-1][j][s]+ (moves[i-1] == s ? 1 : 0), dp[i-1][j-1][state1]+ (moves[i-1] == s ? 1 : 0)), dp[i-1][j-1][state2]+ (moves[i-1] == s ? 1 : 0));
                        }
                    }
                }
            }
        }
        pw.println(Math.max(dp[n][k][0], Math.max(dp[n][k][1], dp[n][k][2])));
        pw.close();
    }
}