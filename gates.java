import java.io.*;
import java.util.*;

public class gates {
    static boolean field[][];
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("gates.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));
        int N = Integer.parseInt(bf.readLine());
        field = new boolean[2*N+10][2*N+10];
        int x = N;
        int y = N;
        field[x][y] = true;
        String directions = bf.readLine();
        for(int i = 0; i <= N-1; i++){
            int dY = 0;
            int dX = 0;
            if(directions.charAt(i) == 'N') dY--;
            else if(directions.charAt(i) == 'S') dY++;
            else if(directions.charAt(i) == 'W') dX--;
            else if(directions.charAt(i) == 'E') dX++;
                x+=dX;
                y+=dY;
                field[x][y] = true;
                x+=dX;
                y+=dY;
                field[x][y] = true;
        }
        int ans = -1;
        int[] dx = new int[]{-1,1,0,0};
        int[] dy = new int[]{0,0,-1,1};
        for(int i = 0; i < field.length; i++) {
            for(int c = 0; c < field[i].length; c++) {
                if(field[i][c]) continue;
                ans++;
                Stack<Point> q = new Stack<>();
                q.add(new Point(i, c));
                field[i][c] = true;
                while(!q.isEmpty()) {
                    Point curr = q.pop();
                    for(int z = 0; z < dx.length; z++) {
                        int newx = curr.x + dx[z];
                        int newy = curr.y + dy[z];
                        if(newx >= 0 && newx < field.length && newy >= 0 && newy < field[newx].length && !field[newx][newy]) {
                            field[newx][newy] = true;
                            q.add(new Point(newx, newy));
                        }
                    }
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
    public static class Point{
        private int x;
        private int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getY(){return y;}
        public int getX(){return x;}
    }
}
