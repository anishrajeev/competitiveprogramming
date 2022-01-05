import java.util.*;
import java.io.*;
public class snowboots {
    static int[] tiles;
    static int N;
    static int B;
    static ArrayList<boot> boots;
    static int answer = Integer.MAX_VALUE;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("snowboots.in"));
        PrintWriter pw = new PrintWriter("snowboots.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(stk.nextToken());
        B = Integer.parseInt(stk.nextToken());
        tiles = new int[N];
        boots = new ArrayList<>();
        visited = new boolean[N][B];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < N; i++){
            tiles[i] = Integer.parseInt(stk.nextToken());
        }
        for(int i = 0; i < B; i++){
            stk = new StringTokenizer(bf.readLine());
            boots.add(new boot(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())));
        }
        search(0, 0);
        pw.println(answer);
        pw.close();
    }
    public static void search(int t, int b){
        if(visited[t][b])return;
        visited[t][b]=true;
        if(t == N-1){
            answer = Math.min(answer, b);
            return;
        }
        for(int i = t; i < N && i <= t+boots.get(b).distance; i++){
            if(tiles[i]<=boots.get(b).depth)search(i, b);
        }
        for(int i = b+1; i < B; i++){
            if(tiles[t]<=boots.get(i).depth)search(t, i);
        }
    }
    public static class boot{
        int distance;
        int depth;
        public boot(int x, int y){
            depth = x;
            distance = y;
        }
    }
}
