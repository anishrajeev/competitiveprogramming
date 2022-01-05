import java.util.*;
import java.io.*;
public class mootube {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("mootube.in"));
        PrintWriter pw = new PrintWriter("mootube.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int Q = Integer.parseInt(stk.nextToken());
        ArrayList<Edge>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++){
            graph[i]=new ArrayList<>();
        }
        for(int i = 0; i < N-1; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            int r = Integer.parseInt(stk.nextToken());
            graph[a].add(new Edge(b, r));
            graph[b].add(new Edge(a, r));
        }
        for(int i = 0; i < Q; i++){
            stk = new StringTokenizer(bf.readLine());
            int K = Integer.parseInt(stk.nextToken());
            int a = Integer.parseInt(stk.nextToken())-1;
            int count = 0;
            boolean[] visited = new boolean[N];
            visited[a]=true;
            LinkedList<Edge> q = new LinkedList<>();
            for(int c = 0; c < graph[a].size(); c++){
                if(graph[a].get(c).w>=K){
                    q.push(graph[a].get(c));
                    count++;
                }
            }
            while(!q.isEmpty()){
                Edge e = q.pop();
                int num = e.d;
                visited[num]=true;
                for(int c = 0; c < graph[num].size(); c++){
                    if(graph[num].get(c).w >= K&&!visited[graph[num].get(c).d]){
                        count++;
                        q.push(graph[num].get(c));
                    }
                }
            }
            pw.println(count);
        }
        pw.close();
    }
    public static class Edge{
        int d, w;
        public Edge(int x, int y){
            d = x;
            w = y;
        }
    }
}
