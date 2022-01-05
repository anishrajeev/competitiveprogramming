import java.util.*;
import java.io.*;
public class wifi {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("wifi.in"));
        PrintWriter pw = new PrintWriter("wifi.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int A = Integer.parseInt(stk.nextToken());
        int B = Integer.parseInt(stk.nextToken());
        int[] cows = new int[N+1];
        cows[0] = 0;
        for(int i = 0; i < N; i++){
            cows[i+1] = Integer.parseInt(bf.readLine());
        }
        Arrays.sort(cows);
        double[] dp = new double[N+1];
        Arrays.fill(dp, Double.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= N; i++){
            for(int c = i-1; c >= 0; c--){
                double range = cows[i] - ((cows[i]*1.0+cows[c+1])/2.0);
                dp[i] = Math.min(dp[i], dp[c] + A + B*range);
            }
        }
        double answer = dp[N];
        int answerint = (int)(answer*10);
        if(answerint%10 == 0)pw.println(answerint/10);
        else pw.println(answer);
        pw.close();
    }
}