import java.io.*;
import java.util.*;

public class div72 {
    public static void main(String args[])throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("div7.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
        ArrayList<Integer> lineup = new ArrayList<>();
        int N = Integer.parseInt(bf.readLine());
        for(int i = 0; i < N; i++){
            lineup.add(Integer.parseInt(bf.readLine()));
        }
        int[] prefixsum = new int[N];
        prefixsum[0] = lineup.get(0)%7;
        for(int i = 1; i < N; i++){
            prefixsum[i] = (prefixsum[i-1]+lineup.get(i))%7;
        }
        int[] max = new int[N];
        int[] position = new int[N];
        Arrays.fill(position, -1);
        for(int i = 0; i < N; i++){
            int num = prefixsum[i];
            if(position[num] == -1) position[num] = i;
            max[num] = i;
        }
        long answer = 0;
        for(int i = 0; i < N; i++){
            long num = max[i]-position[i];
            answer = Math.max(answer, num);
        }
        pw.println(answer);
        pw.close();
    }
}
