import java.util.*;
import java.io.*;
public class leftout {
    static int[][] cows;
    static ArrayList<int[][]> visited;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("leftout.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("leftout.out" )));
        N = Integer.parseInt(bf.readLine());
        cows = new int[N][N];
        visited = new ArrayList<>();
        for(int i = 0; i < N; i++){
            String str = bf.readLine();
            String[] arr = str.split("");
            for(int c = 0; c < N; c++){
                String s = arr[c];
                if(s.equals("R")) cows[i][c] = 0;
                else cows[i][c] = 1;
            }
        }
        for(int i = 0; i < N; i++){
            if(cows[0][i] == 1)flip(i, "C");
            if(cows[i][0] == 1)flip(i, "R");
        }
        int x = -1, y = -1;
        boolean topleft = true;
        for(int i = 1; i < N; i++){
            boolean row = true;
            boolean col = true;
            for(int c = 1; c < N; c++){
                if(cows[i][c] == 0) row = false;
                if(cows[c][i] == 0) col = false;
            }
            if(row && !col){
                x = i;
                y = 0;
                topleft = false;
            }
            if(col && !row){
                x = 0;
                y = i;
                topleft = false;
            }
            if(!row && !col)topleft = false;
        }
        if(topleft)x = y = 0;
        if(x==y&&y==-1){
            int count = 0;
            int xcount = 0, ycount = 0;
            for(int i = 0; i < N; i++){
                for(int c = 0; c < N; c++){
                    if(cows[i][c] == 1){
                        count++;
                        xcount = i;
                        ycount = c;
                    }
                }
            }
            if(count == 1){
                x = xcount;
                y = ycount;
            }
        }
        if(x!=-1&y!=-1){
            x++;
            y++;
        }
        pw.println(x + " " + y);
        pw.close();
    }
    public static void flip(int n, String s){
        if(s.equals("R")){
            for(int i = 0; i < N; i++){
                int x = cows[n][i];
                if(x == 0) cows[n][i] = 1;
                else cows[n][i] = 0;
            }
        }
        else{
            for(int i = 0; i < N; i++){
                int x = cows[i][n];
                if(x == 0) cows[i][n] = 1;
                else cows[i][n] = 0;
            }
        }
    }
}
