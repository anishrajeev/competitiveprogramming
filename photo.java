import java.util.*;
import java.io.*;
public class photo {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("photo.in"));
        PrintWriter pw = new PrintWriter("photo.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        ArrayList<relationship> hate = new ArrayList<>();
        for(int i = 0; i < K; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int newa = Math.min(a, b);
            int newb = Math.max(a, b);
            hate.add(new relationship(newa, newb));
        }
        Collections.sort(hate);
        int lastx = 0;
        int count = 1;
        for(int i = 0; i < K; i++){
            if(hate.get(i).a>=lastx){
                count++;
                lastx = hate.get(i).b;
            }
        }
        pw.println(count);
        pw.close();
    }
    public static class relationship implements Comparable{
        int a, b;
        public relationship(int x, int y){
            a = x;
            b = y;
        }
        @Override
        public int compareTo(Object o) {
            return Integer.compare(b, ((relationship)o).b);
        }
    }
}
