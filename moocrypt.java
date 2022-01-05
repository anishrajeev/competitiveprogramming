import java.io.*;
import java.util.*;

public class moocrypt {
    public static void main(String[] args)throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("moocrypt.in"));
        PrintWriter pw = new PrintWriter("moocrypt.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        HashMap<String, Integer> poss = new HashMap<>();
        char[][] puzzle = new char[N][M];
        int[] dx = new int[]{1, 1, 1, -1, -1, -1, 0, 0, 0};
        int[] dy = new int[]{1, 0, -1, 1, 0, -1, 1, 0, -1};
        for(int i = 0; i < N; i++){
            String s = bf.readLine();
            for(int c = 0;c < M; c++){
                puzzle[i][c] = s.charAt(c);
            }
        }
        for(int i = 0; i < N; i++){
            for(int c = 0; c < M; c++){
                char ch = puzzle[i][c];
                if(puzzle[i][c]=='M')continue;
                for(int d = 0; d < dx.length; d++){
                    int tempi = i+dx[d];
                    int tempc = c+dy[d];
                    int temp2i = tempi+dx[d];
                    int temp2c = tempc+dy[d];
                    if(tempi<0||tempi>=N||temp2i<0||temp2i>=N||tempc<0||tempc>=M||temp2c<0||temp2c>=M)continue;
                    if(puzzle[tempi][tempc]=='O')continue;
                    if(puzzle[tempi][tempc]==puzzle[temp2i][temp2c] && puzzle[tempi][tempc]!=puzzle[i][c]){
                        ArrayList<Character> arr = new ArrayList<>();
                        String s = String.valueOf(puzzle[i][c]) + puzzle[tempi][tempc];
                        if(!poss.containsKey(s))poss.put(s, 1);
                        else poss.replace(s, poss.get(s)+1);
                    }
                }
            }
        }
        int max = 0;
        for(String s:poss.keySet()){
            max = Math.max(poss.get(s), max);
        }
        pw.println(max);
        pw.close();
    }
}
