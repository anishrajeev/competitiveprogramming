import java.io.*;
import java.util.*;
public class spies {
    public static boolean[] spies;
    public static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());
        int[] edges = new int[N];
        spies = new boolean[N];
        dp = new int[N];
        Arrays.fill(dp, -1);
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < N; i++){
            edges[i] = Integer.parseInt(bf.readLine())-1;
            graph[edges[i]].add(i);
        }
        Stack<state> s = new Stack<>();
        s.add(new state(edges[0], new TreeSet<>()));
        s.get(0).add(0);
        ArrayList<TreeSet<Integer>> cycles = new ArrayList<>();
        TreeSet<Integer> visited = new TreeSet<>();
        TreeSet<Integer> notvisited = new TreeSet<>();
        for(int i = 0; i < N; i++)notvisited.add(i);
        while(visited.size()!=N){
            if(s.isEmpty()){
                s.add(new state(notvisited.pollFirst(), new TreeSet<>()));
                s.get(0).add(s.get(0).n);
            }
            state st = s.pop();
            if(visited.contains(st.n))continue;
            visited.add(st.n);
            notvisited.remove(st.n);
            st.add(st.n);
            int going = edges[st.n];
            if(st.par.contains(going)){
                cycles.add(st.par);
            }
            else{
                TreeSet<Integer> npar = new TreeSet<>(st.par);
                npar.add(going);
                s.push(new state(going, npar));
            }
        }
        int answer = 0;
        for(TreeSet<Integer> cycle:cycles){
            TreeSet<Integer> mainnodes = new TreeSet<>(cycle);
            if(mainnodes.size()==0)answer+=((int)Math.floor(N/2.0));
            else{
                for(int i:mainnodes){
                    answer+=solve(i, edges, graph, mainnodes);
                }
            }
        }
        System.out.println(answer);
    }
    public static int solve(int node, int[] edges, ArrayList<Integer>[] graph, TreeSet<Integer> mainnodes){
        int solution = 0;
        if(dp[node]!=-1)return dp[node];
        for(int i = 0; i < graph[node].size(); i++){
            int newnode = graph[node].get(i);
            if(!mainnodes.contains(newnode))solution += solve(newnode, edges, graph, mainnodes);
            if(!spies[graph[node].get(i)])spies[node] = true;
        }
        if(spies[node])solution++;
        dp[node] = solution;
        return solution;
    }
    public static class state{
        int n;
        TreeSet<Integer> par;
        public state(int node, TreeSet<Integer> parent){
            n = node;
            par = parent;
        }
        public void add(int node){
            par.add(node);
        }
    }
}
