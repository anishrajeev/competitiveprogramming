import java.util.*;
import java.io.*;
public class moop {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("moop.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));
        int N = Integer.parseInt(bf.readLine());
        point[] particles = new point[N];
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            particles[i] = new point(x, y);
        }
        Arrays.sort(particles);
        int[] min = new int[N];
        int[] max = new int[N];
        min[0] = particles[0].y;
        max[N-1] = particles[N-1].y;
        for(int i = 1; i < N; i++)min[i] = Math.min(min[i-1], particles[i].y);
        for(int i = N-2; i >= 0; i--)max[i] = Math.max(max[i+1], particles[i].y);
        int ans = 1;
        for(int i=0;i<N-1;i++)
            if(min[i] > max[i+1])
                ans++;
        pw.println(ans);
        pw.close();
    }
    public static class point implements Comparable<point>{
        int x, y;
        public point(int a, int b){
            x = a;
            y = b;
        }

        @Override
        public int compareTo(point p) {
            if(x==p.x)return Integer.compare(y, p.y);
            return Integer.compare(x, p.x);
        }
    }
}