import java.util.*;
import java.io.*;
public class herding {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("herding.in"));
        PrintWriter pw = new PrintWriter("herding.out");
        int N = Integer.parseInt(bf.readLine());
        int[] cows = new int[N];
        int min = 0;
        for(int i = 0; i < N; i++){
            cows[i] = Integer.parseInt(bf.readLine());
        }
        Arrays.sort(cows);
        int max = Math.max(cows[N-2]-cows[0], cows[N-1]-cows[1]) - (N-2);
        if (cows[N-2]-cows[0] == N-2 && cows[N-1]-cows[N-2]>2) min = 2;
        if (cows[N-1]-cows[1] == N-2 && cows[1]-cows[0]>2) min = 2;
        int i, j=0, best=0;
        for (i=0; i<N; i++) {
            while (j<N-1 && cows[j+1]-cows[i]<=N-1) j++;
            best = Math.max(best, j-i+1);
        }
        if(min!=2) min = N-best;
        pw.println(min);
        pw.println(max);
        pw.close();
    }
}
