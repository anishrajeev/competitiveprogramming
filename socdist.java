import java.util.*;
import java.io.*;
public class socdist {
    static int N, M;
    static Interval[] ints;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("socdist.in"));
        PrintWriter pw = new PrintWriter("socdist.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        ints = new Interval[M];
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            long a = Long.parseLong(stk.nextToken());
            long b = Long.parseLong(stk.nextToken());
            ints[i] = new Interval(a, b);
        }
        Arrays.sort(ints);
        long start = 0;
        long end = ints[ints.length-1].b;
        while(start!=end){
            long mid = (start+end+1)/2;
            boolean v = verify(mid);
            if(v)start = mid;
            else end = mid-1;
        }
        pw.println(start);
        pw.close();
    }
    public static boolean verify(long distance){
        long currplace = ints[0].a;
        int cowsused = 1;
        for(int i = 0; i < M; i++){
            long a = ints[i].a;
            long b = ints[i].b;
            long next = currplace+distance;
            if(next<=b){
                if(next<=a)currplace=a;
                if(a<next&&next<b)currplace=next;
                if(next==b)currplace=b;
                cowsused++;
                i--;
            }
        }
        return cowsused>=N;
    }
    public static class Interval implements Comparable<Interval>{
        long a, b;
        public Interval(long x, long y){
            a = x;
            b = y;
        }

        @Override
        public int compareTo(Interval o) {
            return Long.compare(a, o.a);
        }
    }
}
