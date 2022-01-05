import java.io.*;
import java.util.*;
//thinking time:2:06 || 2:17 ||2:23
//coding time:2:09 || 2:20
public class lifeguards {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("lifeguards.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
        ArrayList<time> arr = new ArrayList<>();
        int N = Integer.parseInt(bf.readLine());
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            arr.add(new time(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken())));
        }
        Collections.sort(arr);

    }
    public static class time implements Comparable{
        private int start, end;
        public time(int x, int y){
            start = x;
            end = y;
        }
        public int getStart(){
            return start;
        }
        @Override
        public int compareTo(Object o) {
            int comparestart = ((time)o).getStart();
            return this.start-comparestart;
        }
    }
}
