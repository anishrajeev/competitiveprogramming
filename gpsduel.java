import java.util.*;
import java.io.*;
public class gpsduel {
    static ArrayList<edge>[] GPS1;
    static ArrayList<edge>[] GPS2;
    static ArrayList<edge>[] graph1;
    static State[] GPS1min;
    static State[] GPS2min;
    public static void main(String[] args) throws IOException{
        long startTime = System.nanoTime();
        BufferedReader bf = new BufferedReader(new FileReader("gpsduel.in"));
        PrintWriter pw = new PrintWriter("gpsduel.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        GPS1 = new ArrayList[N];
        GPS2 = new ArrayList[N];
        graph1 = new ArrayList[N];
        GPS1min = new State[N];
        GPS2min = new State[N];
        for(int i = 0; i < N; i++){
            GPS1[i] = new ArrayList<>();
            GPS2[i] = new ArrayList<>();
            graph1[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int A = Integer.parseInt(stk.nextToken())-1;
            int B = Integer.parseInt(stk.nextToken())-1;
            int P = Integer.parseInt(stk.nextToken());
            int Q = Integer.parseInt(stk.nextToken());
            GPS1[B].add(new edge(A, P));
            GPS2[B].add(new edge(A, Q));
            graph1[A].add(new edge(B, P));
        }
        for(int i = 0; i < N; i++){
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(N-1);
            GPS1min[i] = new State(Integer.MAX_VALUE, i, -1);
            GPS2min[i] = new State(Integer.MAX_VALUE, i, -1);
        }

        boolean[] visited = new boolean[N];
        PriorityQueue<State> pq = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                return Integer.compare(o1.count, o2.count);
            }
        });
        pq.add(new State(0, N-1, GPS1min[N-1].next));
        while(!pq.isEmpty()){
            State s = pq.poll();
            if(visited[s.node])continue;
            GPS1min[s.node] = s;
            visited[s.node] = true;
            for(edge e:GPS1[s.node]){
                State newstate = new State(e.weight+s.count, e.to, s.node);
                pq.add(newstate);
            }
        }
        visited = new boolean[N];
        pq = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                return Integer.compare(o1.count, o2.count);
            }
        });
        pq.add(new State(0, N-1, GPS2min[N-1].next));
        while(!pq.isEmpty()){
            State s = pq.poll();
            if(visited[s.node])continue;
            GPS2min[s.node] = s;
            visited[s.node] = true;
            for(edge e:GPS2[s.node]){
                State newstate = new State(e.weight+s.count, e.to, s.node);
                pq.add(newstate);
            }
        }
        int[] d = new int[N];
        visited = new boolean[N];
        pq = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                return Integer.compare(o1.count, o2.count);
            }
        });
        Arrays.fill(d, Integer.MAX_VALUE);
        d[0] = 0;
        pq.add(new State(0, 0, -1));
        while(!pq.isEmpty()){
            State x = pq.poll();
            if(visited[x.node])continue;
            d[x.node] = x.count;
            visited[x.node] = true;
            int gps1 = GPS1min[x.node].next;
            int gps2 = GPS2min[x.node].next;
            for(edge e:graph1[x.node]){
                int to = e.to;
                if(to==gps1&&to==gps2)pq.add(new State(x.count, to, -1));
                else if(to==gps1||to==gps2)pq.add(new State(x.count+1, to, -1));
                else pq.add(new State(x.count+2, to, -1));
            }
        }
        pw.println(d[N-1]);
        /*long endTime = System.nanoTime() - startTime;
        System.out.println(endTime);*/
        pw.close();
    }
    public static class State{
        int count;
        int node, next;
        public State(int c, int n, int p){
            count = c;
            node = n;
            next = p;
        }
    }
    public static class edge{
        int to;
        int weight;
        public edge(int t, int w){
            to = t;
            weight = w;
        }
    }
}
