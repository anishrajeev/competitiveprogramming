import java.io.*;
import java.util.*;

public class ccski {
    static boolean[][] reachable;
    static int[][] grid;
    static int[][] waypoints;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("ccski.in"));
        PrintWriter pw = new PrintWriter("ccski.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int swx = 0;
        int swy = 0;
        grid = new int[N][M];
        reachable = new boolean[N][M];
        waypoints = new int[N][M];
        int waypointcount = 0;
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            for(int c = 0; c < M; c++){
                grid[i][c] = Integer.parseInt(stk.nextToken());
            }
        }
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            for(int c = 0; c < M; c++){
                int num = Integer.parseInt(stk.nextToken());
                if(num==1){
                    swx = i;
                    swy = c;
                    waypoints[i][c] = 1;
                    waypointcount++;
                }
            }
        }
        int start = 0, end = 1000000000;
        while(start != end){
            int mid = (start+end)/2;
            if(verify(mid, N, M, swx, swy, waypointcount))end = mid;
            else start = mid+1;
        }
        pw.println(start);
        pw.close();
    }
    public static boolean verify(int D, int N, int M, int swx, int swy, int waypointcount){
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        for(int i = 0; i < N; i++)Arrays.fill(reachable[i], false);
        ArrayList<pair> q = new ArrayList<>();
        q.add(new pair(swx, swy));
        reachable[swx][swy] = true;
        while(q.size()!=0){
            pair p = q.remove(0);
            for(int i = 0; i < 4; i++){
                int nx = p.x+dx[i];
                int ny = p.y+dy[i];
                if(!(0<=nx&&nx<N&&0<=ny&&ny<M))continue;
                if(Math.abs(grid[nx][ny]-grid[p.x][p.y])>D)continue;
                if(reachable[nx][ny])continue;
                reachable[nx][ny] = true;
                q.add(new pair(nx, ny));
                if(waypoints[nx][ny]==1)waypointcount--;
            }
        }
        return waypointcount==1;
    }
    public static class pair{
        int x, y;
        public pair(int a, int b){
            x = a;
            y = b;
        }
    }
}
