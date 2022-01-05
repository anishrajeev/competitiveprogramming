import java.util.*;
import java.io.*;
public class shuffle {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter pw = new PrintWriter("shuffle.out");
        int N = Integer.parseInt(bf.readLine());
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int[] to = new int[N];
        int[] parent = new int[N];
        int answer = N;
        boolean[] visited = new boolean[N];
        Arrays.fill(visited, false);
        for(int i = 0; i < N; i++){
            to[i]=Integer.parseInt(stk.nextToken())-1;
            parent[to[i]]++;
        }
        LinkedList<Integer> q = new LinkedList<>();
        for(int i = 0; i < N; i++){
            if(parent[i]==0){
                q.add(i);
                answer--;
            }
        }
        while(!q.isEmpty()){
            int num = q.removeFirst();
            if(--parent[to[num]]==0){
                q.push(to[num]);
                answer--;
            }
        }
        pw.println(answer);
        pw.close();
    }
}
