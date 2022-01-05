import java.io.*;
import java.util.*;
public class prosjek {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("tester.in"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(bf.readLine());
        ArrayList<Double> arr = new ArrayList<>();
        for(int i = 0; i < N; i++)arr.add(Double.parseDouble(bf.readLine()));
        ArrayList<Double>[] dp = new ArrayList[N+1];
        for(int i = 0; i <= N; i++)dp[i] = new ArrayList<>();
        dp[N] = new ArrayList<>(arr);
        for(int i = N-1; i >= 1; i--){
            ArrayList<Double> arrList = new ArrayList<>(dp[i + 1]);
            ArrayList<Double> answer = new ArrayList<>();
            double minaverage = Double.MAX_VALUE;
            for(int p1 = 0; p1 <= i; p1++){
                for(int p2 = 0; p2 < p1; p2++){
                    ArrayList<Double> temp = new ArrayList<>(dp[i + 1]);
                    double avrge = (arrList.get(p1)+arrList.get(p2))/2.0;
                    if(avrge < minaverage){
                        temp.remove(p1);
                        temp.remove(p2);
                        temp.add(avrge);
                        answer = new ArrayList<>(temp);
                        minaverage = avrge;
                    }
                }
            }
            dp[i] = answer;
        }
        pw.println(dp[1].get(0));
        pw.close();
    }
}
