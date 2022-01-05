import java.util.*;
import java.io.*;
public class balancing {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("balancing.in"));
        PrintWriter pw = new PrintWriter("balancing.out");
        int N = Integer.parseInt(bf.readLine());
        ArrayList<Point> cows = new ArrayList<>();
        ArrayList<Integer> Xpoints = new ArrayList<>();
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(stk.nextToken()), y = Integer.parseInt(stk.nextToken());
            cows.add(new Point(x, y));
            Xpoints.add(x);
        }
        int answer = cows.size();
        Collections.sort(cows, Point.xsort);
        Collections.sort(Xpoints);
        for(int i = 0; i < N; i++) {
            ArrayList<Integer> below = new ArrayList<>();
            ArrayList<Integer> above = new ArrayList<>();
            for(int j = 0; j < N; j++) {
                if(cows.get(j).y <= cows.get(i).y) {
                    below.add(cows.get(j).x);
                }
                else {
                    above.add(cows.get(j).x);
                }
            }
            for(int c = 0; c < Xpoints.size(); c++){
                int Xline = Xpoints.get(c);
                int index = c;
                while(index<Xpoints.size() && Xpoints.get(index)==Xline)index++;
                int topleft = next(above, Xline)+1;
                int topright = above.size()-topleft;
                int bottomleft = next(below, Xline)+1;
                int bottomright = below.size()-bottomleft;
                answer = Math.min(Math.max(Math.max(topleft, topright), Math.max(bottomleft, bottomright)), answer);
            }
        }
        pw.println(answer);
        pw.close();
    }
    private static int next(ArrayList<Integer> arr, int target)
    {
        int start = 0, end = arr.size()-1;

        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr.get(mid) >= target) {
                end = mid - 1;
            }
            else {
                ans = mid;
                start = mid + 1;
            }
        }
        return ans;
    }
    public static class Point{
        int x, y;
        public Point(int a, int b){
            x = a;
            y = b;
        }
        public static Comparator<Point> xsort = new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                int x1 = p1.x;
                int x2 = p2.x;
                return Integer.compare(x1, x2);
            }};
    }
}
