import java.io.*;
import java.util.*;

public class angry2 {
    static ArrayList<Integer> bales;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("angry.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        bales = new ArrayList<>();
        for(int i = 0; i < N; i++){
            bales.add(Integer.parseInt(bf.readLine()));
        }
        Collections.sort(bales);
        int start = 0, end = 1000000000;
        while(start!=end){
            int mid = (start+end)/2;
            int used = 0;
            int index = 0;
            int num = 0;
            while(index <= bales.size()-1){
                num = bales.get(index);
                num+=mid*2;
                index = findNext(num);
                used++;
            }
            if(used>K) start = mid+1;
            else end = mid;
        }
        pw.println(end);
        pw.close();
    }
    public static int findNext(int num){
        int min = 0, max = bales.size();
        while(min != max){
            int mid = (max + min)/2;
            if(bales.get(mid) <= num) min = mid + 1;
            else max = mid;
        }
        return min;
    }
}
