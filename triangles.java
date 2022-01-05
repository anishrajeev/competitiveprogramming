import java.util.*;
import java.io.*;
public class triangles {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter pw = new PrintWriter("triangles.out");
        int N = Integer.parseInt(bf.readLine());
        Point[] xy = new Point[N];
        int[][] sumx = new int[20001][20001];
        int[][] sumy = new int[20001][20001];
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            xy[i]=(new Point(x, y));
        }
        Arrays.sort(xy, Point.XComparator);
        for(int i = 0; i < N; i++){
            int j = i+1;
            int tot = 1;
            Point x = transform(xy[i]);
            while(xy[j].x == xy[i].x){
                sumy[x.x][x.y]+=xy[j].y-xy[i].y;
                tot++;
                j++;
            }
            j = i+1;
            int prev = sumy[x.x][x.y];
            while(xy[j].x == xy[i].x){
                Point curr = transform(xy[j]);
                sumy[curr.x][curr.y] = prev + (xy[j].y-xy[j-1].y)*(2*j-2*i-tot+2);
                prev = sumy[curr.x][curr.y];
                j++;
            }
        }
        Arrays.sort(xy, Point.YComparator);
        for(int i = 0; i < N; i++){
            int j = i+1;
            int tot = 1;
            Point x = transform(xy[i]);
            while(xy[j].y == xy[i].y){
                sumx[x.x][x.y]+=xy[j].x-xy[i].x;
                tot++;
                j++;
            }
            j = i+1;
            int prev = sumx[x.x][x.y];
            while(xy[j].x == xy[i].x){
                Point curr = transform(xy[j]);
                sumx[curr.x][curr.y] = prev + (xy[j].y-xy[j-1].y)*(2*j-2*i-tot+2);
                prev = sumx[curr.x][curr.y];
                j++;
            }
        }
        int answer = 0;
        for(int i = 0; i < N; i++){
            Point x = transform(xy[i]);
            answer+=sumx[x.x][x.y]*sumy[x.x][x.y];
        }
        pw.println(answer);
        pw.close();
    }
    public static Point transform(Point xy){
        return new Point(xy.x+10000, xy.y+10000);
    }
    public static class Point{
        int x, y;
        public Point(int a, int b){
            x = a;
            y = b;
        }
        public static Comparator<Point> XComparator = new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                int x1 = p1.x;
                int x2 = p2.x;
                if(x1==x2){
                    return Integer.compare(p1.y, p2.y);
                }
                return Integer.compare(x1, x2);
            }};
        public static Comparator<Point> YComparator = new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                int y1 = p1.y;
                int y2 = p2.y;
                if(y1==y2){
                    return Integer.compare(p1.x, p2.x);
                }
                return Integer.compare(y1, y2);
            }};
        @Override
        public String toString(){
            return x+":"+y;
        }
        public boolean equals(Point p){
            return x==p.x&&y==p.y;
        }
    }
}
