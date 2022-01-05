import java.util.*;
import java.io.*;
public class slowdown {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("slowdown.in"));
        PrintWriter pw = new PrintWriter("slowdown.out");
        PriorityQueue<Integer> T = new PriorityQueue<>();
        PriorityQueue<Integer> D = new PriorityQueue<>();
        int N = Integer.parseInt(bf.readLine());
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            String event = stk.nextToken();
            int amount = Integer.parseInt(stk.nextToken());
            if(event.equals("T")) T.add(amount);
            else D.add(amount);
        }
        double time = 0;
        double distance = 0;
        int speed = 1;
        //speed = seconds for one meter
        for(int i = 0; i < N; i++){
            int devent = Integer.MAX_VALUE;
            int tevent = Integer.MAX_VALUE;
            if(T.size()>0)tevent = T.peek();
            if(D.size()>0)devent = D.peek();
            double ttime = tevent-time;
            double dtime = (devent-distance)*speed;
            double tdistance = (tevent-time)/(speed*1.0);
            double ddistance = devent-distance;
            if(ttime < dtime){
                time = T.poll();
                distance+=tdistance;
                speed++;
            }
            else{
                time += dtime;
                distance = D.poll();
                speed++;
            }
        }
        time += (1000-distance)*speed;
        int answer = (int)Math.round(time);
        pw.println(answer);
        pw.close();
    }
}
