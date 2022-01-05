import java.io.*;
import java.util.StringTokenizer;

public class acm {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int[] p1 = new int[N];
        for(int i = 0; i < N; i++)p1[i] = Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(bf.readLine());
        int[] p2 = new int[N];
        for(int i = 0; i < N; i++)p2[i] = Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(bf.readLine());
        int[] p3 = new int[N];
        for(int i = 0; i < N; i++)p3[i] = Integer.parseInt(stk.nextToken());
        int answer = Integer.MAX_VALUE;
        answer = Math.min(answer, solve(N, p1, p2, p3));
        answer = Math.min(answer, solve(N, p1, p3, p2));
        answer = Math.min(answer, solve(N, p2, p1, p3));
        answer = Math.min(answer, solve(N, p2, p3, p1));
        answer = Math.min(answer, solve(N, p3, p1, p2));
        answer = Math.min(answer, solve(N, p3, p2, p1));
        pw.println(answer);
        pw.close();
    }
    public static int solve(int N, int[] p1, int[] p2, int[] p3){
        int[] dp1 = new int[N];
        int[] dp2 = new int[N];
        int[] dp3 = new int[N];
        dp1[0] = p1[0];
        for(int i = 1; i < N; i++)dp1[i] = dp1[i-1]+p1[i];
        dp2[1] = p1[0]+p2[1];
        for(int i = 2; i < N; i++) dp2[i] = Math.min(dp1[i-1], dp2[i-1])+p2[i];
        dp3[2] = p1[0]+p2[1]+p3[2];
        for(int i = 3; i < N; i++) dp3[i] = Math.min(dp2[i-1], dp3[i-1])+p3[i];
        return dp3[N-1];
    }
}
