import java.util.*;
import java.io.*;
public class decorate {
    public static TreeSet<Integer>[] graph;
    public static boolean[] visited;
    static int color[];
    static int colorcount[];
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("decorate.in"));
        PrintWriter pw = new PrintWriter("decorate.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        long answer = 0;
        colorcount = new int[2];
        color = new int[N];
        graph = new TreeSet[N];
        visited = new boolean[N];
        for(int i = 0; i < N; i++){
            graph[i] = new TreeSet<>();
        }
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            graph[a].add(b);
            graph[b].add(a);
        }
        ArrayList<Integer> connectedcomponents = new ArrayList<>();
        boolean[] visited2 = new boolean[N];
        for(int i = 0; i < N; i++){
            if(visited2[i])continue;
            connectedcomponents.add(i);
            Stack<Integer> s = new Stack<>();
            s.push(i);
            while(!s.isEmpty()){
                int node = s.pop();
                visited2[node] = true;
                for(int c:graph[node]){
                    if(!visited2[c])s.push(c);
                }
            }
        }
        Arrays.fill(color, -1);
        int ccnt = 0;
        for(int i = 0; i < N; i++) {
            if(color[i] != -1) continue;
            ccnt++;
            colorcount[0] = colorcount[1] = 0;
            if (!dfs(i, 0)) {
                answer = -1;
                break;
            }
            answer += Math.max(colorcount[0], colorcount[1]);
        }
        pw.println(answer);
        pw.close();
    }
    public static boolean dfs(int u, int c){
        if(color[u] != -1) return color[u] == c;
        color[u] = c;
        colorcount[c]++;
        for(int i:graph[u]) {
            if(!dfs(i, 1 - c)) {
                return false;
            }
        }
        return true;
    }
}
