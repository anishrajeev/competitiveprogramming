import java.util.*;
import java.io.*;
public class diamond {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("diamond.in"));
        PrintWriter pw = new PrintWriter("diamond.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int[] diamonds = new int[N];
        for(int i = 0; i < N; i++){
            diamonds[i] = Integer.parseInt(bf.readLine());
        }
        Arrays.sort(diamonds);
        int p1 = 0;
        int p2 = 0;
        int[] results = new int[N];
        while(p2<N){
            if (diamonds[p2] - diamonds[p1] <= K) {
                results[p1] = Math.max(results[p1], p2-p1+1);
                p2++;
            }
            else
                p1++;
        }
        for (int i=p1+1; i<N; i++)
            results[i] = N-i;
        int[] lookup = new int[N];
        lookup[N-1]=lookup[N-1];
        for(int i = N-2; i >= 0; i--){
            lookup[i] = Math.max(lookup[i+1], results[i]);
        }
        int res = 0;
        for (int i=0; i<N; i++) {
            int tmp = results[i];
            if (i+results[i] < N) tmp += lookup[i+results[i]];
            res = Math.max(res, tmp);
        }
        pw.println(res);
        pw.close();
    }
}
