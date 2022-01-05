import java.io.*;
import java.util.*;

public class tractor {
    static int N;
    static boolean[][] visited;
    static int[][] grid;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("tractor.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("tractor.out")));
        N = Integer.parseInt(bf.readLine());
        grid = new int[N][N];
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            for(int c = 0; c < N; c++){
                grid[i][c] = Integer.parseInt(stk.nextToken());
            }
        }
        int start = 0, end = 1000000000;
        while(start!=end){
            int mid = (start+end)/2;
            if(verify(mid))end = mid;
            else start = mid+1;
        }
        pw.println(start);
        pw.close();
    }
    public static boolean verify(int D){
        for(int i = 0; i < N; i++)Arrays.fill(visited[i], false);
        for(int i = 0; i < N; i++){
            for(int c = 0; c < N; c++){
                if(2*dfs(i, c, D)>=N*N){
                    return true;
                }
            }
        }
        return false;
    }
    public static int dfs(int i, int c, int D){
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        if(visited[i][c])return 0;
        visited[i][c] = true;
        int count = 1;
        for(int d = 0; d < 4; d++){
            int nx = i+dx[d];
            int ny = c+dy[d];
            if(!(0<=nx&&nx<N&&0<=ny&&ny<N))continue;
            if(Math.abs(grid[nx][ny]-grid[i][c])>D)continue;
            count+=dfs(nx, ny, D);
        }
        return count;
    }
    public static class point{
        int x, y;
        public point(int a, int b){
            x = a;
            y = b;
        }
    }
}
