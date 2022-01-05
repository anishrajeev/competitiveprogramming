import java.util.*;
import java.io.*;
public class checklist {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("checklist.in"));
        PrintWriter pw = new PrintWriter("checklist.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int H = Integer.parseInt(stk.nextToken());
        int G = Integer.parseInt(stk.nextToken());
        Point[] Holstein = new Point[H];
        Point[] Guernsey = new Point[G];
        for(int i = 0; i < H; i++){
            stk = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            Holstein[i] = new Point(x, y);
        }
        for(int i = 0; i < G; i++){
            stk = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            Guernsey[i] = new Point(x, y);
        }
        long[][][] dp = new long[H+1][G+1][2];
        for(int i = 0; i < dp.length; i++){
            for(int c = 0; c < dp[i].length; c++){
                Arrays.fill(dp[i][c], 1L << 60);
            }
        }
        dp[1][0][0]=0;
        for(int i = 0; i < dp.length; i++){
            for(int c = 0; c < dp[i].length; c++){
                for(int k = 0; k < 2; k++){
                    if(k==0&&i==0)continue;
                    if(k==1&&c==0)continue;
                    Point p;
                    if(k==0) p = Holstein[i-1];
                    else p = Guernsey[c-1];
                    if(i < H) {
                        dp[i+1][c][0] = Math.min(dp[i+1][c][0], dp[i][c][k] + p.dist(Holstein[i]));
                    }
                    if(c < G) {
                        dp[i][c+1][1] = Math.min(dp[i][c+1][1], dp[i][c][k] + p.dist(Guernsey[c]));
                    }
                }
            }
        }
        pw.println(dp[H][G][0]);
        pw.close();
    }
    public static class Point{
        int x, y;
        public Point(int a, int b){
            x = a;
            y = b;
        }
        public int dist(Point s){
            return (x-s.x)*(x-s.x) + (y-s.y)*(y-s.y);
        }
    }
}
