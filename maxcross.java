import java.io.*;
import java.util.*;
public class maxcross {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("maxcross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int B = Integer.parseInt(stk.nextToken());
        boolean[] arr = new boolean[N];
        int p1 = 1;
        int p2 = K;
        int amount = 0;
        int answer = Integer.MAX_VALUE;
        Arrays.fill(arr, true);
        for(int i = 0; i < B; i++){
            arr[Integer.parseInt(bf.readLine())-1] =  false;
        }
        for(int i = 0; i < K; i++){
            if(!arr[i])amount++;
        }
        while(p2 < N){
            if(!arr[p1-1])amount--;
            if(!arr[p2])amount++;
            answer = Math.min(amount, answer);
            p1++;
            p2++;
        }
        pw.println(answer);
        pw.close();
    }
}
