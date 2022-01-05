import java.io.*;
import java.util.StringTokenizer;

public class planting2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("planting.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
        int[] tree = new int[1000000];
        int N = Integer.parseInt(bf.readLine());
        for(int i = 0; i < N-1; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            tree[a-1]++;
            tree[b-1]++;
        }
        int ans = 0;
        for(int i = 0; i < N; i++){
            if(tree[i] > ans) ans = tree[i];
        }
        pw.println(ans+1);
        pw.close();
    }
}
