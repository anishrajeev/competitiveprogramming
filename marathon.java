import java.util.*;
import java.io.*;
public class marathon {
    static int[] x, y;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("marathon.in"));
        PrintWriter pw = new PrintWriter("marathon.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        x = new int[N];
        y = new int[N];
        int[][] dp = new int[N+1][K+1];
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            x[i]=Integer.parseInt(stk.nextToken());
            y[i]=Integer.parseInt(stk.nextToken());
        }
        for(int i = 0; i < dp.length; i++)Arrays.fill(dp[i], 1 << 30);
        dp[1][0] = 0;
        for(int i = 2; i <= N; i++){
            for(int j = 0; j <= K; j++){
                if(j == 0)
                    dp[i][j] = dp[i-1][j] + dist(i-1, i);
                for(int x = 0; x <= K; x++){
                    if(i-(x+1)<0 || j-x<0)continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i-(x+1)][j-x] + dist(i, i-(x+1)));
                }
            }
        }
        pw.println(dp[N][K]);
        pw.close();
    }
    public static int distanceuseless(int x1, int y1, int x2, int y2){
        return (Math.abs(x1-x2)+Math.abs(y1-y2));
    }
    public static int dist(int i, int j){
        if(i != 0) i--;
        if(j != 0) j--;
        return distanceuseless(x[i],y[i],x[j],y[j]);
    }
}
