import java.util.*;
import java.io.*;
public class radio {
    static ArrayList<Point> John;
    static ArrayList<Point> Bessie;
    static long[][] memo;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("radio.in"));
        PrintWriter pw = new PrintWriter("radio.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        John = new ArrayList<>();
        Bessie = new ArrayList<>();
        memo = new long[1010][1010];
        stk = new StringTokenizer(bf.readLine());
        int fx = Integer.parseInt(stk.nextToken());
        int fy = Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(bf.readLine());
        int bx = Integer.parseInt(stk.nextToken());
        int by = Integer.parseInt(stk.nextToken());
        John.add(new Point(fx, fy));
        Bessie.add(new Point(bx, by));
        HashMap<String, Integer> dx = new HashMap<>();
        HashMap<String, Integer> dy = new HashMap<>();
        int[] dxvalues = new int[]{0, 1, -1, 0};
        int[] dyvalues = new int[]{1, 0, 0, -1};
        String[] stringvalueofdirection = new String[]{"N","E","W","S"};
        for(int i = 0; i < 4; i++){
            dx.put(stringvalueofdirection[i], dxvalues[i]);
            dy.put(stringvalueofdirection[i], dyvalues[i]);
        }
        for(int i = 0; i < 1010; i++)Arrays.fill(memo[i], -1);
        String f = bf.readLine();
        String[] farr = f.split("");
        for(int i = 0; i < N; i++){
            fx+=dx.get(farr[i]);
            fy+=dy.get(farr[i]);
            John.add(new Point(fx, fy));
        }
        String b = bf.readLine();
        String[] barr = b.split("");
        for(int i = 0; i < M; i++){
            bx+=dx.get(barr[i]);
            by+=dy.get(barr[i]);
            Bessie.add(new Point(bx, by));
        }
        long answer = solve(0, 0);
        pw.println(answer);
        pw.close();
    }
    public static long solve(int f, int b){
        long base = (John.get(f).x-Bessie.get(b).x)*(John.get(f).x-Bessie.get(b).x) + (John.get(f).y-Bessie.get(b).y)*(John.get(f).y-Bessie.get(b).y);
        if(f+1 == John.size() && b+1 == Bessie.size())return base;
        long res = memo[f][b];
        if(res!=-1)return res;
        if (f == 0 && b == 0) base = 0;
        res = Integer.MAX_VALUE-10000;
        if(f + 1 < John.size()) res = Math.min(res, base + solve(f+1, b));
        if(b + 1 < Bessie.size()) res = Math.min(res, base + solve(f, b+1));
        if(f + 1 < John.size() && b + 1 < Bessie.size()) res = Math.min(res, base + solve(f+1, b+1));
        memo[f][b]=res;
        return res;
    }
    public static class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
