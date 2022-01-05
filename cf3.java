import java.io.*;
import java.util.*;
public class cf3{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        long[] w = new long[N];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < N; i++){
            w[i] = Long.parseLong(stk.nextToken());
        }
        ArrayList<edge>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++)graph[i] = new ArrayList<>();
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(stk.nextToken())-1;
            int v = Integer.parseInt(stk.nextToken())-1;
            long c = Integer.parseInt(stk.nextToken());
            graph[u].add(new edge(v, c));
            graph[v].add(new edge(u, c));
        }

    }
    public static class edge{
        long to, weight;
        public edge(long t, long w){
            to = t;
            weight = -1*w;
        }
    }
}
