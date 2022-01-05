import java.util.*;
import java.io.*;
public class lasers {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("lasers.in"));
        PrintWriter pw = new PrintWriter("lasers.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int x1 = Integer.parseInt(stk.nextToken());
        int y1 = Integer.parseInt(stk.nextToken());
        int x2 = Integer.parseInt(stk.nextToken());
        int y2 = Integer.parseInt(stk.nextToken());
        TreeMap<Line, Integer> dist = new TreeMap<>();
        LinkedList<Line> q = new LinkedList<Line>();
        q.add(new Line(y1, true));
        dist.put(new Line(y1, true), 0);
        q.add(new Line(x1, false));
        dist.put(new Line(x1, false), 0);
        Map<Integer, ArrayList<Integer>> xtoY = new TreeMap<>();
        Map<Integer, ArrayList<Integer>> ytoX = new TreeMap<>();
        for(int i = 0; i < N; i++) {
            stk = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            if(!xtoY.containsKey(x)) {
                xtoY.put(x, new ArrayList<>());
            }
            xtoY.get(x).add(y);
            if(!ytoX.containsKey(y)) {
                ytoX.put(y, new ArrayList<>());
            }
            ytoX.get(y).add(x);
        }
        int ret = -1;
        while(!q.isEmpty()) {
            Line curr = q.removeFirst();
            if(curr.horizontal && curr.val == y2) {
                ret = dist.get(curr);
                break;
            }
            if(!curr.horizontal && curr.val == x2) {
                ret = dist.get(curr);
                break;
            }
            Map<Integer, ArrayList<Integer>> source = curr.horizontal ? ytoX : xtoY;
            if(source.containsKey(curr.val)) {
                for(int dest: source.get(curr.val)) {
                    Line nextLine = new Line(dest, !curr.horizontal);
                    if(!dist.containsKey(nextLine)) {
                        dist.put(nextLine, dist.get(curr) + 1);
                        q.add(nextLine);
                    }
                }
            }
        }
        pw.println(ret);
        pw.close();
    }
    public static class Line implements Comparable<Line>{
        int val;
        boolean horizontal;
        public Line(int val, boolean horizontal) {
            this.val = val;
            this.horizontal = horizontal;
        }
        @Override
        public int compareTo(Line l) {
            return Integer.compare(val, l.val);
        }
    }
}
