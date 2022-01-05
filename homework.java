import java.io.*;
import java.util.*;
public class homework {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("homework.in"));
        PrintWriter pw = new PrintWriter("homework.out");
        int N = Integer.parseInt(bf.readLine());
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        long[] grades = new long[N];
        long[] sums = new long[N];
        long[] min = new long[N];
        ArrayList<Long> ans = new ArrayList<>();
        double max = 0;
        for(int i = 0; i < N; i++){
            grades[i] = Long.parseLong(stk.nextToken());
        }
        sums[N-1] = grades[N-1];
        for(int i = N-2; i >= 0; i--){
            sums[i]=sums[i+1]+grades[i];
        }
        long mins = Integer.MAX_VALUE;
        for(int i = N-1; i >= 0; i--){
            if(mins > grades[i]){
                mins = grades[i];
            }
            min[i] = mins;
        }
        int count = 1;
        long tempSum = grades[N-1];
        for(int i = N-2; i > 0; i--){
            tempSum+=grades[i];
            double avg = (tempSum-min[i])/(count*1.0);
            if(avg > max){
                max = avg;
                ans.clear();
                ans.add((long)i);
            }
            else if(avg == max){
                ans.add((long) i);
            }
            count++;
        }
        Collections.sort(ans);
        for(long i:ans){
            pw.println(i);
        }
        System.out.println(max);
        pw.close();
    }
}
