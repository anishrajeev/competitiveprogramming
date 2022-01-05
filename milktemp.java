import java.util.*;
import java.io.*;
public class milktemp {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("milktemp.in"));
        PrintWriter pw = new PrintWriter("milktemp.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int X = Integer.parseInt(stk.nextToken());
        int Y = Integer.parseInt(stk.nextToken());
        int Z = Integer.parseInt(stk.nextToken());
        ArrayList<Interval> intervals = new ArrayList<>();
        int[] A = new int[N+1];
        int[] B = new int[N+1];
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            intervals.add(new Interval(a, b));
            A[i] = a;
            B[i] = b;
        }
        Collections.sort(intervals);
        A[N] = 1000000001;
        B[N] = 1000000001;
        Arrays.sort(A);
        Arrays.sort(B);
        int milk = N*X;
        long answer = N*X;
        int acounter = 0;
        int bcounter = 0;
        for(int i = 0; i < 2*N; i++){
            int a = A[acounter];
            int b = B[bcounter];
            boolean ab = false;
            boolean bb = false;
            int num;
            if(a<=b){
                num = a;
                acounter++;
                ab = true;
            }
            else{
                num = b;
                bcounter++;
                bb = true;
            }
            if(ab)milk+=(Y-X);
            if(bb)milk+=(Z-Y);
            answer = Math.max(answer, milk);
        }
        pw.println(answer);
        pw.close();
    }
    public static class Interval implements Comparable{
        int x, y;
        public Interval(int a, int b){
            x = a;
            y = b;
        }

        @Override
        public int compareTo(Object o) {
            return Integer.compare(x, ((Interval)o).x);
        }
    }
}
