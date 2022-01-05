import java.io.*;
import java.util.*;
public class cowjog {
    public static void main(String[] args)throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("cowjog.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));
        StringTokenizer s = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(s.nextToken());
        int T = Integer.parseInt(s.nextToken());
        int answer = 1;
        ArrayList<Long> arr = new ArrayList<>();
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            long a = Long.parseLong(stk.nextToken());
            long b = Long.parseLong(stk.nextToken());
            arr.add((long) (a+b*T));
        }
        long a = arr.get(arr.size()-1);
        for(int i = N-2; i >= 0; i--){
            if(arr.get(i) < a){
                answer++;
                a = arr.get(i);
            }
        }
        pw.println(answer);
        pw.close();
    }
}
