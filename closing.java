import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class closing {
    public static void main(String[] args)throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("closing.in"));
        PrintWriter pw = new PrintWriter("closing.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++)graph[i]=new ArrayList<>();
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            graph[a].add(b);
            graph[b].add(a);
        }
        int num = componentcount(graph);
        if(num == 1)pw.println("YES");
        else pw.println("NO");
        for(int i = 0; i < N-1; i++){
            int a = Integer.parseInt(bf.readLine())-1;
            for(int c = 0; c < N; c++){
                int n = graph[c].indexOf(a);
                if(n==-1)continue;
                else graph[c].remove(n);
            }
            graph[a] = new ArrayList<>();
            graph[a].add(-1);
            int number = componentcount(graph);
            if(number > 1)pw.println("NO");
            else pw.println("YES");
        }
        pw.close();
    }
    public static int componentcount(ArrayList<Integer>[] graph){
        int N = graph.length;
        boolean[] visited = new boolean[N];
        int counter = 0;
        for(int i = 0; i < N; i++){
            if(visited[i] || (graph[i].size()!=0&&graph[i].get(0)==-1))continue;
            counter++;
            if(counter > 1)return 2;
            Stack<Integer> s = new Stack<>();
            for(int c = 0; c < graph[i].size(); c++){
                if(!visited[graph[i].get(c)])s.push(graph[i].get(c));
            }
            while(!s.isEmpty()){
                int num = s.pop();
                visited[num] = true;
                for(int c = 0; c < graph[num].size(); c++){
                    if(graph[num].get(c)!=-1 && !visited[graph[num].get(c)])s.push(graph[num].get(c));
                }
            }
        }
        return counter;
    }
}
