import java.util.*;
import java.io.*;
public class cereal {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("cereal.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int[] f = new int[N];
        int[] s = new int[N];
        int[] ans = new int[N];
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            f[i] = Integer.parseInt(stk.nextToken());
            s[i] = Integer.parseInt(stk.nextToken());
        }
        int[] occ = new int[100001];
        int cnt = 0;
        for(int i=N-1;i>=0;i--)
        {
            int j = i;
            int pos = f[i];
            while(true)
            {
                if(occ[pos] == 0)
                {
                    occ[pos] = j;
                    cnt++;
                    break;
                }
                else if(occ[pos] < j)
                    break;
                else
                {
                    int k = occ[pos];
                    occ[pos] = j;
                    if(pos == s[k])
                        break;
                    j = k;
                    pos = s[k];
                }
            }
            ans[i] = cnt;
        }
        for(int i=0;i<N;i++) pw.println(ans[i]);
        pw.close();
    }
}
