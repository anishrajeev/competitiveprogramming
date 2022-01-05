import java.util.*;
import java.io.*;
public class atlarge {
    static int ans;
    static int N;
    static int K;
    static int[] distStart;
    static int[] distLeaf;
    static final int MAXN = 100000;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("atlarge.in"));
        PrintWriter pw = new PrintWriter("atlarge.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        ans = 0;
        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken())-1;
        distStart = new int[N];
        distLeaf = new int[N];
        graph = new ArrayList[N];
        for(int i = 0; i < N; i++)graph[i] = new ArrayList<>();
        for(int i = 1; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            graph[a].add(b);
            graph[b].add(a);
        }
        for(int i=K;i<K+1;i++)
        {
            if(graph[i].size()==1)
            {
                pw.println(1);
                continue;
            }
            ans = 0;
            dfsDistances(i,-1);
            dfsDistances2(i,-1);
            dfs(i,-1);
            pw.println(ans);
            pw.close();
        }
    }
    public static void dfsDistances(int i, int par){
        distLeaf[i] = MAXN + 1;
        if(par != -1)
            distStart[i] = 1 + distStart[par];
        else
            distStart[i] = 0;
        boolean isLeaf = true;
        for(int j=0;j<graph[i].size();j++) {
            if(par != graph[i].get(j))
            {
                dfsDistances(graph[i].get(j),i);
                distLeaf[i] = Math.min(distLeaf[i], 1 + distLeaf[graph[i].get(j)]);
                isLeaf = false;
            }
        }
        if(isLeaf)
            distLeaf[i] = 0;
    }
    public static void dfsDistances2(int i,int par){
        if(par!=-1)
            distLeaf[i] = Math.min(distLeaf[i],distLeaf[par]+1);
        for(int j=0;j<graph[i].size();j++)
            if(par!=graph[i].get(j))
                dfsDistances2(graph[i].get(j),i);
    }
    public static void dfs(int i,int par)
    {
        if((par!=-1)&&(distLeaf[i]<=distStart[i])&&(distLeaf[par]>distStart[par]))
            ans++;
        for(int j=0;j<graph[i].size();j++)
            if(graph[i].get(j)!=par)
                dfs(graph[i].get(j),i);
    }
}
