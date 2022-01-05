import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class trapped {
    public static void main(String[] args)throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("trapped.in"));
        PrintWriter pw = new PrintWriter("trapped.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int B = Integer.parseInt(stk.nextToken());
        ArrayList<bale> bales = new ArrayList<>();
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            int size = Integer.parseInt(stk.nextToken());
            int pos = Integer.parseInt(stk.nextToken());
            bales.add(new bale(size, pos));
        }
        Collections.sort(bales);
        int K = 0;
        for(int i = 0; i < N; i++)if(bales.get(i).pos<B)K = i;
        int secondi = K;
        int j = K+1;
        for (int i = K; i >= 0; i--) {
            while (j < N && bales.get(j).size < bales.get(j).pos-bales.get(secondi).pos) {
                j++;
            }
            if(j==N) break;
            result = Math.min(result, bales.get(j).pos-bales.get(secondi).pos-bales.get(secondi).size);
            if(bales.get(j).pos-bales.get(secondi).pos>bales.get(secondi).size)secondi--;
        }
        K++;
        secondi = K;
        j = K-1;
        for(int i = K; i < N; i++){
            while(j >= 0 && bales.get(j).size < bales.get(secondi).pos - bales.get(j).pos){
                j--;
            }
            if(j==-1)break;
            result = Math.min(result, bales.get(secondi).pos-bales.get(j).pos-bales.get(secondi).size);
            if(bales.get(secondi).pos-bales.get(j).pos > bales.get(secondi).size)secondi++;
        }
        if (result == Integer.MAX_VALUE) {
            pw.println(-1);
        }
        else if(result < 0)pw.println(0);
        else pw.println(result);
        pw.close();
    }
    public static class bale implements Comparable{
        private int pos, size;
        public bale(int s, int p){
            size = s;
            pos = p;
        }
        public int getPos(){
            return pos;
        }
        @Override
        public int compareTo(Object o) {
            int comparepos = ((bale)o).getPos();
            return this.pos-comparepos;
        }
    }
    public static int low(int location, ArrayList<bale> bales){
        int start = 0, end = bales.size()-1;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (bales.get(mid).pos >= location) end = mid - 1;
            else {
                ans = mid;
                start = mid + 1;
            }
        }
        return ans;
    }
    public static int high(int location, ArrayList<bale> bales){
        int start = 0, end = bales.size() - 1;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (bales.get(mid).pos <= location) start = mid + 1;
            else {
                ans = mid;
                end = mid - 1;
            }
        }
        return ans;
    }
}
