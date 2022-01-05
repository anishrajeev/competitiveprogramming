import java.util.*;
import java.io.*;
public class crossings {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("crossings.in"));
        PrintWriter pw = new PrintWriter("crossings.out");
        int N = Integer.parseInt(bf.readLine());
        int[][] cows = new int[N][2];
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            cows[i][0] = a;
            cows[i][1] = b;
        }
        Arrays.sort(cows, new Comparator<int[]>() {
            public int compare(int[] A, int[] B) {
                return A[0]-B[0];
            }
        });
        int[] max = new int[N];
        int[] min = new int[N];
        max[0] = cows[0][1];
        for(int i = 1; i < N; i++)max[i] = Math.max(max[i-1], cows[i][1]);
        min[N-1] = cows[N-1][1];
        for(int i = N-2; i >= 0; i--)
            min[i] = Math.min(min[i+1], cows[i][1]);
        int safe = 0;
        for(int i=0; i<N; i++) {
            boolean ok = true;
            if(i!=0 && max[i-1] > cows[i][1]) ok = false;
            if(i!=N-1 && min[i+1] < cows[i][1]) ok = false;
            if(ok) safe++;
        }
        pw.println(safe);
        pw.close();
    }
}
