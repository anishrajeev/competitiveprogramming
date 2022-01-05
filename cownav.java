import java.util.*;
import java.io.*;
public class cownav {
    static int[] dx = new int[]{-1,0,1,0};
    static int[] dy = new int[]{0,1,0,-1};
    static char[][] grid;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("cownav.in"));
        PrintWriter pw = new PrintWriter("cownav.out");
        N = Integer.parseInt(bf.readLine());
        grid = new char[N][N];
        for(int i = 0; i < N; i++) {
            grid[i] = bf.readLine().toCharArray();
        }
        int[][][][][][] dp = new int[N][N][4][N][N][4];
        dp[N-1][0][0][N-1][0][1] = 1;
        LinkedList<State> q = new LinkedList<>();
        q.push(new State(N-1, 0, 0, N-1, 0, 1));
        while(!q.isEmpty()){
            State curr = q.removeFirst();
            if(curr.ax == 0 && curr.bx == 0 && curr.ay == N-1 && curr.by == N-1) {
                pw.println(dp[curr.ax][curr.ay][curr.ad][curr.bx][curr.by][curr.bd] - 1);
                break;
            }
            for(State next:curr.genTransitions()){
                if(dp[next.ax][next.ay][next.ad][next.bx][next.by][next.bd] == 0){
                    dp[next.ax][next.ay][next.ad][next.bx][next.by][next.bd] = dp[curr.ax][curr.ay][curr.ad][curr.bx][curr.by][curr.bd] + 1;
                    q.add(next);
                }
            }
        }
        pw.close();
    }
    public static class State{
        int ax, ay, ad, bx, by, bd;
        public State(int ax, int ay, int ad, int bx, int by, int bd) {
            this.ax = ax;
            this.ay = ay;
            this.ad = ad;
            this.bx = bx;
            this.by = by;
            this.bd = bd;
        }
        private State turnLeft() {
            return new State(ax, ay, (ad+3)%4, bx, by, (bd+3)%4);
        }
        private State turnRight() {
            return new State(ax, ay, (ad+1)%4, bx, by, (bd+1)%4);
        }
        private State advance() {
            int nax = ax;
            int nay = ay;
            if(nax != 0 || nay != N-1) {
                if(nax + dx[ad] >= 0 && nax + dx[ad] < N) {
                    nax += dx[ad];
                }
                if(nay + dy[ad] >= 0 && nay + dy[ad] < N) {
                    nay += dy[ad];
                }
                if(grid[nax][nay] == 'H') {
                    nax = ax;
                    nay = ay;
                }
            }
            int nbx = bx;
            int nby = by;
            if(nbx != 0 || nby != N-1) {
                if(nbx + dx[bd] >= 0 && nbx + dx[bd] < N) {
                    nbx += dx[bd];
                }
                if(nby + dy[bd] >= 0 && nby + dy[bd] < N) {
                    nby += dy[bd];
                }
                if(grid[nbx][nby] == 'H') {
                    nbx = bx;
                    nby = by;
                }
            }
            return new State(nax, nay, ad, nbx, nby, bd);
        }
        public State[] genTransitions() {
            return new State[]{turnLeft(), turnRight(), advance()};
        }
    }
}
