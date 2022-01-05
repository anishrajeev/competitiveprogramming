import java.io.*;
import java.util.*;
public class dancemooves {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int[] ans = new int[N];
        int[] cows = new int[N];
        pair[] swaps = new pair[K];
        for(int i = 1; i <= N; i++)ans[i-1] = i;
        cows = ans.clone();
        TreeSet<Integer>[] answer = new TreeSet[N];
        for(int i = 0; i < N; i++){
            answer[i] = new TreeSet<>();
            answer[i].add(i);
        }
        for(int i = 0; i < K; i++) {
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            swaps[i] = new pair(a, b);
            int temp = cows[b];
            cows[b] = cows[a];
            cows[a] = temp;
            answer[cows[a] - 1].add(b);
            answer[cows[b] - 1].add(a);
        }
        int count = 0;
        while(!Arrays.equals(cows,  ans)){
            for(int i = 0; i < K; i++) {
                int a = swaps[i].a;
                int b = swaps[i].b;
                int temp = cows[b];
                answer[cows[a] - 1].add(b);
                answer[cows[b] - 1].add(a);
                cows[b] = cows[a];
                cows[a] = temp;
                System.out.println(Arrays.toString(cows));
            }
        }
        /*for(int i = 0; i < N; i++){
            pw.println(answer[i].size());
        }*/
        pw.close();
    }
    public static class pair{
        int a, b;
        public pair(int x, int y){
            a = x;
            b = y;
        }
    }
}
