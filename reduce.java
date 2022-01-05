import java.io.*;
import java.util.*;
public class reduce {
    public static void main(String[] args)throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("reduce.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
        int N = Integer.parseInt(bf.readLine());
        ArrayList<Point> xsorted = new ArrayList<>();
        ArrayList<Point> ysorted = new ArrayList<>();
        ArrayList<Point> max = new ArrayList<>();
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            Point p = new Point(a, b);
            xsorted.add(p);
            ysorted.add(p);
        }
        Collections.sort(xsorted, Point.XComparator);
        Collections.sort(ysorted, Point.YComparator);
        ArrayList<Point> right = new ArrayList<>();
        ArrayList<Point> left = new ArrayList<>();
        ArrayList<Point> up = new ArrayList<>();
        ArrayList<Point> down = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            right.add(xsorted.get(xsorted.size()-1-i));
            up.add(ysorted.get(ysorted.size()-1-i));
            down.add(ysorted.get(i));
            left.add(xsorted.get(i));
        }
        for(int i = 0; i < 4; i++){
            for(int c = 0; c < 4; c++){
                for(int z = 0; z < 4; z++){
                    for(int q = 0;  q< 4; q++){
                        int counter = 0;
                        Point r = right.get(i);
                        Point l = left.get(c);
                        Point u = up.get(z);
                        Point d = down.get(q);
                        for(int f = 0; f < N; f++){
                            Point p = xsorted.get(f);
                            if(p.x > r.x || p.x < l.x || p.y > u.y || p.y < d.y) counter++;
                        }
                        if(counter >3)continue;
                        int area = ((r.x-l.x)*(u.y-d.y));
                        ans = Math.min(area, ans);
                    }
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
    public static class Point{
        private int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getX(){return x;}
        public int getY(){return y;}
        public static Comparator<Point> XComparator = new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                int X1 = p1.getX();
                int X2 = p2.getX();
                return Integer.compare(X1,X2);
            }};
        public static Comparator<Point> YComparator = new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                int Y1 = p1.getY();
                int Y2 = p2.getY();
                return Integer.compare(Y1,Y2);
            }};
    }
}
