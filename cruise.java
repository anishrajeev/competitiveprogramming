import java.io.*;
import java.util.*;

public class cruise {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader bf = new BufferedReader(new FileReader(new File("cruise.in")));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cruise.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int[][] network = new int[N][2];
        int[] steps = new int[M];
        int[] graph = new int[N];
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            int L = Integer.parseInt(stk.nextToken())-1;
            int R = Integer.parseInt(stk.nextToken())-1;
            network[i][0] = L;
            network[i][1] = R;
        }
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < M; i++){
            char c = stk.nextToken().charAt(0);
            if(c=='L')steps[i] = 0;
            else steps[i] = 1;
        }
        for(int i = 0; i < N; i++){
            int curr = i;
            for(int c = 0; c < M; c++){
                curr = network[curr][steps[c]];
            }
            graph[i] = curr;
        }
        TreeSet<Integer> set = new TreeSet<>();
        ArrayList<Integer> order = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int curr = 0;
        boolean answered = false;
        while(true){
            if(set.contains(curr))break;
            if(order.size()-1>=K){
                pw.println(order.get(K)+1);
                pw.close();
                answered = true;
                break;
            }
            set.add(curr);
            order.add(curr);
            map.put(curr, order.size()-1);
            curr = graph[curr];
        }
        if(!answered){
            int lastindex = map.get(curr);
            K = K-lastindex;
            K = K%(order.size()-lastindex);
            K += lastindex;
            pw.println(order.get(K)+1);
            pw.close();
        }
    }
}
