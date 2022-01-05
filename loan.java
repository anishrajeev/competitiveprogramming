import java.io.*;
import java.util.StringTokenizer;

public class loan {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("loan.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("loan.out")));
        long N, M, K;
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        N = Long.parseLong(stk.nextToken());
        K = Long.parseLong(stk.nextToken());
        M = Long.parseLong(stk.nextToken());
        int start = 0, end = 1000000000;
        while(start!=end){
            int mid = (start+end)/2;
            if(verify(N, M, K, mid))start = mid;
            else end = mid-1;
        }
        pw.println(start);
        pw.close();
    }
    public static boolean verify(long N, long M, long K, int X){
        double fraction = (1.0*N)/(M);
        double Ddouble = Math.log(fraction)/Math.log(X);
        long D = (long)Ddouble;
        long later = M*(K-D);
        int before = (int)(N-(N*1.0/(Math.pow(X, D))));
        return (before+later>=N);
    }
}
