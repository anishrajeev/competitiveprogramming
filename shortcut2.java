import java.util.*;
import java.io.*;
public class shortcut2 {
    static int[] par;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("shortcut.in"));
        PrintWriter pw = new PrintWriter("shortcut.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int T = Integer.parseInt(stk.nextToken());
        long[] cows = new long[N];
        long[] d = new long[N];
        long[] amounts = new long[N];
        par = new int[N];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < N; i++){
            cows[i] = Long.parseLong(stk.nextToken());
        }
        ArrayList<Edge>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++)graph[i] = new ArrayList<>();
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            long t = Long.parseLong(stk.nextToken());
            graph[a].add(new Edge(b, t));
            graph[b].add(new Edge(a, t));
        }
        Arrays.fill(d, (int)Math.pow(10, 18));
        d[0] = 0;
        Arrays.fill(par, -1);
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Long.compare(d[o1],d[o2]);
            }
        });
        pq.add(0);
        TreeSet<Integer> visited = new TreeSet<>();
        while(!pq.isEmpty()){
            int node = pq.poll();
            if(visited.contains(node))continue;
            visited.add(node);
            for(Edge edge:graph[node]){
                int newnode = edge.going;
                if(visited.contains(newnode))continue;
                long distance = d[node] + edge.weight;
                //if(distance < 0)System.out.println("Negative");
                if(distance < d[newnode]){
                    pq.remove(newnode);
                    d[newnode] = distance;
                    par[newnode] = node;
                    pq.add(newnode);
                }
                else if(distance==d[newnode]){
                    if(node < par[newnode]){
                        pq.remove(newnode);
                        d[newnode] = distance;
                        par[newnode] = node;
                        pq.add(newnode);
                    }
                }
            }
        }
        //System.out.println(Arrays.toString(par));
        for(int i = 0; i < N; i++){
            int trace = i;
            while(trace != -1){
                amounts[trace] += cows[i];
                trace = par[trace];
            }
        }
        long answer = 0;
        for(int i = 0; i < N; i++){
            answer = Math.max(answer, amounts[i]*(d[i]-T));
        }
        pw.println(answer);
        pw.close();
    }

    public static class Edge{
        int going;
        long weight;
        public Edge(int g, long w){
            going = g;
            weight = w;
        }
    }
}
