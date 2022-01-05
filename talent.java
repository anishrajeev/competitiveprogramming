import java.util.*;
import java.io.*;
public class talent {
    static final int WMAX = 1000;
    static final int NMAX = 1000;
    static int W;
    static int N;
    static int[] weight;
    static int[] talent;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("talent.in"));
        PrintWriter pw = new PrintWriter("talent.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(stk.nextToken());
        W = Integer.parseInt(stk.nextToken());
        weight = new int[N];
        talent = new int[N];
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            int w = Integer.parseInt(stk.nextToken());
            int t = Integer.parseInt(stk.nextToken());
            weight[i] = w;
            talent[i] = t;
        }
        int lo = 0;
        int hi = (1000 * 250 * 1000) + 1;
        while (hi > lo + 1) {
            int mid = (lo + hi) / 2;
            if (verify(mid)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        pw.println(lo);
        pw.close();
    }
    public static boolean verify(int y){
        long[] dp = new long[W+1];
        Arrays.fill(dp, Long.MIN_VALUE);
        dp[0] = 0;
        for(int i = 0; i < N; i++){
            int inc = weight[i];
            long val = 1000*(long)talent[i]-y*(long)weight[i];
            for(int k = W; k >= 0; k--){
                int newk = Math.min(inc + k, W);
                if(dp[k]!=Long.MIN_VALUE){
                    dp[newk] = Math.max(dp[k]+val, dp[newk]);
                }
            }
        }
        return dp[W]>=0;
    }
}
