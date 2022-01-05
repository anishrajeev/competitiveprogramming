import java.util.*;
import java.io.*;
public class shortcut {
    static long[] d;
    static int[] par;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("shortcut.in"));
        PrintWriter pw = new PrintWriter("shortcut.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int T = Integer.parseInt(stk.nextToken());
        int[][] graph = new int[N][N];
        d = new long[N];
        par = new int[N];
        Arrays.fill(d, 1000000);
        d[0] = 0;
        int[] amounts = new int[N];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < N; i++){
            amounts[i] = Integer.parseInt(stk.nextToken());
        }
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            int t = Integer.parseInt(stk.nextToken());
            graph[a][b] = t;
            graph[b][a] = t;
        }
        PriorityQueue<Integer> q = new PriorityQueue<>(pq);
        boolean[] visited = new boolean[N];
        q.add(0);
        while(!q.isEmpty()){
            int num = q.poll();
            if(visited[num])continue;
            visited[num]=true;
            for(int i = 0; i < N; i++){
                if(graph[num][i]==0)continue;
                if(d[num]+graph[num][i]<d[i]){
                    d[i] = d[num]+graph[num][i];
                    par[i] = num;
                }
                else if(d[num]+graph[num][i]==d[i]){
                    ArrayList<Integer> curr = backtrack(i);
                    ArrayList<Integer> newarr = backtrack(num);
                    newarr.add(0, i);
                    int lexo = lexographic(curr, newarr);
                    if(lexo==1){
                        par[i] = num;
                    }
                }
                if(!visited[i])q.add(i);
            }
        }
        //System.out.println(Arrays.toString(d));
        //System.out.println(Arrays.toString(par));
        //for(int i = 0; i < N; i++)System.out.println(backtrack(i));
        long[] amount = new long[N];
        for(int i = 0; i < N; i++){
            ArrayList<Integer> path = backtrack(i);
            for(int c:path){
                amount[c]+=amounts[i];
            }
        }
        long answer = 0;
        for(int i = 1; i < N; i++){
            answer = Math.max(answer, amount[i]*(d[i]-T));
        }
        pw.println(answer);
        pw.close();
    }
    static Comparator<Integer> pq = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return (int) (d[o1]-d[o2]);
        }
    };
    public static ArrayList<Integer> backtrack(int node){
        if(node==0){
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(0);
            return arr;
        }
        ArrayList<Integer> steps = new ArrayList<>();
        steps.add(node);
        steps.addAll(backtrack(par[node]));
        return steps;
    }
    public static int lexographic(ArrayList<Integer> arr1, ArrayList<Integer> arr2){
        for(int i = 0; i < Math.min(arr1.size(), arr2.size()); i++){
            if(arr1.get(i)<arr2.get(i))return 0;
        }
        return 1;
    }
}
