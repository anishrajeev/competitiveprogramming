import java.util.*;
import java.io.*;
public class helpcross {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("helpcross.in"));
        PrintWriter pw = new PrintWriter("helpcross.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int C = Integer.parseInt(stk.nextToken());
        int N = Integer.parseInt(stk.nextToken());
        animal[] animals = new animal[N+C];
        for(int i = 0; i < C; i++){
            int t = Integer.parseInt(bf.readLine());
            animals[i]=new animal(t, t, false);
        }
        for(int i = C; i < N+C; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            animals[i] = new animal(a, b, true);
        }
        Arrays.sort(animals);
        int counter = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < animals.length; i++){
            animal a = animals[i];
            if(a.isCow)pq.add(a.end);
            else{
                while(pq.size()>0&&pq.peek()<a.start)pq.poll();
                if(pq.size()>0){
                    counter++;
                    pq.poll();
                }
            }
        }
        pw.println(counter);
        pw.close();
    }
    public static class animal implements Comparable<animal> {
        public int start;
        public int end;
        public boolean isCow;

        public animal(int s, int e, boolean b) {
            start = s;
            end = e;
            isCow = b;
        }

        public int compareTo(animal other) {
            if (this.start != other.start)
                return this.start - other.start;
            if (this.isCow && !other.isCow) return -1;
            if (!this.isCow && other.isCow) return 1;
            return 0;
        }
    }
}
