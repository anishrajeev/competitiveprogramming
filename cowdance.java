import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class cowdance {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("cowdance.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int T = Integer.parseInt(stk.nextToken());
        ArrayList<Integer> cows = new ArrayList<>();
        for(int i = 0; i < N; i++){
            cows.add(Integer.parseInt(bf.readLine()));
        }
        int max = N;
        int min = 1;
        System.out.println(calculate(1, cows));
        while(min != max){
            int mid = ((max + min)/2);
            if(calculate(mid, cows) > T)  min = mid + 1;
            else if(calculate(mid, cows) <= T) max = mid;
        }
        pw.println(min);
        pw.close();
    }
    public static int calculate(int K, ArrayList<Integer> cow){
        ArrayList<Integer> stage = new ArrayList<>();
        ArrayList<Integer> cows = new ArrayList<>();
        cows.addAll(cow);
        int counter = 0;
        for(int i = 0; i < K; i++){
            stage.add(cows.get(0));
            cows.remove(0);
        }
        while(cows.size() > 0 || stage.size() > 0){
            int min = Collections.min(stage);
            counter += min;
            for(int i = 0; i < stage.size(); i++){
                stage.set(i, stage.get(i) - min);
            }
            for(int i = 0; i < stage.size(); i++){
                if(stage.get(i) == 0){
                    stage.remove(i);
                    if(cows.size() != 0){
                        stage.add(cows.get(0));
                        cows.remove(0);
                    }
                }
            }
        }
        return counter;
    }
}
