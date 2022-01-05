import java.util.*;
import java.io.*;
public class hps {
    public static void main(String[] args)throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter("hps.out");
        int N = Integer.parseInt(bf.readLine());
        int[] H = new int[N];
        int[] P = new int[N];
        int[] S = new int[N];
        int hcount = 0;
        int pcount = 0;
        int scount = 0;
        int max = 0;
        for(int i = 0; i < N; i++){
            String s = bf.readLine();
            if(s.equals("H")) hcount++;
            else if(s.equals("P")) pcount++;
            else if(s.equals("S")) scount++;
            H[i] = hcount;
            P[i] = pcount;
            S[i] = scount;
        }
        for(int i = 1; i < N; i++){
            //PART 1
            int h = H[i-1];
            int p = P[i-1];
            int s = S[i-1];
            int part1max = Math.max(h,Math.max(p,s));
            //PART 2
            h = H[N-1]-H[i-1];
            p = P[N-1]-P[i-1];
            s = S[N-1]-S[i-1];
            int part2max = Math.max(h,Math.max(p,s));
            max = Math.max(max, (part1max+part2max));
        }
        pw.println(max);
        pw.close();
    }
}
