import java.util.*;
import java.io.*;
public class distant {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("distant.in"));
        PrintWriter pw = new PrintWriter("distant.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int A = Integer.parseInt(stk.nextToken());
        int B = Integer.parseInt(stk.nextToken());
        int[][] graph = new int[N][N];
        long[][] d = new long[N][N];
        for(int i = 0; i < N; i++){
            String str = bf.readLine();
            String[] strarrsplit = str.split("");
            for(int c = 0; c < N; c++){
                if(strarrsplit[c].equals("("))graph[i][c] = 0;
                else graph[i][c] = 1;
            }
        }
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        //NESW
        long max = 0;
        for(int startx = 0; startx < N; startx++){
            for(int starty = 0; starty < N; starty++){
                for(int i = 0; i < N; i++)Arrays.fill(d[i], Long.MAX_VALUE);
                d[startx][starty] = 0;
                TreeSet<pair> visited = new TreeSet<>();
                PriorityQueue<pair> pq = new PriorityQueue<>(new Comparator<pair>() {
                    @Override
                    public int compare(pair p1, pair p2) {
                        return Long.compare(d[p1.x][p1.y], d[p2.x][p2.y]);
                    }
                });
                pq.add(new pair(startx, starty, startx, starty));
                while(!pq.isEmpty()){
                    pair p = pq.poll();
                    if(visited.contains(p))continue;
                    visited.add(p);
                    for(int i = 0; i < 4; i++){
                        int newx = p.x+dx[i];
                        int newy = p.y+dy[i];
                        if(0<=newx&&newx<N&&0<=newy&&newy<N){
                            long newvalue = d[p.x][p.y] + ((graph[p.x][p.y]==graph[newx][newy])?A:B);
                            if(newvalue<d[newx][newy])d[newx][newy] = newvalue;
                            pq.add(new pair(newx, newy, p.x, p.y));
                        }
                    }
                }
                for(int i = 0; i < N; i++){
                    for(int c = 0; c < N; c++){
                        max = Math.max(max, d[i][c]);
                    }
                }
            }
        }
        pw.println(max);
        pw.close();
    }
    public static class pair implements Comparable<pair>{
        int x, y, parx, pary;
        public pair(int a, int b, int px, int py){
            x = a;
            y = b;

        }
        @Override
        public int compareTo(pair p) {
            if(x!=p.x)return Integer.compare(x, p.x);
            return Integer.compare(y, p.y);
        }

        @Override
        public boolean equals(Object obj) {
            pair p = (pair)obj;
            if(x==p.x&&y==p.y)return true;
            return false;
        }
    }
}
