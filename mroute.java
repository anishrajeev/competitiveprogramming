import java.util.*;
import java.io.*;
public class mroute {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("mroute.in"));
        PrintWriter pw = new PrintWriter("mroute.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int X = Integer.parseInt(stk.nextToken());
        long[] d = new long[N];
        ArrayList<pipe>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++)graph[i] = new ArrayList<>();
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int I = Integer.parseInt(stk.nextToken())-1;
            int J = Integer.parseInt(stk.nextToken())-1;
            int L = Integer.parseInt(stk.nextToken());
            int C = Integer.parseInt(stk.nextToken());
            pipe p1 = new pipe(J, L, C);
            pipe p2 = new pipe(I, L, C);
            graph[I].add(p1);
            graph[J].add(p2);
        }
        Arrays.fill(d, Long.MAX_VALUE);
        d[0] = 0;
        PriorityQueue<State> pq = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                return Long.compare(d[o1.node], d[o2.node]);
            }
        });
        State start = new State(0, 0, Long.MAX_VALUE);
        pq.add(start);
        TreeSet<Integer> visited = new TreeSet<>();
        long min = Long.MAX_VALUE;
        while(!pq.isEmpty()){
            State s = pq.poll();
            if(s.node==N-1){
                min = Math.min(min, (s.latency+(X/s.capacity)));
            }
            if(visited.contains(s.node)&&(s.latency+(X/s.capacity)>=d[s.node]))continue;
            visited.add(s.node);
            if(!(s.node==0)&&s.latency+(X/s.capacity)<d[s.node])d[s.node]=s.latency+(X/s.capacity);
            for(pipe p:graph[s.node]){
                State news = new State(p.to, s.latency+p.L, Math.min(s.capacity, p.C));
                pq.add(news);
            }
        }
        pw.println(min);
        pw.close();
    }
    public static class State{
        int node;
        long latency, capacity;
        public State(int n, long l, long c){
            node = n;
            latency = l;
            capacity = c;
        }
    }
    public static class pipe{
        int to, L, C;
        public pipe(int t, int l, int c){
            to = t;
            L = l;
            C = c;
        }
    }
}
