import java.io.*;
import java.util.*;

public class lunchbox {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("tester.in"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int[] k = new int[M];
        for(int i = 0; i < M; i++)k[i] = Integer.parseInt(bf.readLine());
        Arrays.sort(k);
        int counter = 0;
        int answer = 0;
        for(int i = 0; i < M; i++){
            if(counter + k[i] > N)break;
            else{
                answer++;
                counter+=k[i];
            }
        }
        pw.println(answer);
        pw.close();
    }
}
