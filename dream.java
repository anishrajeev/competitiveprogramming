import java.util.*;
import java.io.*;
public class dream {
    static int N;
    static int M;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("dream.in"));
        PrintWriter pw = new PrintWriter("dream.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        //NESW
        int[][] d = new int[N][M];
        int[][] board = new int[N][M];
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            for(int c = 0; c < M; c++){
                board[i][c] = Integer.parseInt(stk.nextToken());
            }
        }
        for(int i = 0; i < N; i++)Arrays.fill(d[i], Integer.MAX_VALUE);
        d[0][0] = 0;
        if(board[0][0]==0||board[0][0]==3){
            pw.println(-1);
            pw.close();
        }
        PriorityQueue<State> pq = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                return Integer.compare(d[o1.x][o1.y], d[o2.x][o1.y]);
            }
        });
        pq.add(new State(0,0,0,0,board[0][0] == 2));
        boolean[][][][] visited = new boolean[N][M][4][2];
        while(!pq.isEmpty()){
            State s = pq.poll();
            //System.out.println("xcoordinate: " + s.x + " ycoordinate: " + s.y + " counter: " + s.counter);
            boolean smell = s.isSmell;
            if(board[s.x][s.y]==2)smell = true;
            if(board[s.x][s.y]==4)smell = false;
            if(s.counter<d[s.x][s.y]){
                pq.remove(s);
                d[s.x][s.y]=s.counter;
            }
            else if(visited[s.x][s.y][s.orientation][smell?0:1])continue;
            visited[s.x][s.y][s.orientation][smell?0:1] = true;
            boolean b = false;
            if(board[s.x][s.y]==4){
                int newx = s.x+dx[s.orientation];
                int newy = s.y+dy[s.orientation];
                if(!(newx>=N||newx<0||newy>=M||newy<0)){
                    if(board[newx][newy]==0||(!smell&&board[newx][newy]==3))b = true;
                    else{
                        pq.add(new State(newx, newy, s.orientation, s.counter+1, smell));
                    }
                }
            }
            if(b||board[s.x][s.y]!=4){
                for(int i = 0; i < 4; i++){
                    int newx = s.x+dx[i], newy = s.y+dy[i];
                    if(!(newx>=N||newx<0||newy>=M||newy<0)){
                        if(board[newx][newy]==0||(!smell&&board[newx][newy]==3))continue;
                        pq.add(new State(newx, newy, i, s.counter+1, smell));
                    }
                }
            }
        }
        //for(int i = 0; i < N; i++)System.out.println(Arrays.toString(d[i]));
        if(d[N-1][M-1]==Integer.MAX_VALUE)pw.println(-1);
        else pw.println(d[N-1][M-1]);
        pw.close();
    }
    public static class State{
        int x, y, orientation, counter;
        boolean isSmell;
        public State(int x, int y, int o, int c, boolean b){
            this.x = x;
            this.y = y;
            orientation = o;
            counter = c;
            isSmell = b;
        }
    }
}
