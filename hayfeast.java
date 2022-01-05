import java.util.*;
import java.io.*;
public class hayfeast {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("hayfeast.in"));
        PrintWriter pw = new PrintWriter("hayfeast.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        long M = Long.parseLong(stk.nextToken());
        long[] flavor = new long[N];
        long[] spice = new long[N];
        TreeMap<Long, Integer> map = new TreeMap<>();
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            flavor[i] = Long.parseLong(stk.nextToken());
            spice[i] = Long.parseLong(stk.nextToken());
        }
        long flvr = 0;
        int left = 0;
        long answer = Long.MAX_VALUE;
        for(int i = 0; i < N; i++){
            flvr += flavor[i];
            update(map, spice[i], 1);
            while(flvr - flavor[left] >= M) {
                update(map, spice[left], -1);
                flvr -= flavor[left++];
            }
            if(flvr >= M) {
                answer = Math.min(answer, map.lastKey());
            }
        }
        pw.println(answer);
        pw.close();
    }
    private static void update(TreeMap<Long, Integer> m, long k, int v) {
        if(!m.containsKey(k)) {
            m.put(k, 0);
        }
        int nv = m.get(k) + v;
        if(nv == 0) {
            m.remove(k);
        }
        else {
            m.put(k, nv);
        }
    }
}
