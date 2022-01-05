import java.util.*;
import java.io.*;
public class skidesign {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("skidesign.in"));
        PrintWriter pw = new PrintWriter("skidesign.out");
        int N = Integer.parseInt(bf.readLine());
        int[] hill = new int[N];
        for(int i = 0; i < N; i++){
            hill[i] = Integer.parseInt(bf.readLine());
        }
        int min = Integer.MAX_VALUE;
        Arrays.sort(hill);
        int maxrange = hill[N-1]-17;
        for(int i = 0; i <= maxrange; i++){
            int cost = 0;
            for(int c = 0; c < N; c++){
                int hillsize = hill[c];
                if(hillsize > i + 17)cost += (hillsize-(i+17))*(hillsize-(i+17));
                else if(hillsize < i)cost += (i-hillsize)*(i-hillsize);
            }
            min = Math.min(min, cost);
        }
        pw.println(min);
        pw.close();
    }
}
