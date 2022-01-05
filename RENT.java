import java.util.*;
import java.io.*;
public class RENT {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(bf.readLine());
        for(int testcase = 0; testcase < T; testcase++){
            int N = Integer.parseInt(bf.readLine());
            event[] a = new event[N];
            for(int i = 0; i < N; i++){
                StringTokenizer stk = new StringTokenizer(bf.readLine());
                int s = Integer.parseInt(stk.nextToken());
                int e = s + Integer.parseInt(stk.nextToken());
                int c = Integer.parseInt(stk.nextToken());
                a[i] = new event(s, e, c);
            }
            Arrays.sort(a);
            int[] f = new int[N];
            for(int i = 0; i < N; i++){
                f[i] = f(i, a);
            }
            long[] dp = new long[N];
            dp[0] = a[0].cost;
            for(int i = 1; i < N; i++){
                if(f[i]==-1){
                    dp[i] = Math.max(dp[i-1], a[i].cost);
                    continue;
                }
                dp[i] = Math.max(dp[i-1], dp[f[i]] + a[i].cost);
            }
            Arrays.sort(dp);
            pw.println(dp[N-1]);
        }
        pw.close();
    }
    public static int f(int i, event[] a){
        int s = 0, e = a.length-1;
        int answer = -1;
        pair strt = new pair(a[i].start, i);
        while (s <= e) {
            int mid = (s+e)/2;
            if (a[mid].end > strt.end) {
                e = mid - 1;
            }
            else {
                answer = mid;
                s = mid + 1;
            }
        }
        return answer;
    }
    public static class event implements Comparable<event>{
        long start, end, cost;
        public event(long s, long e, long c){
            start = s;
            end = e;
            cost = c;
        }
        @Override
        public int compareTo(event o) {
            return Long.compare(end, o.end);
        }
    }
    public static class pair implements Comparable<pair>{
        long end;
        int index;
        public pair(long e, int i){
            end = e;
            index = i;
        }
        @Override
        public int compareTo(pair o) {
            return Long.compare(end, o.end);
        }
        @Override
        public boolean equals(Object obj) {
            pair p = (pair)obj;
            return (end == p.end && index == p.index);
        }
    }
}