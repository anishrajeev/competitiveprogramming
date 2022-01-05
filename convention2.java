import java.util.*;
import java.io.*;
public class convention2{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("convention2.in"));
        PrintWriter pw = new PrintWriter("convention2.out");
        int N = Integer.parseInt(bf.readLine());
        ArrayList<cow> cows = new ArrayList<>();
        PriorityQueue<cow> q = new PriorityQueue<>(cow.senioritysort);
        for(int i = 1; i <= N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            cows.add(new cow(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()), i));
        }
        Collections.sort(cows, cow.timesort);
        int currCow = 0, answer = 0, currT = 0;
        while(currCow<N||q.size()>0){
            if(currCow<N&&cows.get(currCow).arrive<=currT){
                q.offer(cows.get(currCow++));
            }
            else if(q.size()==0){
                currT=cows.get(currCow).time+cows.get(currCow).arrive;
                currCow++;
            }
            else{
                cow c = q.poll();
                answer = Math.max(answer, currT-c.arrive);
                currT+=c.time;
            }
        }
        pw.println(answer);
        pw.close();
    }
    public static class cow{
        int arrive, time, seniority;
        public cow(int a, int t, int s){
            arrive=a;
            time=t;
            seniority=s;
        }
        public static Comparator<cow> timesort = new Comparator<cow>() {
            public int compare(cow c1, cow c2) {
                int time1 = c1.arrive;
                int time2 = c2.arrive;
                return Integer.compare(time1, time2);
            }};
        public static Comparator<cow> senioritysort = new Comparator<cow>() {
            public int compare(cow c1, cow c2) {
                int age1 = c1.seniority;
                int age2 = c2.seniority;
                return Integer.compare(age1, age2);
            }};
    }
}
