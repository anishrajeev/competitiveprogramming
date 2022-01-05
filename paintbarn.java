import java.io.*;
import java.util.*;
public class paintbarn {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("paintbarn.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int answer = 0;
        int[][] barn = new int[1005][1005];
        for(int i = 0; i < 1005; i++){
            Arrays.fill(barn[i], 0);
        }
        for(int i = 0; i < N; i++){
            int x1,y1,x2,y2;
            StringTokenizer stkxy = new StringTokenizer(bf.readLine());
            x1 = Integer.parseInt(stkxy.nextToken());
            y1 = Integer.parseInt(stkxy.nextToken());
            x2 = Integer.parseInt(stkxy.nextToken());
            y2 = Integer.parseInt(stkxy.nextToken());
            for(int c = x1; c < x2; c++){
                barn[c][y1]++;
                barn[c][y2]--;
            }
        }
        for(int i = 0; i < 1004; i++){
            for(int c = 0; c < 1004; c++){
                if(barn[i][c]== K) answer++;
                barn[i][c+1]+=barn[i][c];
            }
        }
        pw.println(answer);
        pw.close();
    }
}
