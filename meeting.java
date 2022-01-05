import java.util.*;
import java.io.*;
public class meeting {
    static int N;
    static int M;
    static int[][] bessie;
    static int[][] elsie;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("meeting.in"));
        PrintWriter pw = new PrintWriter("meeting.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        bessie = new int[N][N];
        elsie = new int[N][N];
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            int c = Integer.parseInt(stk.nextToken());
            int d = Integer.parseInt(stk.nextToken());
            bessie[a][b] = c;
            elsie[a][b] = d;
        }
        boolean[] bessiecan = solve(bessie);
        boolean[] elsiecan = solve(elsie);
        boolean solved = false;
        for(int i = 0; i < bessiecan.length; i++){
            if(bessiecan[i]&&elsiecan[i]){
                pw.println(i);
                pw.close();
                solved = true;
                break;
            }
        }
        if(!solved)pw.println("IMPOSSIBLE");
        pw.close();
    }
    public static boolean[] solve(int[][] grid){
        boolean[][] dp = new boolean[N][100*N+1];
        dp[0][0] = true;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < dp[i].length; j++){
                if(!dp[i][j]) continue;
                for(int k = i+1; k < N; k++) {
                    if(grid[i][k] > 0) dp[k][j + grid[i][k]] = true;
                }
            }
        }
        return dp[N-1];
    }
}
