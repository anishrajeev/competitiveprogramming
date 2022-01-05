import java.io.*;
import java.util.*;

public class bcount2 {
    public static void main(String[] args)throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("bcount.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int Q = Integer.parseInt(stk.nextToken());
        int[] H = new int[N+1];
        int[] G = new int[N+1];
        int[] J = new int[N+1];
        int hcount = 0;
        int gcount = 0;
        int jcount = 0;
        Arrays.fill(H, 0);
        Arrays.fill(G, 0);
        Arrays.fill(J, 0);
        for(int i = 1; i <= N; i++){
            int num = Integer.parseInt(bf.readLine());
            if(num == 1)hcount++;
            if(num == 2)gcount++;
            if(num == 3)jcount++;
            H[i]=hcount;
            G[i]=gcount;
            J[i]=jcount;
        }
        for(int i = 0; i < Q; i++){
            String ans = "";
            StringTokenizer stkq = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stkq.nextToken());
            int b = Integer.parseInt(stkq.nextToken());
            int numH = H[b]-H[a-1];
            int numG = G[b]-G[a-1];
            int numJ = J[b]-J[a-1];
            ans+=numH + " ";
            ans+=numG + " ";
            ans+=numJ;
            pw.println(ans);
        }
        pw.close();
    }
}
