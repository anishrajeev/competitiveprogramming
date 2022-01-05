import java.util.*;
import java.io.*;

public class cowrun {
    public static int length;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("cowrun.in"));
        PrintWriter pw = new PrintWriter("cowrun.out");
        int N = Integer.parseInt(bf.readLine());
        int[] cows = new int[N+1];
        cows[N] = 0;
        for(int i = 0; i < N; i++){
            int c = Integer.parseInt(bf.readLine());
            cows[i] = c;
        }
        length = N+1;
        Arrays.sort(cows);
        N++;
        pair[][][] dp = new pair[N][N][2];
        int zeroindex = 0;
        for(int i = 0; i < N; i++){
            if(cows[i]==0){
                zeroindex = i;
                break;
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                dp[i][j][0] = new pair(Integer.MAX_VALUE/2, Integer.MAX_VALUE/2, 0, 0);
                dp[i][j][1] = new pair(Integer.MAX_VALUE/2, Integer.MAX_VALUE/2, 0, 0);
            }
        }
        dp[zeroindex][zeroindex][0] = new pair(0, 0, 0, 0);
        dp[zeroindex][zeroindex][1] = new pair(0, 0, 0, 0);
        for (int length = 1; length < N; length++) {
            for(int i = 0; i < N && i <= zeroindex; i++){
                int j = i + length;
                if(j < zeroindex || j >= N)continue;
                dp[i][j][1] = min(
                        new pair(dp[i][j-1][0].x + dp[i][j-1][0].y + cows[j] - cows[i], dp[i][j-1][0].y + cows[j] - cows[i], i, j),
                        new pair(dp[i][j-1][1].x + dp[i][j-1][1].y + cows[j] - cows[j-1], dp[i][j-1][1].y + cows[j] - cows[j-1], i, j));
                if(i+1 < N)
                    dp[i][j][0] = min(
                            new pair(dp[i+1][j][0].x + dp[i+1][j][0].y + cows[i+1] - cows[i], dp[i+1][j][0].y + cows[i+1] - cows[i], i, j),
                            new pair(dp[i+1][j][1].x + dp[i+1][j][1].y + cows[j] - cows[i], dp[i+1][j][1].y + cows[j] - cows[i], i, j));
                /*System.out.println("(" + cows[i] + ", " + cows[j] + "): ");
                System.out.println(dp[i][j][0].x + ", " + dp[i][j][0].y);
                System.out.println(dp[i][j][1].x + ", " + dp[i][j][1].y);*/
            }
        }
        long answer = min(dp[0][N-1][0], dp[0][N-1][1]).x;
        pw.println(answer);
        pw.close();
    }
    public static class pair{
        long x, y;
        int xindex, yindex;
        public pair(long a, long b, int xi, int yi){
            x = a;
            y = b;
            xindex = xi;
            yindex = yi;
        }
    }
    public static pair min(pair p1, pair p2){
        int x = length - Math.abs(p1.yindex-p1.xindex+1);
        //System.out.println(x + " " + p1.x + " " + p1.y);
        //System.out.println(x + " " + p2.x + " " + p2.y);
        //System.out.println(x + " " + p2.xindex + " " + p2.yindex);
        if(p1.x >= Integer.MAX_VALUE/2 || p1.y >= Integer.MAX_VALUE/2){
            //System.out.println("p2 returned");
            return p2;
        }
        if(p1.x + x*(p1.y-p2.y) < p2.x){
            //System.out.println("p1 returned");
            return p1;
        }
        else{
            //System.out.println("p2 returned");
            return p2;
        }
    }
}