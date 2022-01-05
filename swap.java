import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class swap {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("swap.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int[] cows = new int[N+1];
        boolean[] visited = new boolean[N+1];
        for(int i = 1; i <= N; i++)cows[i]=i;
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int l = Integer.parseInt(stk.nextToken());
            int r = Integer.parseInt(stk.nextToken());
            for(int j = 0; j < (r-l+1)/2; j++){
                int temp = cows[l+j];
                cows[l+j]=cows[r-j];
                cows[r-j]=temp;
            }
        }
        for(int i = 1; i <= N; i++){
            if(visited[i])continue;
            visited[i]=true;
            int start = cows[i];
            ArrayList<Integer> cycles = new ArrayList<>();
            cycles.add(i);
            while(start != i){
                visited[start]=true;
                cycles.add(start);
                start = cows[start];
            }
            //System.out.println(cycles);
            int mod = K%cycles.size();
            for(int c = 0; c < cycles.size(); c++){
                cows[cycles.get(c)]=cycles.get((c+mod)%cycles.size());
            }
        }
        for(int i = 1; i <= N; i++)pw.println(cows[i]);
        pw.close();
    }
}
