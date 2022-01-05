import java.io.*;
import java.util.*;
public class div7 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("div7.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
        ArrayList<Long> lineup = new ArrayList<>();
        ArrayList<Integer> prefixsum = new ArrayList<>();
        long total = 0;
        long N = Integer.parseInt(bf.readLine());
        for(int i = 0; i < N; i++){
            long num = Long.parseLong(bf.readLine());
            lineup.add(num);
            total += num;
            prefixsum.add((int)(total%7));
            System.out.print(num + " " + total);
            System.out.print("   " + (int)total%7);
            System.out.println();
        }
        long[] first = new long[7];
        long[] last = new long[7];
        long[] length = new long[7];
        Arrays.fill(first, Long.MAX_VALUE);
        Arrays.fill(last, Long.MAX_VALUE);
        Arrays.fill(length, Long.MAX_VALUE);
        for(int i = 0; i < prefixsum.size(); i++){
            int x = prefixsum.get(i);
            if(first[x] == Long.MAX_VALUE) first[x] = i;
        }
        for(int i = prefixsum.size()-1; i >= 0; i--){
            int x = prefixsum.get(i);
            if(last[x] == Long.MAX_VALUE) last[x] = i;
        }
        for(int i = 0; i < 7; i++){
            length[i] = last[i]-first[i];
        }
        Arrays.sort(length);
        pw.println(length[length.length-1]);
        pw.close();
    }
}
