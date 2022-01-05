import java.util.*;
import java.io.*;
public class gravity {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("gravity.in"));
        PrintWriter pw = new PrintWriter("gravity.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        char[][] grid = new char[N][M];
        for(int i = 0; i < N; i++){
            String[] arr = bf.readLine().split("");
            for(int c = 0; c < M; c++){
                grid[i][c] = arr[c].charAt(0);
            }
        }
        int[][] up = new int[N][M];
        int[][] down = new int[N][M];
        for(int i = 0; i < N; i++){
            Arrays.fill(up[i], -1);
            Arrays.fill(down[i], -1);
        }
        for(int i = 0; i < N; i++){
            for(int c = 0; c < M; c++){
                if(grid[i][c]=='#')continue;
                if(grid[i][c]=='D')down[i][c] = i;
                if(down[i][c]!=-1)continue;
                for(int d = i+1; d < N; d++){
                    if(grid[d][c]=='#'){
                        down[i][c] = d-1;
                        break;
                    }
                    if(down[i][c]==-1&&grid[d][c]=='D'){
                        down[i][c] = d;
                        break;
                    }
                }
            }
        }
        for(int i = 0; i < N; i++){
            for(int c = 0; c < M; c++){
                if(grid[i][c]=='#')continue;
                if(grid[i][c]=='D')up[i][c] = i;
                if(up[i][c]!=-1)continue;
                for(int d = i-1; d >= 0; d--){
                    if(grid[d][c]=='#'){
                        up[i][c] = d+1;
                        break;
                    }
                    if(up[i][c]==-1&&grid[d][c]=='D'){
                        up[i][c] = d;
                        break;
                    }
                }
            }
        }
        pair[][] dp = new pair[N][M];
        PriorityQueue<pair> pq = new PriorityQueue<>(new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                return Integer.compare(o1.flips, o2.flips);
            }
        });
        int CX = 0, CY = 0;
        for(int i = 0; i < N; i++){
            for(int c = 0; c < M; c++){
                dp[i][c] = new pair(Integer.MAX_VALUE, Integer.MAX_VALUE, i, c);
                if(grid[i][c]=='C'){
                    CX = i;
                    CY = c;
                }
            }
        }
        int DX = 0, DY = 0;
        for(int i = 0; i < N; i++){
            for(int c = 0; c < M; c++){
                if(grid[i][c]=='D'){
                    DX = i;
                    DY = c;
                    break;
                }
            }
        }
        dp[CX][CY] = new pair(0, 0, CX, CY);
        pq.add(dp[CX][CY]);
        boolean[][][] visited = new boolean[N][M][2];
        while(!pq.isEmpty()){
            pair p = pq.poll();
            int nx = p.x;
            if(p.gravity==0)nx = down[p.x][p.y];
            if(p.gravity==1)nx = up[p.x][p.y];
            p = new pair(p.flips, p.gravity, nx, p.y);
            if(!(0<=p.x&&p.x<N)||!(0<=p.y&&p.y<M))continue;
            if(visited[p.x][p.y][p.gravity]){
                if(p.flips >= dp[p.x][p.y].flips)continue;
            }
            if(p.flips < dp[p.x][p.y].flips) dp[p.x][p.y] = p;
            visited[p.x][p.y][p.gravity] = true;
            int[] dy = new int[]{1, -1};
            for(int d = 0; d < 2; d++){
                int newx = p.x;
                int newy = p.y + dy[d];
                if(!(0<=newx&&newx<N)||!(0<=newy&&newy<M))continue;
                if(p.gravity==0)newx = down[newx][newy];
                if(p.gravity==1)newx = up[newx][newy];
                if(!(0<=newx&&newx<N)||!(0<=newy&&newy<M))continue;
                if(grid[newx][newy]=='#')continue;
                pq.add(new pair(p.flips, p.gravity, newx, newy));
                //if(newx == 9 && newy == 6)System.out.println(p.x + ", " + p.y + " Flips: " + p.flips + " Gravity: " + p.gravity);
            }
            if(p.gravity==0){
                int graveff = up[p.x][p.y];
                if(graveff == -1)continue;
                pq.add(new pair(p.flips+1, 1, graveff, p.y));
                //if(graveff == 9 && p.y == 6)System.out.println(p.x + ", " + p.y + " Flips: " + p.flips + " Gravity: " + p.gravity);
            }
            else if(p.gravity==1){
                int graveff = down[p.x][p.y];
                if(graveff == -1)continue;
                pq.add(new pair(p.flips+1, 0, graveff, p.y));
                //if(graveff == 9 && p.y == 6)System.out.println(p.x + ", " + p.y + " Flips: " + p.flips + " Gravity: " + p.gravity);
            }
        }
        int answer = dp[DX][DY].flips;
        if(answer==Integer.MAX_VALUE)answer = -1;
        pw.println(answer);
        pw.close();
    }
    public static class pair{
        int flips, gravity, x, y;
        //0 == down && 1 == up
        public pair(int a, int b, int ax, int ay){
            flips = a;
            gravity = b;
            x = ax;
            y = ay;
        }
    }
}
