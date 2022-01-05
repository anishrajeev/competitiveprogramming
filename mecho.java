import java.io.*;
import java.util.*;

public class mecho {
    public static LinkedList<pair> q = new LinkedList<>();
    public static int[] dx = new int[]{0, 1, 0, -1};
    public static int[] dy = new int[]{1, 0, -1, 0};
    public static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("tester.in"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int S = Integer.parseInt(stk.nextToken());
        char[][] grid = new char[N][N];
        int[][] bees = new int[N][N];
        visited = new boolean[N][N];
        int startx = 0, starty = 0, homex = 0, homey = 0;
        for(int i = 0; i < N; i++){
            String line = bf.readLine();
            Arrays.fill(bees[i], Integer.MAX_VALUE);
            for(int c = 0; c < N; c++){
                grid[i][c] = line.charAt(c);
                if(grid[i][c] == 'H'){
                    q.add(new pair(i, c, 0));
                    visited[i][c] = true;
                    bees[i][c] = 0;
                }
                if(grid[i][c] == 'M'){
                    startx = i;
                    starty = c;
                }
                if(grid[i][c]=='D'){
                    homex = i;
                    homey = c;
                }
            }
        }
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        while(!q.isEmpty()){
            pair p = q.pop();
            if(p.x == homex && p.y == homey)continue;
            if(grid[p.x][p.y] == 'T')continue;
            bees[p.x][p.y] = Math.min(bees[p.x][p.y], p.t+S);
            for(int i = 0; i < 4; i++){
                int nx = p.x+dx[i];
                int ny = p.y+dy[i];
                if(N <= nx || nx < 0 || N <= ny || ny < 0 || visited[nx][ny])continue;
                q.add(new pair(nx, ny, bees[p.x][p.y]));
                visited[nx][ny] = true;

            }
        }
        bees[homex][homey] = Integer.MAX_VALUE;
        int start = 0, end = 1000000;
        while(start != end){
            int mid = (start+end+1)/2;
            if(verify(bees, grid, mid, startx, starty, homex, homey, N, S))start = mid;
            else end = mid-1;
        }
        if(!verify(bees, grid, 0, startx, starty, homex, homey, N, S))System.out.println(-1);
        else System.out.println(start);
    }
    public static boolean verify(int[][] bees, char[][] grid, int time, int startx, int starty, int homex, int homey, int N, int S){
        q.add(new pair(startx, starty, time*S));
        for(int i = 0; i < N; i++)Arrays.fill(visited[i], false);
        visited[startx][starty] = true;
        while(!q.isEmpty()){
            pair p = q.poll();
            if(p.t >= bees[p.x][p.y])continue;
            for(int i = 0; i < 4; i++){
                int nx = p.x+dx[i];
                int ny = p.y+dy[i];
                if(N <= nx || nx < 0 || N <= ny || ny < 0)continue;
                if(grid[nx][ny]=='T'||grid[nx][ny]=='H')continue;
                if(bees[nx][ny] <= p.t+1 || visited[nx][ny])continue;
                q.add(new pair(nx, ny, p.t+1));
                visited[nx][ny] = true;
            }
        }
        return visited[homex][homey];
    }
    public static class pair implements Comparable<pair>{
        int x, y, t;
        public pair(int a, int b, int c){
            x = a;
            y = b;
            t = c;
        }

        @Override
        public int compareTo(pair o) {
            if(x==o.x&&y==o.y)return Integer.compare(t, o.t);
            if(x==o.x)return Integer.compare(y, o.y);
            return Integer.compare(x, o.x);
        }
        public boolean equals(pair o){
            if(x == o.x && y == o.y && t == o.t)return true;
            return false;
        }
    }
}
