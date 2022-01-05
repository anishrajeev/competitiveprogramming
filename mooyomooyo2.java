import java.util.*;
import java.io.*;
public class mooyomooyo2{
    static int[][] board;
    static int[][] region;
    static int[] regsizes;
    static int K;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("mooyomooyo.in"));
        PrintWriter pw = new PrintWriter("mooyomooyo.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());
        int[] regsizes = new int[1001];
        region = new int[100][10];
        board = new int[100][10];
        for(int i = 0; i < N; i++){
            String s = bf.readLine();
            String[] strarray = s.split("");
            for(int c = 0; c < 10; c++){
                board[i][c]=Integer.parseInt(strarray[c]);
            }
        }
        while(iterate());
        for(int[] a: board){
            for(int i:a){
                pw.print(i);
            }
            pw.println();
        }
    }
    public static void visit(int i, int j, int r, int c){
        if (i<0 || i>=N || j<0 || j>9 || board[i][j]!=c || region[i][j]!=0) return;
        region[i][j] = r;
        regsizes[r]++;
        visit(i-1,j,r,c);
        visit(i+1,j,r,c);
        visit(i,j-1,r,c);
        visit(i,j+1,r,c);
    }
    public static void gravity()
    {
        for (int j=0; j<10; j++) {
            int top = N-1, bottom = N-1;
            while (top >= 0) {
                while (top >= 0 && board[top][j] == 0) top--;
                if (top >= 0)
                    board[bottom--][j] = board[top--][j];
            }
            while (bottom >= 0) board[bottom--][j] = 0;
        }
    }
    public static boolean iterate()
    {
        int r = 1;
        for (int i=0; i<N; i++)
            for (int j=0; j<10; j++)
                region[i][j] = 0;
        for (int i=0; i<N; i++)
            for (int j=0; j<10; j++)
                if (board[i][j]!=0 && region[i][j]==0) visit(i,j,r++,board[i][j]);
        boolean progress = false;
        for (int i=0; i<N; i++)
            for (int j=0; j<10; j++)
                if (board[i][j]!=0 && regsizes[region[i][j]]>=K) {
                    board[i][j] = 0;
                    progress = true;
                }
        gravity();
        while (r>=0) regsizes[r--] = 0;
        return progress;
    }
}