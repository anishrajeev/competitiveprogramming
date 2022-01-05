import java.io.*;
import java.util.*;
public class fenceplan {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("fenceplan.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        ArrayList<Integer>[] graph = new ArrayList[N];
        ArrayList<Point> cows = new ArrayList<>();
        boolean[] visited = new boolean[N];
        int minperm = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            cows.add(new Point(a, b));
        }
        for(int i = 0; i < N; i++)graph[i]=new ArrayList<>();
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            graph[a].add(b);
            graph[b].add(a);
        }
        for(int i = 0; i < N; i++){
            if(visited[i])continue;
            int minx = Integer.MAX_VALUE, maxx = 0, miny = Integer.MAX_VALUE, maxy = 0;
            Stack<Integer> s = new Stack<>();
            for(int c = 0; c < graph[i].size(); c++){
                if(visited[graph[i].get(c)])continue;
                Point p = cows.get(graph[i].get(c));
                minx = Math.min(minx, p.x);
                maxx = Math.max(maxx, p.x);
                miny = Math.min(miny, p.y);
                maxy = Math.max(maxy, p.y);
                s.push(graph[i].get(c));
            }
            while(!s.isEmpty()){
                int num = s.pop();
                visited[num]=true;
                for(int c = 0; c < graph[num].size(); c++){
                    if(visited[graph[num].get(c)])continue;
                    Point p = cows.get(graph[num].get(c));
                    minx = Math.min(minx, p.x);
                    maxx = Math.max(maxx, p.x);
                    miny = Math.min(miny, p.y);
                    maxy = Math.max(maxy, p.y);
                    s.push(graph[num].get(c));
                }
            }
            int p = 2*(maxx-minx)+2*(maxy-miny);
            minperm = Math.min(minperm, p);
        }
        pw.println(minperm);
        pw.close();
    }
    public static class Point{
        private int x;
        private int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}