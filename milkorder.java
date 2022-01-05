import java.util.*;
import java.io.*;
public class milkorder {
    static int N, M;
    static boolean[] visited;
    static boolean check;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("milkorder.in"));
        PrintWriter pw = new PrintWriter("milkorder.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        visited = new boolean[N];
        ArrayList<ArrayList<Integer>> reqs = new ArrayList<>();
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int amount = Integer.parseInt(stk.nextToken());
            ArrayList<Integer> arr = new ArrayList<>();
            for(int c = 0; c < amount; c++){
                int num = Integer.parseInt(stk.nextToken());
                arr.add(num);
            }
            reqs.add(arr);
        }
        int start = 0, end = M;
        while(start!=end){
            int mid = (start+end+1)/2;
            if(verify2(reqs, mid))start = mid;
            else end = mid-1;
        }
        int X = end;
        //System.out.println(X);
        ArrayList<Integer>[] graph = new ArrayList[N];
        ArrayList<Integer>[] graph2 = new ArrayList[N];
        boolean[] canbefirst = new boolean[N];
        ArrayList<Integer> cows = new ArrayList<>();
        Arrays.fill(canbefirst, true);
        for(int i = 0; i < N; i++){
            graph[i] = new ArrayList<>();
            graph2[i] = new ArrayList<>();
        }
        for(int i = 0; i < X; i++){
            for(int c = 0; c < reqs.get(i).size()-1; c++){
                int num1 = reqs.get(i).get(c)-1;
                int num2 = reqs.get(i).get(c+1)-1;
                graph[num2].add(num1);
                graph2[num1].add(num2);
                canbefirst[num2] = false;
            }
        }
        //System.out.println(X);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        boolean[] placed = new boolean[N];
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < N; i++) if(canbefirst[i])queue.add(i);
        while(!queue.isEmpty()){
            int num = queue.poll();
            placed[num] = true;
            cows.add(num);
            set.add(num);
            for(int i:graph2[num]){
                if(queue.contains(i)||placed[i])continue;
                if(set.containsAll(graph[i])) queue.add(i);
            }
        }
        for(int i = 0; i < N-1; i++){
            pw.print((cows.get(i)+1)+" ");
        }
        pw.println(cows.get(cows.size()-1)+1);
        pw.close();
    }
    public static boolean verify(ArrayList<ArrayList<Integer>> req, int X){
        ArrayList<Integer>[] graph = new ArrayList[N];
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < N; i++)graph[i] = new ArrayList<>();
        for(int i = 0; i < X; i++){
            for(int c = 0; c < req.get(i).size()-1; c++){
                int num1 = req.get(i).get(c)-1;
                int num2 = req.get(i).get(c+1)-1;
                set.add(num1);
                set.add(num2);
                graph[num1].add(num2);
            }
        }
        boolean[] visited2 = new boolean[N];
        for(int i = 0; i < N; i++){
            if(visited2[i])continue;
            TreeSet<Integer> visited = new TreeSet<>();
            Stack<Integer> stack = new Stack<>();
            stack.push(i);
            while(!stack.isEmpty()){
                int num = stack.pop();
                visited2[num] = true;
                visited.add(num);
                for(int c:graph[num]){
                    if(visited.contains(c))return false;
                    if(!visited2[c])stack.push(c);
                }
            }
        }
        return true;
    }
    public static boolean verify2(ArrayList<ArrayList<Integer>> req, int X){
        check = true;
        Arrays.fill(visited, false);
        ArrayList<Integer>[] graph = new ArrayList[N];
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < N; i++)graph[i] = new ArrayList<>();
        for(int i = 0; i < X; i++){
            for(int c = 0; c < req.get(i).size()-1; c++){
                int num1 = req.get(i).get(c)-1;
                int num2 = req.get(i).get(c+1)-1;
                set.add(num1);
                set.add(num2);
                graph[num1].add(num2);
            }
        }
        boolean[] open = new boolean[N];
        Arrays.fill(open, false);
        for(int i = 0; i < N; i++){
            if(!visited[i])dfs(i, open, graph);
        }
        return check;
    }
    public static void dfs(int node, boolean[] open, ArrayList<Integer>[] graph){
        if(open[node])check = false;
        open[node] = true;
        visited[node] = true;
        for(int i:graph[node]){
            if(open[i])check = false;
            if(visited[i])continue;
            dfs(i, open, graph);
        }
        open[node] = false;
    }
}
