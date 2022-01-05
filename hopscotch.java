import java.util.*;
import java.io.*;
public class hopscotch {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("hopscotch.in"));
        PrintWriter pw = new PrintWriter("hopscotch.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int r = Integer.parseInt(stk.nextToken());
        int c = Integer.parseInt(stk.nextToken());
        int[][] board = new int[r][c];
        int[][] dp = new int[r][c];
        for(int i = 0; i < r; i++){
            stk = new StringTokenizer(bf.readLine());
            for(int z = 0; z < c; z++){
                board[i][z]=Integer.parseInt(stk.nextToken());
            }
        }
        dp[0][0]=1;
        long mod = 1000000007;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                for(int k = i+1; k < r; k++){
                    for(int l = j+1; l < c; l++){
                        if(board[k][l]!=board[i][j]){
                            dp[k][l]+=dp[i][j];
                            dp[k][l]%=mod;
                        }
                    }
                }
            }
        }
        pw.println(dp[r-1][c-1]);
        pw.close();
    }
}