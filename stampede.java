import java.io.*;
import java.util.*;
public class stampede {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("stampede.in"));
        PrintWriter pw = new PrintWriter("stampede.out");
        int N = Integer.parseInt(bf.readLine());
        int ans = 1;
        ArrayList<Pair> times = new ArrayList<>();
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int r = Integer.parseInt(stk.nextToken());
            x *= -r;
            times.add(new Pair(x-r, y));
            times.add(new Pair(x, -y));
        }
        Collections.sort(times);
        TreeSet<Integer> active = new TreeSet<>();
        TreeSet<Integer> answer = new TreeSet<>();
        for(int i = 0; i < times.size(); i++){
            int num = times.get(i).x;
            for(int j = i; j < times.size() && times.get(j).x==num; j++){
                if(times.get(j).y > 0)
                    active.add(times.get(j).y);
                else
                    active.remove(times.get(j).y*-1);
                i = j;
            }
            if(!active.isEmpty())answer.add(active.first());
        }
        pw.println(answer.size());
        pw.close();
    }
    public static class Pair implements  Comparable{
        private int x;
        private int y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getX(){
            return x;
        }
        @Override
        public int compareTo(Object o) {
            return x-((Pair)o).getX();
        }
    }
}
