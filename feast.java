import java.util.*;
import java.io.*;
public class feast {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("feast.in"));
        PrintWriter pw = new PrintWriter("feast.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int T = Integer.parseInt(stk.nextToken());
        int X = Integer.parseInt(stk.nextToken());
        int Y = Integer.parseInt(stk.nextToken());
        boolean[][] seen = new boolean[2][T+1];
        seen[0][0]=true;
        for(int i = 0; i < seen.length; i++){
            for(int c = 0; c < seen[i].length; c++){
                if(!seen[i][c])continue;
                if(c + X <= T) seen[i][c+X] = true;
                if(c + Y <= T) seen[i][c+Y] = true;
                if(i==0){
                    seen[1][c/2] = true;
                }
            }
        }
        int answer = 0;
        for(int i = 0; i < 2; i++){
            for(int c = 0; c < seen[i].length; c++){
                if(seen[i][c]){
                    answer = Math.max(answer, c);
                }
            }
        }
        pw.println(answer);
        pw.close();
    }
}
