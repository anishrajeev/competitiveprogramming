import java.io.*;
import java.util.*;
public class rectangularfence {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int N = Integer.parseInt(bf.readLine());
        pair[] points = new pair[N];
        int[] coords = new int[N*2];
        long answer = (long)Math.pow(2, N);
        HashMap<Integer, Integer> compress = new HashMap<>();
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            points[i] = new pair(x, y);
            coords[i+i] = x;
            coords[i+i+1] = y;
        }
        Arrays.sort(points);
        Arrays.sort(coords);
        for(int i = 0; i < 2*N; i++) if(!compress.containsKey(coords[i]))compress.put(coords[i], i);
        int[][] board = new int[2*N][2*N];
        for(int i = 0; i < N; i++){
            int x = compress.get(points[i].x);
            int y = compress.get(points[i].y);
            board[x][y]++;
        }
        int[][] prefix = new int[2*N][2*N];
        prefix[0][0] = board[0][0];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(i==0&&j==0)continue;
                if(i > 0 && j == 0)prefix[i][j] = prefix[i-1][j] + board[i][j];
                else if(i == 0 && j > 0)prefix[i][j] = prefix[i][j-1] + board[i][j];
                else prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + board[i][j];
            }
        }
        int ans = 1;
        for(int i = 0; i < N; i++){
            for(int j = i; j < N; j++){
                int minx = compress.get(Math.min(points[i].x, points[j].x));
                int maxx = compress.get(Math.max(points[i].x, points[j].x));
                int miny = compress.get(Math.min(points[i].y, points[j].y));
                int maxy = compress.get(Math.max(points[i].y, points[j].y));
                int above = prefix[maxx][maxy] - (minx!=0 ? prefix[minx-1][maxy]:0);
                int below = prefix[maxx][miny] - (minx!=0 ? prefix[minx-1][miny]:0);
                ans += above*below;
            }
        }
        System.out.println(ans);
    }
    public static class pair implements Comparable<pair>{
        int x, y;
        public pair(int a, int b){
            x = a;
            y = b;
        }
        @Override
        public int compareTo(pair o) {
            return Integer.compare(x, o.x);
        }
    }
}
