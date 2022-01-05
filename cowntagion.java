import java.io.*;
import java.util.*;

public class cowntagion {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int N = Integer.parseInt(bf.readLine());
        int[] children = new int[N];
        for(int i = 0; i < N-1; i++) {
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken()) - 1;
            int b = Integer.parseInt(stk.nextToken()) - 1;
            children[a]++;
            children[b]++;
        }
        int answer = N-1;;
        for(int i = 0; i < N; i++){
            if(i!=0&&children[i]<=1)continue;
            int c = children[i];
            if(i!=0)c--;
            int moves = 1;
            while(moves < c+1){
                moves*=2;
                answer++;
            }
        }
        pw.println(answer);
        pw.close();
    }
}