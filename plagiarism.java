import java.io.*;
import java.util.*;
public class plagiarism {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());
        int[] submissions = new int[N];
        double[] percents = new double[N];
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < N; i++){
            submissions[i] = Integer.parseInt(stk.nextToken());
            percents[i] = submissions[i]*0.9;
        }
        Arrays.sort(submissions);
        Arrays.sort(percents);
        String s = "";
        long answer = 0;
        for(int i = 0; i < N; i++){
            int large = binsearch(submissions[i], percents, i);
            if(large==-1)continue;
            answer+= large-i;
        }
        System.out.println(answer);
    }
    public static int binsearch(int small, double[] percents, int index){
        int start = 0, end = percents.length - 1;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (percents[mid] > small) {
                end = mid - 1;
            }
            else {
                ans = mid;
                start = mid + 1;
            }
        }
        return ans;
    }
}
