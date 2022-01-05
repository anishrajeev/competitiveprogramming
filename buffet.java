import java.util.*;
import java.io.*;
public class buffet {
    static int N;
    static int E;
    static ArrayList<Integer>[] graph;
    static int[] qual;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("buffet.in"));
        PrintWriter pw = new PrintWriter("buffet.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(stk.nextToken());
        E = Integer.parseInt(stk.nextToken());
        graph = new ArrayList[N];
        qual = new int[N];
        ArrayList<TreeSet> components = new ArrayList<>();
        int[] componentindex = new int[N];
        for(int i = 0; i < N; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            qual[i] = Integer.parseInt(stk.nextToken());
            int D = Integer.parseInt(stk.nextToken());
            for(int c = 0; c < D; c++){
                int num = Integer.parseInt(stk.nextToken())-1;
                graph[i].add(num);
            }
        }
        ArrayList<Integer> nodes = new ArrayList<>();
        for(int i = 0; i < N; i++)nodes.add(i);
        Collections.sort(nodes, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(qual[o1], qual[o2]);
            }
        });
        int[] DP = new int[N];
        int[][] distance = new int[N][N];
        for(int i = 0; i < N; i++) {
            DP[i] = qual[i];
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        boolean[] visiteddfs = new boolean[N];
        for(int i = 0; i < N; i++){
            if(visiteddfs[i])continue;
            components.add(new TreeSet());
            int index = components.size()-1;
            componentindex[i] = index;
            Stack<Integer> stack = new Stack<>();
            stack.push(i);
            while(!stack.isEmpty()){
                int num = stack.pop();
                if(visiteddfs[num])continue;
                componentindex[num] = index;
                visiteddfs[num] = true;
                components.get(index).add(num);
                for(int c:graph[num]){
                    if(!visiteddfs[c])stack.push(c);
                }
            }
        }
        for(int i = 0; i < N; i++){
            LinkedList<Integer> q = new LinkedList<>();
            boolean[] visited = new boolean[N];
            int u = nodes.get(i);
            distance[u][u] = 0;
            q.add(u);
            while (!q.isEmpty()) {
                int n = q.pollFirst();
                visited[n] = true;
                for (int c = 0; c < graph[n].size(); c++) {
                    int v = graph[n].get(c);
                    distance[u][v] = Math.min(distance[u][v], distance[u][n] + 1);
                    if(!visited[v])q.add(v);
                }
            }
        }
        for(int i = 0; i < N; i++){
            int node = nodes.get(i);
            for(int c = 0; c < i; c++){
                int nn = nodes.get(c);
                if(qual[node]==qual[nn])break;
                if(componentindex[node]!=componentindex[nn])continue;
                DP[node] = Math.max(DP[node], DP[nn]+qual[node]-E*distance[node][nn]);
            }
        }
        Arrays.sort(DP);
        int answer = DP[N-1];
        pw.println(answer);
        pw.close();
    }
}
