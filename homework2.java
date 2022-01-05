import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class homework2 {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("homework.in"));
        int N = Integer.parseInt(bf.readLine());
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int[] scores = new int[N];
        for(int i = 0; i < N; i++){
            scores[i] = Integer.parseInt(stk.nextToken());
        }
        int[] lowscores = new int[N];
        int num = 999999999;
        for(int i = N-1; i>=0; i--){
            if(num>scores[i])num = scores[i];
            lowscores[i] = num;
        }
        ArrayList<Integer> results = new ArrayList<>();
        double tempAvg;
        int tempSum = scores[N-1];
        int count = 1;
        double maxAvg = 0.0;
        for(int i = N-2; i > 0; i--){
            tempSum+=scores[i];
            tempAvg = (tempSum-lowscores[i])/(count*1.0);
            if(tempAvg>maxAvg){
                maxAvg = tempAvg;
                results = new ArrayList<>();
                results.add(i);
            }
            else if(tempAvg == maxAvg){
                results.add(i);
            }
            count++;
        }
        System.out.println(maxAvg);
        Collections.sort(results);
        PrintWriter pw = new PrintWriter("homework.out");
        for(int i = 0; i < results.size(); i++){
            pw.println(results.get(i));
        }
        pw.close();
    }
}
