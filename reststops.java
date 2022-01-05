import java.io.*;
import java.util.*;
public class reststops {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("reststops.in"));
        PrintWriter pw = new PrintWriter("reststops.out");
        ArrayList<Long> location = new ArrayList<>();
        ArrayList<Long> tastiness = new ArrayList<>();
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        long L = Long.parseLong(stk.nextToken());
        long N = Long.parseLong(stk.nextToken());
        long fspeed = Long.parseLong(stk.nextToken());
        long bspeed = Long.parseLong(stk.nextToken());
        long answer = 0;
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            location.add(Long.parseLong(stk.nextToken()));
            tastiness.add(Long.parseLong(stk.nextToken()));
        }
        boolean[] max = new boolean[(int) N];
        long maxnum = 0;
        for(long i = N-1; i >= 0; i--){
            if(tastiness.get((int) i)>maxnum){
                max[(int) i]=true;
                maxnum = tastiness.get((int) i);
            }
        }
        long lastpos = 0;
        long john = 0;
        long bessie = 0;
        for(int i = 0; i < N; i++){
            if(max[i]){
                john += (location.get(i) - lastpos)*(fspeed);
                bessie += (location.get(i) - lastpos)*(bspeed);
                answer += (john - bessie)*(tastiness.get(i));
                bessie = john;
                lastpos = location.get(i);
            }
        }
        pw.println(answer);
        pw.close();
    }
}
