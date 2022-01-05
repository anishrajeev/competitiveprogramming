import java.util.*;
import java.io.*;
public class mate {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        String str = bf.readLine();
        int mod = 1000000007;
        char[] array = new char[str.length()];
        int N = array.length;
        for(int i = 0; i < str.length(); i++)array[i] = str.charAt(i);
        int[] s = new int[N];
        for(int i = 0; i < N; i++)s[i] = array[i]-96;
        int[][][][] dpwindow = new int[2][N+1][27][27];
        dpwindow[1][1][0][s[0]] = 1;
        for(int x = 1; x < N; x++){
            dpwindow[0][1][0][s[x]] = 1;
            for(int length = 1; length <= N; length++){
                for(int i = 0; i <= 26; i++){
                    for(int c = 0; c <= 26; c++){
                        //if(length!=1 || i != 0 || c != s[x])dpwindow[0][length][i][c] = 0;
                        if(c == s[x]){
                            for(int d = 0; d <= 26; d++){
                                /*dp[x][length][i][c] += dp[x-1][length-1][d][i];
                                dp[x][length][i][c] %= mod;*/
                                dpwindow[0][length][i][c] += dpwindow[1][length-1][d][i];
                                dpwindow[0][length][i][c] %= mod;
                            }
                        }
                        dpwindow[0][length][i][c] += dpwindow[1][length][i][c];
                        dpwindow[0][length][i][c] %= mod;
                    }
                }
            }
            dpwindow[1] = dpwindow[0].clone();
            for(int i = 0; i <= N; i++){
                for(int c = 0; c < 27; c++){
                    Arrays.fill(dpwindow[0][i][c], 0);
                }
            }
            System.out.println(Arrays.deepToString(dpwindow[0]));
            dpwindow[0] = new int[N+1][27][27];
            System.out.println();
            System.out.println(Arrays.deepToString(dpwindow[0]));
        }
        int Q = Integer.parseInt(bf.readLine());
        for(int i = 0; i < Q; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(stk.nextToken());
            String[] XY = stk.nextToken().split("");
            int letter1 = XY[0].charAt(0)-96;
            int letter2 = XY[1].charAt(0)-96;
            pw.println(dpwindow[1][num][letter1][letter2]);
        }
        pw.close();
    }
}