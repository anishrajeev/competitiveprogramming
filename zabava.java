import java.io.*;
import java.util.*;

public class zabava {
    public static void main(String[] args) throws IOException{
        //BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader bf = new BufferedReader(new FileReader("tester.in"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int[] population = new int[M];
        for(int i = 0; i < N; i++){
            population[Integer.parseInt(bf.readLine())-1]++;
        }
        int start = 0, end = N;
        while(start!=end){
            int mid = (start+end)/2;
            boolean b = solve(population, mid, K);
            if(b)end = mid;
            else start = mid+1;
        }
        int bs = start;
        int[] splits = new int[M];
        int total = 0;
        for(int i = 0; i < M; i++){
            splits[i] = (int)Math.floor(population[i]/(1.0*bs));
            //if(population[i]%bs==0)splits[i]--;
            total += splits[i];
            if(splits[i]==-1)System.out.println(i);
        }
        int[] cost = new int[M];
        for(int i = 0; i < M; i++){
            cost[i] = cost(population[i], splits[i]);
        }
        while(total < K){
            int buildingmin = 0;
            int costreduce = 0;
            for(int i = 0; i < M; i++){
                int ncost = cost(population[i], splits[i]+1);
                ncost = cost[i]-ncost;
                if(ncost > costreduce){
                    costreduce = ncost;
                    buildingmin = i;
                }
            }
            splits[buildingmin]++;
            cost[buildingmin]-=costreduce;
            total++;
        }
        int answer = 0;
        for(int i = 0; i < M; i++)answer+=cost[i];
        pw.println(answer);
        pw.close();
    }
    public static boolean solve(int[] population, int bs, int K){
        int total = 0;
        for(int i:population){
            total+=((int)Math.ceil(i/(bs*1.0))-1);
        }
        return total <= K;
    }
    public static int cost(int population, int splits){
        int high = population%(splits+1);
        int low = splits+1-high;
        int bs = (int)Math.ceil(population*1.0/(splits+1));
        if(high == 0){
            high = low;
            low = 0;
        }
        return (high*(bs*(bs+1))/2 + low*(bs*(bs-1))/2);
    }
}
