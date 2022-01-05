import java.util.*;
import java.io.*;
public class painting2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("painting.in"));
        PrintWriter pw = new PrintWriter("painting.out");
        TreeSet<pair> S = new TreeSet<>();
        int N = Integer.parseInt(bf.readLine());
        pair[][] rectangles = new pair[N][2];
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(stk.nextToken());
            int y1 = Integer.parseInt(stk.nextToken());
            int x2 = Integer.parseInt(stk.nextToken());
            int y2 = Integer.parseInt(stk.nextToken());
            rectangles[i][0] = new pair(x1, y1, 0);
            rectangles[i][1] = new pair(x2, y2, 0);
        }
        TreeSet<pair> set = new TreeSet<>(new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                if(o1.x==o2.x){
                    return Integer.compare(o1.z, o2.z);
                }
                return Integer.compare(o2.x, o1.x);
            }
        });
        for(int i = 0; i < N; i++){
            set.add(new pair(rectangles[i][0].x, -1, i));
            set.add(new pair(rectangles[i][1].x, 1, i));
        }
        TreeSet<pair> intervals = new TreeSet<>(new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                return Integer.compare(o1.x, o2.x);
            }
        });
        long answer = 0;
        for(pair i:set){
            pair inter = new pair(rectangles[i.z][0].y, rectangles[i.z][1].y, i.z);
            if(i.y==-1)intervals.remove(inter);
            else{
                pair p = intervals.lower(inter);
                if(p == null){
                    intervals.add(inter);
                    answer++;
                    continue;
                }
                else if(p.y<inter.x){
                    answer++;
                    intervals.add(inter);
                }
            }
        }
        pw.println(answer);
        pw.close();
    }
    public static class pair implements Comparable<pair>{
        int x, y, z;
        public pair(int a, int b, int c){
            x = a;
            y = b;
            z = c;
        }
        @Override
        public int compareTo(pair o) {
            if(x==o.x)return Integer.compare(y, o.y);
            return Integer.compare(x, o.x);
        }
        @Override
        public boolean equals(Object o){
            pair p = ((pair)o);
            if(x == p.x && y == p.y && z == p.z)return true;
            return false;
        }
    }
}