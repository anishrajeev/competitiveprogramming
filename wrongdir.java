import java.util.*;
import java.io.*;
public class wrongdir {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("wrongdir.in"));
        PrintWriter pw = new PrintWriter("wrongdir.out");
        String[] directionsstring = bf.readLine().split("");
        int N = directionsstring.length;
        char[] directions = new char[N];
        for(int i = 0; i < N; i++)directions[i] = directionsstring[i].charAt(0);
        /* N E S W */
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        point[] offset = new point[N+1];
        point[] regular = new point[N*2];
        offset[N] = new point(0, 0);
        int i, total=0, x=0, y=0, dir=0, n=0;
        for (i=N-1; i>=0; i--) {
            if (directions[i]=='F') { offset[i] = new point(offset[i+1].x, 1 + offset[i+1].y); }
            if (directions[i]=='L') { offset[i] = new point(-offset[i+1].y, offset[i+1].x); }
            if (directions[i]=='R') { offset[i] = new point(offset[i+1].y, -offset[i+1].x); }
        }
        /* Build a list of all possible destination points */
        for (i=0; i<N; i++) {
            if (directions[i]!='F') {
                regular[n] = new point(x + dx[dir] + rotate_x(dir, offset[i+1]), y + dy[dir] + rotate_y(dir, offset[i+1]));
                n++;
            }
            if (directions[i]!='L') {
                regular[n] = new point(x + rotate_x(left_turn(dir), offset[i+1]), y + rotate_y(left_turn(dir), offset[i+1]));
                n++;
            }
            if (directions[i]!='R') {
                regular[n] = new point(x + rotate_x(right_turn(dir), offset[i+1]), y + rotate_y(right_turn(dir), offset[i+1]));
                regular[n].x = x + rotate_x(right_turn(dir), offset[i+1]);
                regular[n].y = y + rotate_y(right_turn(dir), offset[i+1]);
                n++;
            }
            if (directions[i]=='F') { x += dx[dir]; y += dy[dir]; }
            if (directions[i]=='L') { dir = left_turn(dir); }
            if (directions[i]=='R') { dir = right_turn(dir); }
        }
        Arrays.sort(regular);
        for (i=0; i<2*N; i++) if (i==0 || regular[i].x!=regular[i-1].x || regular[i].y!=regular[i-1].y) total++;
        pw.println(total);
        pw.close();
    }
    public static int right_turn(int dir) { return (dir+1)%4; }
    public static int left_turn(int dir)  { return (dir+3)%4; }
    public static int rotate_x(int dir, point p)
    {
        if (dir==0) return p.x;
        if (dir==1) return p.y;
        if (dir==2) return -p.x;
        if (dir==3) return -p.y;
        return 0;
    }
    public static int rotate_y(int dir, point p)
    {
        if (dir==0) return p.y;
        if (dir==1) return -p.x;
        if (dir==2) return -p.y;
        if (dir==3) return p.x;
        return 0;
    }
    public static class point implements Comparable{
        int x, y;
        public point(int a, int b){
            x = a;
            y = b;
        }
        @Override
        public int compareTo(Object o) {
            if(x!=((point)o).x)return Integer.compare(x, ((point)o).x);
            else return Integer.compare(y, ((point)o).y);
        }
        public boolean equals(point p){
            if(x == p.x && y == p.y)return true;
            return false;
        }
    }
}
