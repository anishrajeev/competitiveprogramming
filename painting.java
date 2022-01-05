import java.util.*;
import java.io.*;
public class painting {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("painting.in"));
        PrintWriter pw = new PrintWriter("painting.out");
        TreeSet<Rectangle> big = new TreeSet<>(new Comparator<Rectangle>() {
            @Override
            public int compare(Rectangle o1, Rectangle o2) {
                return Long.compare(o1.getArea(), o2.getArea());
            }
        });
        PriorityQueue<Rectangle> q = new PriorityQueue<>(new Comparator<Rectangle>() {
            @Override
            public int compare(Rectangle o1, Rectangle o2) {
                return Long.compare(o2.getArea(), o1.getArea());
            }
        });

        int N = Integer.parseInt(bf.readLine());
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            long x1 = Long.parseLong(stk.nextToken()), y1 = Long.parseLong(stk.nextToken()), x2 = Long.parseLong(stk.nextToken()), y2 = Long.parseLong(stk.nextToken());
            q.add(new Rectangle(x1, y1, x2, y2));
        }
        while(!q.isEmpty()){
            Rectangle rec = q.poll();
            long area = rec.getArea();
            int index = binsearch(area, big);
            if(index==-1){
                big.add(rec);
                continue;
            }
            else{
                ArrayList<Rectangle> arr = new ArrayList<>();
                arr.addAll(big);
                boolean ind = true;
                for(int i = index; i < arr.size(); i++){
                    Rectangle bigger = arr.get(i);
                    if(bigger.x1<rec.x1&&bigger.y1<rec.y1&&bigger.x2>rec.x2&&bigger.y2>rec.y2){
                        ind = false;
                        break;
                    }
                }
                if(ind)big.add(rec);
            }
        }
        pw.println(big.size());
        pw.close();
    }
    public static int binsearch(long area, TreeSet<Rectangle> big){
        ArrayList<Rectangle> arr = new ArrayList<>();
        arr.addAll(big);
        int start = 0, end = arr.size()-1;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr.get(mid).getArea() <= area) {
                start = mid + 1;
            }
            else {
                ans = mid;
                end = mid - 1;
            }
        }
        return ans;
    }
    public static class Rectangle{
        long x1, y1, x2, y2;
        public Rectangle(long xp1, long yp1, long xp2, long yp2){
            x1 = xp1;
            y1 = yp1;
            x2 = xp2;
            y2 = yp2;
        }
        public long getArea(){
            return (Math.abs(x1-x2)*Math.abs(y1-y2));
        }
    }
}
