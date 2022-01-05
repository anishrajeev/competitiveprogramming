import java.util.*;
import java.io.*;
public class pails {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("pails.in"));
        PrintWriter pw = new PrintWriter("pails.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int X = Integer.parseInt(stk.nextToken());
        int Y = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        boolean possible[][] = new boolean[X+1][Y+1];
        possible[0][0] = true;
        for(int opnum = 0; opnum < K; opnum++){
            boolean[][] next = new boolean[X+1][Y+1];
            for(int i = 0; i < possible.length; i++){
                for(int c = 0; c < possible[i].length; c++){
                    if(!possible[i][c])continue;
                    next[i][c]=true;
                    next[0][c]=true;
                    next[i][0]=true;
                    next[X][c]=true;
                    next[i][Y]=true;
                    int num = Math.min(Y-c, i);
                    next[i-num][c+num]=true;
                    num = Math.min(c, X-i);
                    next[i+num][c-num]=true;
                }
            }
            possible = next;
        }
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < possible.length; i++){
            for(int c = 0; c < possible[i].length; c++){
                if(!possible[i][c])continue;
                answer = Math.min(answer, Math.abs(i+c-M));
            }
        }
        pw.println(answer);
        pw.close();
    }
}
