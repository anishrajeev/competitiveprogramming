import java.io.*;
import java.util.*;
public class cf{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(bf.readLine());
        for(int testcase = 0; testcase < T; testcase++){
            int N = Integer.parseInt(bf.readLine());
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int[] a = new int[N];
            for(int i = 0; i < N; i++){
                a[i] = Integer.parseInt(stk.nextToken());
            }
            int sum = 0;
            ArrayList<Integer> nums = new ArrayList<>();
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    nums.add((Math.abs(a[i]-a[j])));
                }
            }
            int ans = GCDArr(nums);
            if(ans==0)System.out.println(-1);
            else System.out.println(ans);
        }
    }
    public static int gcd(int a, int b)
    {
        if (a == 0) return b;
        return gcd(b % a, a);
    }
    public static int GCDArr(ArrayList<Integer> arr)
    {
        int result = 0;
        for (int element: arr){
            result = gcd(result, element);
            if(result == 1) return 1;
        }
        return result;
    }
}