import java.io.*;
import java.util.*;
public class lightson {
    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("lightson.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        boolean[][] barn = new boolean[N][N];
        boolean[][] visited = new boolean[N][N];
        ArrayList<Point>[][] connections = new ArrayList[N][N];
        for(int i = 0; i < N; i++){
            Arrays.fill(barn[i], false);
        }
        barn[0][0]=true;
        for(int i = 0; i < N; i++){
            for(int c = 0; c < N; c++){
                connections[i][c] = new ArrayList<>();
            }
        }
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            int c = Integer.parseInt(stk.nextToken())-1;
            int d = Integer.parseInt(stk.nextToken())-1;
            connections[a][b].add(new Point(c, d));
        }
        ArrayList<Point> arr = connections[0][0];
        for(int i = 0; i < arr.size(); i++){
            Point p = arr.get(i);
            barn[p.a][p.b] = true;
        }
        Stack<Point> s = new Stack<>();
        visited[0][0]=true;
        barn[0][0]=true;
        if(barn[0][1])s.push(new Point(0, 1));
        if(barn[1][0])s.push(new Point(1, 0));
        while(!s.isEmpty()){
            Point p = s.pop();
            int x = p.a;
            int y = p.b;
            if(visited[x][y])continue;
            visited[x][y]=true;
            for(int i = 0; i < connections[x][y].size(); i++){
                barn[connections[x][y].get(i).a][connections[x][y].get(i).b] = true;
                Point p2 = new Point(connections[x][y].get(i).a, connections[x][y].get(i).b);
                if(hasVisitedNeighbor(barn, visited, p2)) s.push(p2);
            }
            for(int i = 0; i < dx.length; i++){
                int newx = x+dx[i];
                int newy = y+dy[i];
                if(newx>=0&&newx<N&&newy>=0&&newy<N&&barn[newx][newy]&&!visited[newx][newy])s.push(new Point(newx, newy));
            }
        }
        int counter = 0;
        for(boolean[] ba:barn){
            for(boolean b:ba){
                if(b)counter++;
            }
        }
        pw.println(counter);
        pw.close();
    }
    public static boolean hasVisitedNeighbor(boolean[][] barn,boolean[][] visited, Point p){
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        int x = p.a;
        int y = p.b;
        for(int i = 0; i < dx.length; i++){
            int newx = x+dx[i];
            int newy = y+dy[i];
            if(newx>=0&&newx<barn.length&&newy>=0&&newy<barn.length&&barn[newx][newy]&&visited[newx][newy]) return true;
        }
        return false;
    }
    public static class Point{
        private int a, b;
        private boolean visited, on;
        public Point(int a, int b){
            this.a = a;
            this.b = b;
        }
    }
}
