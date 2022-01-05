import java.io.*;
import java.util.*;

public class cbarn {
    static ArrayList<Integer> cows;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
        cows = new ArrayList<>();
        N = Integer.parseInt(bf.readLine());
        int energy = 0;
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++){
            cows.add(Integer.parseInt(bf.readLine()));
        }
        for(int i = 0; i < N; i++){
            int nextpos = i;
            System.out.println("STARTING AT DOOR " + i);
            boolean[] visited = new boolean[N];
            Arrays.fill(visited, false);
            energy = 0;
            for(int c = 0; c < N; c++){
                int num = i+c;
                if(i+c>=N)num = Math.abs(N-(i+c));
                int amount = cows.get(num);
                boolean wentinside = false;
                System.out.println("Num: " + num + "  Amount: " + amount + "  NextPos: " + nextpos + "  Energy: " + energy);
                System.out.println();
                for(int d = 0; d < amount; d++){
                    energy+=Math.pow(nextpos-num, 2);
                    nextpos++;
                    wentinside = true;
                    if(nextpos == N-1){
                        for(int z = 0; z < N; z++){
                            if(!visited[z]){
                                nextpos = z;
                                break;
                            }
                        }
                    }
                }
                if(!wentinside && nextpos<num)nextpos=num+1;
            }
            ans = Math.min(energy, ans);
        }
        pw.println(ans);
        pw.close();
    }
}