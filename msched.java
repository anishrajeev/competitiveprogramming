import java.util.*;
import java.io.*;
public class msched {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("msched.in"));
        PrintWriter pw = new PrintWriter("msched.out");
        int N = Integer.parseInt(bf.readLine());
        cow[] c = new cow[N];
        int maxd = 0;
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int g = Integer.parseInt(stk.nextToken());
            int d = Integer.parseInt(stk.nextToken());
            maxd = Math.max(maxd, d);
            c[i] = new cow(g, d);
        }
        Arrays.sort(c);
        long total = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < maxd; i++){
            arr.add(i);
        }
        for(int i = 0; i < N; i++){
            int d = c[i].d;
            int answer = binsearch(arr, d);
            if(answer == -1)continue;
            total+=c[i].g;
            arr.remove(answer);
        }
        pw.println(total);
        pw.close();
    }
    public static int binsearch(ArrayList<Integer> arr, int d){
        int ans = -1;
        int start = 0, end = arr.size()-1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr.get(mid) >= d) {
                end = mid - 1;
            }
            else {
                ans = mid;
                start = mid + 1;
            }
        }
        return ans;
    }
    public static class cow implements Comparable<cow>{
        int g, d;
        public cow(int gal, int dea){
            g = gal;
            d = dea;
        }
        @Override
        public int compareTo(cow o) {
            if(o.g!=g)return Integer.compare(o.g, g);
            return Integer.compare(d, o.d);
        }
    }
}
