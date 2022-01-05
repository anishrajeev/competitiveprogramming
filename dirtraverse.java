import java.util.*;
import java.io.*;
public class dirtraverse {
    static String[] names;
    static ArrayList<Integer>[] graph;
    static boolean[] isFile;
    static long[] downsums;
    static long[] upsums;
    static long[] leaves;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("dirtraverse.in"));
        PrintWriter pw = new PrintWriter("dirtraverse.out");
        int N = Integer.parseInt(bf.readLine());
        names = new String[N];
        graph = new ArrayList[N];
        isFile = new boolean[N];
        downsums = new long[N];
        upsums = new long[N];
        leaves = new long[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            names[i] = stk.nextToken();
            int num = Integer.parseInt(stk.nextToken());
            if (num == 0) isFile[i] = true;
            for (int c = 0; c < num; c++) {
                int n = Integer.parseInt(stk.nextToken()) - 1;
                graph[i].add(n);
            }
        }
        leaves(0);
        downsum(0);
        upsum(-1, 0);
        long answer = Long.MAX_VALUE;
        for(int i = 0; i < N; i++){
            if(isFile[i])continue;
            answer = Math.min(answer, (upsums[i]+downsums[i]));
        }
        pw.println(answer);
        pw.close();
    }
    public static long downsum(int node){
        downsums[node] = 0;
        if(isFile[node])return names[node].length();
        for(int i:graph[node]){
            if(isFile[i])downsums[node]+=names[i].length();
            else downsums[node]+=(names[i].length()+1)*leaves[i]+downsum(i);
        }
        return downsums[node];
    }
    public static long leaves(int node){
        leaves[node] = 0;
        if(isFile[node])return 1;
        for(int i:graph[node]){
            leaves[node]+=leaves(i);
        }
        return leaves[node];
    }
    public static long upsum(int par, int node){
        if(par==-1)upsums[node]=0;
        else{
            upsums[node] = 0;
            upsums[node]+=upsums[par];
            upsums[node]+=3*(leaves[0]-leaves[node]);
            upsums[node]+=downsums[par]-downsums[node]-leaves[node]*(names[node].length()+1);
        }
        for(int i:graph[node]){
            upsum(node, i);
        }
        return upsums[node];
    }
}
