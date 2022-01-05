import java.io.*;
import java.util.*;
public class pairup {
    public static void main(String[] args)throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("pairup.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
        int N = Integer.parseInt(bf.readLine());
        int max = 0;
        int maxcount = 0;
        int ans = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        ArrayList<Integer> cows = new ArrayList<>();
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int amount = Integer.parseInt(stk.nextToken());
            int time = Integer.parseInt(stk.nextToken());
            if(time > max){
                max = time;
                maxcount = amount;
            }
            map.put(time, amount);
            cows.add(time);
        }
        Collections.sort(cows);
        int counter = 0;
        for(int i = 0; i < N; i++){
            counter += map.get(cows.get(i));
            if(counter >= maxcount){
                ans = max+cows.get(i);
                break;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
