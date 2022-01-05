import java.util.*;
import java.io.*;
public class clocktree {
    static ArrayList<Integer>[] adj;
    static int[] dfst;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("clocktree.in"));
        PrintWriter pw = new PrintWriter("clocktree.out");
        int N = Integer.parseInt(bf.readLine());
        adj = new ArrayList[N];
        int[] times = new int[N];
        dfst = new int[N];
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < N; i++)times[i]=Integer.parseInt(stk.nextToken());
        for(int i = 0; i < N; i++)adj[i]=new ArrayList<>();
        for(int i = 0; i < N-1; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            adj[a].add(b);
            adj[b].add(a);
        }
        int total = 0;
        for(int i = 0; i < N; i++){
            dfst = times.clone();
            dfs(i, -1);
            if(dfst[i]==1||dfst[i]==12) total++;
        }
        pw.println(total);
        pw.close();
    }
    public static int dfs(int node, int parent){
        for(int i:adj[node]){
            if(i!=parent){
                dfst[node]+=12-dfs(i, node);
                dfst[node] = (dfst[node]-1)%12 + 1;
            }
        }
        if(parent!=-1)dfst[parent]++;
        return dfst[node];
    }
}
