import java.io.*;
import java.util.*;
public class angrygold {
    static int N;
    static int[] left;
    static int[] right;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("angry.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
        N = Integer.parseInt(bf.readLine());
        int[] bales = new int[N];
        for(int i = 0; i < N; i++)bales[i] = Integer.parseInt(bf.readLine());
        Arrays.sort(bales);
        left = new int[N];
        right = new int[N];
        left[0] = 0;
        int lag = 1;
        for(int i = 1; i < N; i++){
            while(lag < i && Math.max(left[lag] + 1, bales[i] - bales[lag]) < Math.max(left[lag-1] + 1, bales[i] - bales[lag-1]))
                lag++;
            left[i] = Math.max(bales[i]-bales[lag-1], left[lag-1] + 1);
        }
        right[N-1] = 0;
        lag = N-2;
        for(int i = N-2; i >= 0; i--) {
            while(lag > i && Math.max(right[lag] + 1, bales[lag] - bales[i]) < Math.max(right[lag+1] + 1, bales[lag+1] - bales[i]))
                lag--;
            right[i] = Math.max(bales[lag+1] - bales[i], right[lag+1] + 1);
        } //THIS IS THE END OF THE AREA CHANGED.
        double answer = 999999999;
        int l = 0, r = N-1;
        while(true){
            int loc1 = bales[l];
            int loc2 = bales[r];
            int power = Math.max(left[l], right[r])+1;
            double middle = (loc2+loc1)/2.0;
            if(middle+power>=loc2 && middle-power<=loc1)answer = Math.min(answer, power);
            else answer = Math.min(answer, Math.max(power, (loc2-loc1)/2.0));
            if(l+1 == r)break;
            if(l > r)break;
            if(left[l+1] < right[r-1]) l = l+1;
            else if(left[l+1] > right[r-1]) r = r-1;
            else if(left[l+1] == right[r-1]){
                l++;
                r--;
            }
            else break;
        }
        for(int i = 0; i < N; i++)answer = Math.min(answer, Math.max(left[i], right[i]));
        int answerint = (int)(answer*10);
        if(answerint%10==5){
            answerint/=10;
            pw.println(answerint + ".5");
        }
        else pw.println((answerint/10) + ".0");
        pw.close();
    }
}