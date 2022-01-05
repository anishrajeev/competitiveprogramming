import java.util.*;
import java.io.*;
public class bphoto {
    static int N;
    static int[] ft;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("bphoto.in"));
        PrintWriter pw = new PrintWriter("bphoto.out");
        N = Integer.parseInt(bf.readLine());
        ft = new int[N+5];
        Cow[] cows = new Cow[N];
        for(int i = 0; i < N; i++)cows[i] = new Cow(i+1, Integer.parseInt(bf.readLine()));
        Arrays.sort(cows);
        int seen = 0;
        int answer = 0;
        for(Cow c: cows) {
            int lhs = query(c.index);
            int rhs = seen - lhs;
            if(Math.max(lhs, rhs) > 2 * Math.min(lhs, rhs)) {
                answer++;
            }
            update(c.index, 1);
            seen++;
        }
        pw.println(answer);
        pw.close();
    }
    public static class Cow implements Comparable<Cow>{
        int index, height;
        public Cow(int i, int h){
            index = i;
            height = h;
        }

        @Override
        public int compareTo(Cow c) {
            return Integer.compare(c.height, height);
        }
    }
    public static void update(int x, int v) {
        while(x<=N){
            ft[x]+=v;
            x+=(x&-x);
        }
    }
    public static int query (int x) {return x>0 ? ft[x] + query(x-(x&-x)):0;}
}
