import java.io.*;
import java.util.*;
public class wormsort {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("wormsort.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormsort.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int[] order = new int[N];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < N; i++){
            order[i] = Integer.parseInt(stk.nextToken())-1;
        }
        ArrayList<TreeSet<Integer>> components = new ArrayList<>();
        TreeSet<Integer> visited = new TreeSet<>();

        for(int i = 0; visited.size()!=N && i < N; i++){
            if(visited.contains(order[i]))continue;
            components.add(new TreeSet<>());
            visited.add(order[i]);
            int index = order[i];
            components.get(components.size()-1).add(index);
            while(index != i){
                index = order[index];
                components.get(components.size()-1).add(index);
                visited.add(index);
            }
        }
        int start = 0, end = 1000000000;
        wormhole[] wormholes = new wormhole[M];
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            int w = Integer.parseInt(stk.nextToken());
            wormholes[i] = new wormhole(a, b, w);
        }
        while(start!=end){
            int mid = (start+end+1)/2;
            if(verify(N, M, wormholes, mid, components)) start = mid;
            else end = mid-1;
        }
        if(start==1000000000)pw.println(-1);
        else pw.println(start);
        pw.close();
    }
    public static boolean verify(int N, int M, wormhole[] wormholes, int X, ArrayList<TreeSet<Integer>> components){
        wormhole[] wormholesn = new wormhole[M];
        for(int i = 0; i < M; i++){
            if(wormholes[i].w<X)wormholesn[i] = new wormhole(-1, -1, -1);
            else wormholesn[i] = new wormhole(wormholes[i].a, wormholes[i].b, wormholes[i].w);
        }
        ArrayList<pair>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < M; i++){
            if(wormholesn[i].a==-1)continue;
            graph[wormholesn[i].a].add(new pair(wormholesn[i].b, wormholesn[i].w));
            graph[wormholesn[i].b].add(new pair(wormholesn[i].a, wormholesn[i].w));
        }
        ArrayList<TreeSet<Integer>> componentsn = new ArrayList<>();
        Stack<Integer> s = new Stack<>();
        TreeSet<Integer> visited = new TreeSet<>();
        for(int i = 0; i < N && visited.size()!=N; i++){
            if(visited.contains(i))continue;
            visited.add(i);
            componentsn.add(new TreeSet<>());
            s.push(i);
            boolean[] visitedsmall = new boolean[N];
            while(!s.isEmpty()){
                int n = s.pop();
                componentsn.get(componentsn.size()-1).add(n);
                for(int c = 0; c < graph[n].size(); c++){
                    if(visitedsmall[graph[n].get(c).x])continue;
                    visitedsmall[graph[n].get(c).x] = true;
                    visited.add(graph[n].get(c).x);
                    s.push(graph[n].get(c).x);
                }
            }
        }
        int[] componentnum = new int[N];
        for(int i = 0; i < componentsn.size(); i++){
            for(int c:componentsn.get(i)){
                componentnum[c] = i;
            }
        }
        for(int i = 0; i < components.size(); i++){
            if(components.get(i).size()==1)continue;
            TreeSet<Integer> verify = new TreeSet<>();
            for(int c:components.get(i)){
                verify.add(componentnum[c]);
            }
            if(verify.size()!=1)return false;
        }
        return true;
    }
    public static class wormhole{
        int a, b, w;
        public wormhole(int x, int y, int z){
            a = x;
            b = y;
            w = z;
        }
    }
    public static class pair{
        int x, y;
        public pair(int a, int b){
            x = a;
            y = b;
        }
    }
}
