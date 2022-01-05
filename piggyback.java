import java.util.*;
import java.io.*;
public class piggyback {
    static int B;
    static int E;
    static int P;
    static int N;
    static ArrayList<Integer>[] farm;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("piggyback.in"));
        PrintWriter pw = new PrintWriter("piggyback.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        B = Integer.parseInt(stk.nextToken());
        E = Integer.parseInt(stk.nextToken());
        P = Integer.parseInt(stk.nextToken());
        N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        farm = new ArrayList[N];
        for(int i = 0; i < N; i++)farm[i] = new ArrayList<>();
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            farm[a].add(b);
            farm[b].add(a);
        }
        int[] D0 = Distance(0);
        int[] D1 = Distance(1);
        int[] DN = Distance(N-1);
        long ans = Long.MAX_VALUE;
        for(int meet = 0; meet < N; meet++) {
            ans = Math.min(ans, D0[meet]*B + D1[meet]*E + DN[meet]*P);
        }
        pw.println(ans);
        pw.close();
    }
    public static int[] Distance(int X){
        int[] D = new int[N];
        for(int i = 0; i < N; i++)D[i] = -1;
        D[X]= 0;
        LinkedList<Integer> q = new LinkedList<>();
        q.add(X);
        while(!q.isEmpty()){
            int x = q.poll();
            for(int y:farm[(int) x]){
                if(D[y]==-1){
                    D[y] = D[x]+1;
                    q.add(y);
                }
            }
        }
        return D;
    }
}
