import java.util.*;
import java.io.*;
public class countcross{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("countcross.in"));
        PrintWriter pw = new PrintWriter("countcross.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int R = Integer.parseInt(stk.nextToken());
        int[] cows = new int[N*N];
        ArrayList<Integer> componentcows = new ArrayList<>();
        ArrayList<Integer>[] graph = new ArrayList[N*N];
        boolean[] visited = new boolean[N*N];
        Arrays.fill(visited, false);
        for(int i = 0; i < N*N; i++){
            graph[i] = new ArrayList<>();
            if((i+1)%N!=0&&(i+1)<N*N)graph[i].add(i+1);
            if((i-1)%N!=N-1&&(i-1)>=0)graph[i].add(i-1);
            if((i+N)<N*N)graph[i].add(i+N);
            if((i-N)>=0)graph[i].add(i-N);
        }
        for(int i = 0; i < R; i++){
            stk = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(stk.nextToken())-1;
            int y1 = Integer.parseInt(stk.nextToken())-1;
            int x2 = Integer.parseInt(stk.nextToken())-1;
            int y2 = Integer.parseInt(stk.nextToken())-1;
            int node1 = y1*N+x1;
            int node2 = y2*N+x2;
            graph[node1].remove(graph[node1].indexOf(node2));
            graph[node2].remove(graph[node2].indexOf(node1));
        }
        for(int i = 0; i < K; i++){
            stk = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(stk.nextToken())-1;
            int y = Integer.parseInt(stk.nextToken())-1;
            int node = y*N+x;
            cows[node]++;
        }
        for(int i = 0; i < N*N; i++){
            if(visited[i])continue;
            visited[i]=true;
            Stack<Integer> s = new Stack<>();
            int componentcount = cows[i];
            for(int c = 0; c < graph[i].size(); c++){
                int num = graph[i].get(c);
                if(!visited[num]){
                    s.push(num);
                    visited[num]=true;
                }
            }
            while(!s.isEmpty()){
                int num = s.pop();
                componentcount+=cows[num];
                for(int c = 0; c < graph[num].size(); c++){
                    int d = graph[num].get(c);
                    if(!visited[d]){
                        s.push(d);
                        visited[d]=true;
                    }
                }
            }
            componentcows.add(componentcount);
        }
        int answer = 0;
        for(int i = 0; i < componentcows.size(); i++){
            for(int c = i+1; c < componentcows.size(); c++){
                answer+=componentcows.get(i)*componentcows.get(c);
            }
        }
        pw.println(answer);
        pw.close();
    }
}
