import java.io.*;
import java.util.*;

public class kolone {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("tester.in"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int N2 = Integer.parseInt(stk.nextToken());
        String a1backwards = bf.readLine();
        String ant2 = bf.readLine();
        String[] a1arr = a1backwards.split("");
        StringBuilder a1sb = new StringBuilder();
        for(int i = N-1; i >= 0; i--){
            a1sb.append(a1arr[i]);
        }
        String ant1 = a1sb.toString();
        int T = Integer.parseInt(bf.readLine());
        if(T > (N+N2-1))T = N+N2-1;
        System.out.println(solve(ant1, ant2, T));
    }
    public static String solve(String ant1, String ant2, int T){
        String[] answerarr = new String[ant1.length()+ant2.length()];
        Arrays.fill(answerarr, "");
        for(int i = 0; i < ant1.length(); i++){
            int timeleft = Math.min(T-(ant1.length()-(i+1)), ant2.length());
            timeleft = Math.max(timeleft, 0);
            int index = i+timeleft;
            answerarr[index] = String.valueOf(ant1.charAt(i));
        }
        int counter = 0;
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < ant1.length()+ant2.length(); i++){
            if(answerarr[i].equals("")){
                answerarr[i] = String.valueOf(ant2.charAt(counter));
                counter++;
            }
            answer.append(answerarr[i]);
        }
        return answer.toString();
    }
}
