import java.util.*;
import java.io.*;
public class dining {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("dining.in"));
        PrintWriter pw = new PrintWriter("dining.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        ArrayList<edge>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++)graph[i] = new ArrayList<>();
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            int t = Integer.parseInt(stk.nextToken());
            graph[a].add(new edge(b, t, false));
            graph[b].add(new edge(a, t, false));
        }
        int[] shortest = new int[N];
        Arrays.fill(shortest, Integer.MAX_VALUE);
        shortest[N-1] = 0;
        PriorityQueue<state> pq = new PriorityQueue<>(new Comparator<state>() {
            @Override
            public int compare(state o1, state o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });
        pq.add(new state(N-1, 0, false));
        boolean[] visited = new boolean[N];
        while(!pq.isEmpty()){
            state s = pq.poll();
            if(s.weight < shortest[s.node])shortest[s.node] = s.weight;
            else if(visited[s.node])continue;
            visited[s.node] = true;
            for(edge e:graph[s.node]){
                state ns = new state(e.to, s.weight + e.weight, false);
                pq.add(ns);
            }
        }
        for(int i = 0; i < K; i++){
            stk = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(stk.nextToken())-1;
            int t = Integer.parseInt(stk.nextToken());
            graph[N-1].add(new edge(n, shortest[n]-t, true));
        }

        int[] newshortest = new int[N];
        Arrays.fill(newshortest, Integer.MAX_VALUE);
        newshortest[N-1] = 0;
        pq.clear();
        Arrays.fill(visited, false);
        pq.add(new state(N-1, 0, false));
        while(!pq.isEmpty()){
            state s = pq.poll();
            if(s.weight < 0 && newshortest[s.node] < 0)continue;
            if(s.weight < newshortest[s.node] && s.ate)newshortest[s.node] = s.weight;
            else if(visited[s.node])continue;
            visited[s.node] = true;
            for(edge e:graph[s.node]){
                if(e.directed && s.ate)continue;
                boolean newate;
                if(s.ate)newate = true;
                else if(e.directed)newate = true;
                else newate = false;
                state ns = new state(e.to, s.weight + e.weight, newate);
                pq.add(ns);
            }
        }
        for(int i = 0; i < N-1; i++){
            if(newshortest[i] <= shortest[i])pw.println(1);
            else pw.println(0);
        }
        pw.close();
    }
    public static class edge{
        int to, weight;
        boolean directed;
        public edge(int t, int w, boolean d){
            to = t;
            weight = w;
            directed = d;
        }
    }
    public static class state{
        int node, weight;
        boolean ate;
        public state(int n, int w, boolean a){
            node = n;
            weight = w;
            ate = a;
        }
    }
}
