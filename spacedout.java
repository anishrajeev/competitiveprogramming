import java.io.*;
import java.util.*;

public class spacedout {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());
        int[][] beauty = new int[N][N];
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            for(int c = 0; c < N; c++){
                beauty[i][c] = Integer.parseInt(stk.nextToken());
            }
        }
        Stack<state> stack = new Stack<>();
        while(!stack.isEmpty()){
            state s = stack.pop();

        }
    }
    public static class state{
        boolean[][] locked;
        char[][] choices;
        int ans;
        public state(int N){
            locked = new boolean[N][N];
            choices = new char[N][N];
            ans = 0;
        }
    }
}
