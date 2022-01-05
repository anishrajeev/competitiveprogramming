import java.io.*;
import java.util.*;

public class loan2 {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("loan.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("loan.out")));
        long N, M, K;
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        N = Long.parseLong(stk.nextToken());
        K = Long.parseLong(stk.nextToken());
        M = Long.parseLong(stk.nextToken());
        int start = 1, end = 1000000000;
        while(start!=end){
            int mid = (start+end+1)/2;
            if(verify(N, M, K, mid))start = mid;
            else end = mid-1;
        }
        pw.println(start);
        pw.close();
    }
    public static boolean verify(long N, long M, long K, int X){
        int G = 0;
        long Y = (long)(N-G)/X;
        long counter = 0;
        while(Y > M){
            G += Y;
            counter++;
            Y = (long)(N-G)/X;
        }
        long daysremaining = K-counter;
        G+=daysremaining*M;
        return G >= N;
    }
}
