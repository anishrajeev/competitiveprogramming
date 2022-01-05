import java.io.*;
import java.util.*;
public class mountains {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("mountains.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
        ArrayList<range> arr = new ArrayList<>();
        int N = Integer.parseInt(bf.readLine());
        int count = 0;
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            range r = new range((x-y), (x+y));
            arr.add(r);
        }
        Collections.sort(arr);
        ArrayList<Integer> starts = new ArrayList<>();
        ArrayList<Integer> ends = new ArrayList<>();
        for(int i = 0; i < N; i++){
            starts.add(arr.get(i).x);
            ends.add(arr.get(i).y);
        }
        int activeend = ends.get(0);
        count++;
        for(int i = 0; i < ends.size(); i++){
            if(ends.get(i)>activeend){
                count++;
                activeend = ends.get(i);
            }
        }
        pw.println(count);
        pw.close();
    }
    public static class range implements Comparable{
        int x, y;
        public range(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getX(){
            return x;
        }
        @Override
        public int compareTo(Object o) {
            int comparerange =((range)o).getX();
            return this.x-comparerange;
        }
        @Override
        public String toString(){
            return x + " to " + y;
        }
    }
}
