import java.util.*;
import java.io.*;
public class baseball{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("baseball.in"));
        PrintWriter pw = new PrintWriter("baseball.out");
        int N = Integer.parseInt(bf.readLine());
        int[] cows = new int[N];
        int ans = 0;
        for(int i = 0; i < N; i++)cows[i] = Integer.parseInt(bf.readLine());
        Arrays.sort(cows);
        for(int i = 0; i < N; i++){
            for(int c = i+1; c < N; c++){
                for(int d = c+1; d < N; d++){
                    int X = cows[i];
                    int Y = cows[c];
                    int Z = cows[d];
                    int dist1 = Y-X;
                    int dist2 = Z-Y;
                    if(dist1 <= dist2 && dist2 <= 2*dist1)ans++;
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
