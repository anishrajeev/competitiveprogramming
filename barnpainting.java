import java.util.*;
import java.io.*;
public class barnpainting {
    static long[][] dp;
    static final int MOD = 1000000007;
    static ArrayList<Integer>[] edges;
    static int[] color;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("barnpainting.in"));
        PrintWriter pw = new PrintWriter("barnpainting.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());
        dp = new long[n][3];
        color = new int[n];
        edges = new ArrayList[n];
        Arrays.fill(color, -1);
        for(int i = 0; i < n; i++){
            edges[i] = new ArrayList<>();
            Arrays.fill(dp[i], -1);
        }
        for(int i = 1; i < n; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            edges[a].add(b);
            edges[b].add(a);
        }
        for(int i = 0; i < k; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int c = Integer.parseInt(stk.nextToken())-1;
            color[a] = c;
        }
        long ans = solve(0, 0, -1, -1) + solve(0, 1, -1, -1) + solve(0, 2, -1, -1);
        ans%=MOD;
        pw.println(ans);
        pw.close();
    }
    public static long solve(int currV, int currC, int parV, int parC){
        if(currC == parC || (color[currV] >= 0 && currC != color[currV])) return 0;
        if(dp[currV][currC] >= 0)return dp[currV][currC];
        dp[currV][currC] = 1;
        for(int out: edges[currV]) {
            if(out == parV) continue;
            long canColor = 0;
            for(int c = 0; c < 3; c++){
                canColor += solve(out, c, currV, currC);
                canColor %= MOD;
            }
            dp[currV][currC] *= canColor;
            dp[currV][currC] %= MOD;
        }
        return dp[currV][currC];
    }
}
