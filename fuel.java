import java.util.*;
import java.io.*;
public class fuel {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("fuel.in"));
        PrintWriter pw = new PrintWriter("fuel.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int G = Integer.parseInt(stk.nextToken());
        int B = Integer.parseInt(stk.nextToken());
        int D = Integer.parseInt(stk.nextToken());
        station[] stations = new station[N];
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(stk.nextToken());
            long y = Long.parseLong(stk.nextToken());
            stations[i] = new station(x, y);
        }
        Arrays.sort(stations);
        int[] nextcheap = new int[N];
        int[] s = new int[N];
        int stacklen = 0;
        for (int i = N-1; i >= 0; i--) {
            while (stacklen > 0 && stations[s[stacklen-1]].cost >= stations[i].cost) {
                stacklen--;
            }
            nextcheap[i] = (stacklen == 0 ? -1 : s[stacklen-1]);
            s[stacklen] = i;
            stacklen++;
        }

        int tank = B;
        tank -= stations[0].x;
        long answer = 0;
        for(int i = 0; i < N; i++){
            if (tank < 0) {
                pw.println(-1);
                pw.close();
            }
            int gasNeeded = Math.min(G, (nextcheap[i] == -1 ? D : stations[nextcheap[i]].x) - stations[i].x);
            if (gasNeeded > tank) {
                answer += (long) (gasNeeded - tank) * (long) stations[i].cost;
                tank = gasNeeded;
            }
            tank -= (i==N-1 ? D:stations[i+1].x)-stations[i].x;
        }
        if(tank<0)answer = -1;
        pw.println(answer);
        pw.close();
    }
    public static int first(int target, station[] stations){
        int start = 0, end = stations.length - 1;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (stations[mid].x <= target) {
                start = mid + 1;
            }
            else {
                ans = mid;
                end = mid - 1;
            }
        }
        return ans;
    }
    public static int last(int target, station[] stations){
        int start = 0, end = stations.length-1;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (stations[mid].x >= target) {
                end = mid - 1;
            }
            else {
                ans = mid;
                start = mid + 1;
            }
        }
        return ans;
    }
    public static class station implements Comparable<station>{
        int x;
        long cost;
        public station(int pos, long y){
            x = pos;
            cost = y;
        }
        @Override
        public int compareTo(station o) {
            return Integer.compare(x, o.x);
        }
    }
}
