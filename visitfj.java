import java.util.*;
import java.io.*;
public class visitfj {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("visitfj.in"));
        PrintWriter pw = new PrintWriter("visitfj.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int T = Integer.parseInt(stk.nextToken());
        int[][] grid = new int[N][N];
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            for(int c = 0; c < N; c++){
                int num = Integer.parseInt(stk.nextToken());
                grid[i][c] = num;
            }
        }
        state[][] dp = new state[N][N];
        for(int i = 0; i < N; i++)for(int c = 0; c < N; c++)dp[i][c] = new state(Integer.MAX_VALUE, i, c, 0);
        dp[0][0] = new state(0, 0, 0, 0);
        PriorityQueue<state> pq = new PriorityQueue<>(new Comparator<state>() {
            @Override
            public int compare(state o1, state o2) {
                return Integer.compare(o1.time, o2.time);
            }
        });
        pq.add(dp[0][0]);
        boolean[][][] visited = new boolean[N][N][4];
        while(!pq.isEmpty()){
            state s = pq.poll();
            if(s.time < dp[s.x][s.y].time)dp[s.x][s.y] = s;
            else if(visited[s.x][s.y][s.position])continue;
            visited[s.x][s.y][s.position] = true;
            int[] dx = new int[]{0, 1, 0, -1};
            int[] dy = new int[]{1, 0, -1, 0};
            for(int i = 0; i < 4; i++){
                int nx = s.x + dx[i];
                int ny = s.y + dy[i];
                int np = s.position+1;
                int nt = s.time + T;
                if(nx < 0 || nx >= N || ny < 0 || ny >= N)continue;
                if(np == 4)np = 1;
                if(np == 3)nt += grid[nx][ny];
                pq.add(new state(nt, nx, ny, np));
            }
        }
        pw.println(dp[N-1][N-1].time);
        pw.close();
    }
    public static class state {
        int time, x, y, position;
        public state(int t, int x, int y, int p){
            time = t;
            this.x = x;
            this.y = y;
            position = p;
        }
    }
    public static state min(state s1, state s2){
        if(s1.time < s2.time)return s1;
        else return s2;
    }
}
