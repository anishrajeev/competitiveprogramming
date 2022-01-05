import java.util.*;
import java.io.*;
public class hshoe {
    static char[][] graph;
    static int N;
    static int answer;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("hshoe.in"));
        PrintWriter pw = new PrintWriter("hshoe.out");
        N = Integer.parseInt(bf.readLine());
        graph = new char[N][N];
        boolean[][] visited = new boolean[N][N];
        answer = 0;
        for(int i = 0; i < N; i++){
            String str = bf.readLine();
            String[] strarrinput = str.split("");
            for(int c = 0; c < N; c++){
                graph[i][c] = strarrinput[c].charAt(0);
            }
        }
        if(graph[0][0]=='(')recurse(0, 0, 1, 0, visited, false);
        pw.println(answer);
        pw.close();
    }
    public static void recurse(int x, int y, int opening, int closing, boolean[][] visited, boolean second){
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        boolean[][] newvisited = new boolean[N][N];
        for(int i = 0; i < N; i++)newvisited[i] = Arrays.copyOf(visited[i], visited[i].length);
        newvisited[x][y] = true;
        for(int i = 0; i < 4; i++){
            int nx = dx[i]+x;
            int ny = dy[i]+y;
            if(nx<0||nx>=N||ny<0||ny>=N||visited[nx][ny])continue;
            if(!second && graph[nx][ny] == '(')recurse(nx, ny, opening+1, closing, newvisited, false);
            if(!second && graph[nx][ny] == ')')recurse(nx, ny, opening, closing+1, newvisited, true);
            if(second && graph[nx][ny] == '(')continue;
            if(second && graph[nx][ny] == ')')recurse(nx, ny, opening, closing+1, newvisited, true);
        }
        if(opening == closing)answer = Math.max(answer, opening*2);
    }
}
