import java.util.*;
import java.io.*;
public class multimoo {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("multimoo.in"));
        PrintWriter pw = new PrintWriter("multimoo.out");
        int N = Integer.parseInt(bf.readLine());
        int[][] board = new int[N][N];
        int[][] boardtruecolor = new int[N][N];
        ArrayList<Integer> size = new ArrayList<>();
        int teamanswer = 0;
        int singleanswer = 0;
        HashMap<Integer, Integer> newtoog = new HashMap<>();
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            for(int c = 0; c < N; c++){
                board[i][c]=Integer.parseInt(stk.nextToken());
                boardtruecolor[i][c]=board[i][c];
            }
        }
        boolean[][] visited = new boolean[N][N];
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        int counter = -1;
        for(int i = 0; i < N; i++){
            for(int c = 0; c < N; c++){
                if(visited[i][c])continue;
                visited[i][c]=true;
                int color = board[i][c];
                counter++;
                board[i][c]=counter;
                newtoog.put(counter, color);
                int componentsize = 1;
                Stack<Point> s = new Stack<>();
                for(int d = 0; d < dx.length; d++){
                    int newx = i+dx[d];
                    int newy = c+dy[d];
                    if(newx>=0&&newx<N&&newy>=0&&newy<N&&!visited[newx][newy]&&board[newx][newy]==color){
                        s.push(new Point(newx, newy));
                    }
                }
                while(!s.isEmpty()){
                    Point p = s.pop();
                    if(visited[p.x][p.y])continue;
                    componentsize++;
                    visited[p.x][p.y]=true;
                    board[p.x][p.y]=counter;
                    for(int d = 0; d < dx.length; d++){
                        int newx = p.x+dx[d];
                        int newy = p.y+dy[d];
                        if(newx>=0&&newx<N&&newy>=0&&newy<N&&!visited[newx][newy]&&board[newx][newy]==color){
                            s.push(new Point(newx, newy));
                        }
                    }
                }
                size.add(componentsize);
                singleanswer = Math.max(singleanswer, componentsize);
            }
        }
        TreeSet<Integer>[] neighbors = new TreeSet[counter+1];
        for(int i = 0; i < counter+1; i++)neighbors[i]=new TreeSet<>();
        boolean[][] visited2 = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int c = 0; c < N;c++){
                if(visited2[i][c])continue;
                visited2[i][c]=true;
                int color = board[i][c];
                Stack<Point> s = new Stack<>();
                for(int d = 0; d < dx.length; d++){
                    int newx = i+dx[d];
                    int newy = c+dy[d];
                    if(newx>=0&&newx<N&&newy>=0&&newy<N&&board[newx][newy]==color&&!visited2[newx][newy]){
                        s.push(new Point(newx, newy));
                    }
                    else if(newx>=0&&newx<N&&newy>=0&&newy<N&&board[newx][newy]!=color){
                        neighbors[color].add(board[newx][newy]);
                    }
                }
                while(!s.isEmpty()){
                    Point p = s.pop();
                    if(visited2[p.x][p.y])continue;
                    visited2[p.x][p.y]=true;
                    for(int d = 0; d < dx.length; d++){
                        int newx = p.x+dx[d];
                        int newy = p.y+dy[d];
                        if(newx>=0&&newx<N&&newy>=0&&newy<N&&board[newx][newy]==color&&!visited2[newx][newy]){
                            s.push(new Point(newx, newy));
                        }
                        else if(newx>=0&&newx<N&&newy>=0&&newy<N&&board[newx][newy]!=color){
                            neighbors[color].add(board[newx][newy]);
                        }
                    }
                }
            }
        }
        Stack<Integer> s = new Stack<>();
        boolean[] visited3 = new boolean[counter+1];
        for(int i = 0; i < counter+1; i++){
            if(visited3[i])continue;
            visited3[i] = true;
            for(int c:neighbors[i]){
                int componentsize = size.get(c)+size.get(i);
                int color1 = newtoog.get(i);
                int color2 = newtoog.get(c);
                s.push(c);
                s.push(i);
                while(!s.isEmpty()){
                    int num = s.pop();
                    for(int d:neighbors[num]){
                        if((color1==newtoog.get(d)||color2==newtoog.get(d))&&d!=c&&!visited3[d]){
                            componentsize+=size.get(d);
                            s.push(d);
                            visited3[d]=true;
                        }
                    }
                }
                teamanswer = Math.max(teamanswer, componentsize);
            }
        }
        pw.println(singleanswer);
        pw.println(teamanswer);
        pw.close();
    }
    public static class Point implements Comparable{
        int x, y;
        public Point(int a, int b){
            x = a;
            y = b;
        }

        @Override
        public int compareTo(Object o) {
            return Integer.compare(x, ((Point)o).x);
        }
    }
}
