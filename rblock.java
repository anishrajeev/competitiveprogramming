import java.util.*;
import java.io.*;
public class rblock {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("rblock.in"));
        PrintWriter pw = new PrintWriter("rblock.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int[][] graph = new int[N][N];
        ArrayList<Integer>[] graphlist = new ArrayList[N];
        for(int i = 0; i < N; i++)graphlist[i] = new ArrayList<>();
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            int length = Integer.parseInt(stk.nextToken());
            graph[a][b] = length;
            graph[b][a] = length;
            graphlist[a].add(b);
            graphlist[b].add(a);
        }
        LinkedList<state> q = new LinkedList<>();
        long[] d = new long[N];
        Arrays.fill(d, Long.MAX_VALUE);
        d[0] = 0;
        ArrayList<Integer> path12321 = new ArrayList<>();
        path12321.add(0);
        q.add(new state(0, 0, path12321));
        boolean[] visited = new boolean[N];
        ArrayList<Integer> minpath = new ArrayList<>();
        while(!q.isEmpty()){
            state s = q.poll();
            if(d[s.node]>s.count){
                d[s.node] = s.count;
                if(s.node==N-1){
                    minpath.clear();
                    minpath.addAll(s.path);
                }
            }
            else if(visited[s.node])continue;
            visited[s.node] = true;
            for(int i:graphlist[s.node]){
                ArrayList<Integer> newpath = new ArrayList<>();
                newpath.addAll(s.path);
                newpath.add(i);
                q.add(new state(i, s.count+graph[s.node][i], newpath));
            }
        }
        long regular = d[N-1];
        long answer = 0;
        for(int i = 0; i < minpath.size()-1; i++){
            int from = minpath.get(i);
            int to = minpath.get(i+1);
            int oldvalue = graph[from][to];
            graph[from][to] = graph[from][to]*2;
            graph[to][from] = graph[to][from]*2;
            q.clear();
            Arrays.fill(d, Long.MAX_VALUE);
            d[0] = 0;
            ArrayList<Integer> newminpath = new ArrayList<>();
            newminpath.add(0);
            q.add(new state(0, 0, newminpath));
            Arrays.fill(visited, false);
            while(!q.isEmpty()){
                state s = q.poll();
                if(d[s.node]>s.count){
                    d[s.node] = s.count;
                    if(s.node==N-1){
                        newminpath.clear();
                        newminpath.addAll(s.path);
                    }
                }
                else if(visited[s.node])continue;
                visited[s.node] = true;
                for(int c:graphlist[s.node]){
                    ArrayList<Integer> newpath = new ArrayList<>();
                    newpath.addAll(s.path);
                    newpath.add(c);
                    q.add(new state(c, s.count+graph[s.node][c], newpath));
                }
            }
            answer = Math.max(answer, (d[N-1]-regular));
            graph[from][to] = oldvalue;
            graph[to][from] = oldvalue;
        }
        pw.println(answer);
        pw.close();
    }
    public static class state{
        int node;
        long count;
        ArrayList<Integer> path;
        public state(int n, long c, ArrayList<Integer> p){
            node = n;
            count = c;
            path = p;
        }
    }
}
