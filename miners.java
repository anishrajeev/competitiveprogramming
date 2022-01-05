import java.util.*;
import java.io.*;
public class miners {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());
        String[] food = bf.readLine().split("");
        int[][][] dp = new int[N+2][34][34];
        int[] combos = new int[]{0, 1, 2, 3, 11, 12, 13, 21, 22, 23, 31, 32, 33};
        int[][] store = new int[100][5];

        for (int i=0; i<100; i++) for (int j=0; j<5; j++) store[i][j] = value(i, j);
        //1 = Meat, 2 = Fish, 3 = Bread
        for(int i = 0; i < 13; i++){
            for(int c = 0; c < 13; c++){
                dp[N+1][combos[i]][combos[c]] = 0;
            }
        }
        for(int i = N; i > 0; i--){
            int f = -1;
            if(food[i-1].equals("M"))f = 1;
            if(food[i-1].equals("F"))f = 2;
            if(food[i-1].equals("B"))f = 3;
            for(int s1 = 0; s1 < 13; s1++){
                int ns1 = (combos[s1]%10)*10 + f;
                int vs1 = store[combos[s1]][f];
                for(int s2 = 0; s2 < 13; s2++){


                    int ns2 = (combos[s2]%10)*10 + f;

                    dp[i][combos[s1]][combos[s2]] = Math.max(dp[i+1][ns1][combos[s2]] + vs1, dp[i+1][combos[s1]][ns2] + store[combos[s2]][f]);
                    //System.out.println("(" + i + ", " + combos[s1] + ", " + combos[s2] + ") = " + dp[i][combos[s1]][combos[s2]]);
                }
            }
        }
        pw.println(dp[1][0][0]);
        pw.close();
    }
    public static int value(int state, int f){
        int s = -1;
        s = state*10 + f;

        int[] v = new int[10];
        while(s != 0){
            int num = s%10;
            s /= 10;
            v[num]++;
        }
        int ret = 0;
        for (int i=0; i<10; i++) {
            if (v[i] > 0) ret++;
        }
        return ret;
    }
}