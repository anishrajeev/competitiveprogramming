import java.io.*;
import java.util.*;
public class meetings {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("meetings.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int L = Integer.parseInt(stk.nextToken());
        ArrayList<Cow> cows = new ArrayList<>();
        ArrayList<Pair> tw = new ArrayList<>();
        ArrayList<Integer> ltimes = new ArrayList<>();
        ArrayList<Integer> rtimes = new ArrayList<>();
        ArrayList<Integer> lx = new ArrayList<>();
        int totalweight = 0;
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            int w, x, d;
            w = Integer.parseInt(stk.nextToken());
            x = Integer.parseInt(stk.nextToken());
            d = Integer.parseInt(stk.nextToken());
            cows.add(new Cow(w, x, d));
            totalweight+=w;
            if(d==-1)lx.add(x);
        }
        Collections.sort(cows);
        for(int i = 0; i < N; i++){
            if(cows.get(i).d==-1)ltimes.add(cows.get(i).x);
            else rtimes.add(L-cows.get(i).x);
        }
        for(int i = 0; i < ltimes.size(); i++){
            tw.add(new Pair(ltimes.get(i), cows.get(i).w));
        }
        Collections.reverse(rtimes);
        for(int i = 0; i < rtimes.size(); i++){
            tw.add(new Pair(rtimes.get(i), cows.get(N-i-1).w));
        }
        Collections.sort(tw);
        int tot = 0;
        int T = 0;
        while(2*tot<totalweight){
            tot += tw.get(T).y;
            T++;
        }
        T = tw.get(T-1).x;
        Collections.sort(lx);
        int answer = 0;
        for(int i = 0; i < N; i++){
            if(cows.get(i).d==1){
                answer+=upperBound(lx, cows.get(i).x+2*T)-lowerBound(lx, cows.get(i).x);
            }
        }
        pw.println(answer);
        pw.close();
    }
    public static class Pair implements Comparable{
        int x, y;
        public Pair(int a, int b){
            x = a;
            y = b;
        }
        @Override
        public int compareTo(Object o) {
            return this.x-((Pair)o).x;
        }
        @Override
        public String toString(){
            return (x+":"+ y);
        }
    }
    public static class Cow implements Comparable{
        int w, x, d;
        public Cow(int a, int b, int c){
            w = a;
            x = b;
            d = c;
        }
        @Override
        public int compareTo(Object o){
            return this.x-((Cow)o).x;
        }
    }
    public static int upperBound(ArrayList<Integer> array, int value) {
        int low = 0;
        int high = array.size();
        while (low < high) {
            final int mid = (low + high) / 2;
            if (value >= array.get(mid)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    public static int lowerBound(ArrayList<Integer> array, int value) {
        int low = 0;
        int high = array.size();
        while (low < high) {
            final int mid = (low + high) / 2;
            //checks if the value is less than middle element of the array
            if (value <= array.get(mid)){
                high = mid;
            }
            else {
                low = mid + 1;
            }
        }
        return low;
    }
}
