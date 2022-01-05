import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[][] board = new int[N][N];
        int[][] psum = new int[N][N];
        boolean positive = false;
        int max = 0;
        for(int i = 0; i < N; i++){
            for(int c = 0; c < N; c++){
                board[i][c]=scanner.nextInt();
                if(board[i][c]>0)positive=true;
                max = Math.max(max, board[i][c]);
            }
        }
        if(!positive){
            System.out.println(max);
            return;
        }
        psum[0][0]=board[0][0];
        for (int i = 1; i < N; i++)
            psum[0][i] = psum[0][i - 1] + board[0][i];
        for (int i = 1; i < N; i++)
            psum[i][0] = psum[i - 1][0] + board[i][0];
        for (int i = 1; i < N; i++){
            for (int j = 1; j < N; j++){
                psum[i][j] = psum[i - 1][j] + psum[i][j - 1] - psum[i - 1][j - 1] + board[i][j];
            }
        }
        /*for(int i = 0; i < N; i++){
            for(int c = 0;c  < N; c++){
                System.out.print(psum[i][c]+" ");
            }
            System.out.println();
        }*/
        for(int x1 = 0; x1 < N; x1++){
            for(int y1 = 0; y1 < N; y1++){
                for(int x2 = x1+1; x2 < N; x2++){
                    for(int y2 = y1+1; y2 < N; y2++){
                        int sum = 0;
                        if(x1==0&&y1==0){
                            sum = psum[x2][y2];
                        }
                        else if(x1==0){
                            sum = psum[x2][y2]-psum[x2][y1-1];
                        }
                        else if(y1==0){
                            sum = psum[x2][y2]-psum[x1-1][y2];
                        }
                        else{
                            sum = psum[x2][y2] - psum[x1-1][y2] - psum[x2][y1-1] + psum[x1-1][y1-1];
                        }
                        max = Math.max(sum, max);
                    }
                }
            }
        }
        System.out.println(max);
    }
}
