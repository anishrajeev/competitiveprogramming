import java.io.*;
import java.util.*;
public class angry {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("angry.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int min = 0, max = 500000;
        ArrayList<Integer> bales = new ArrayList<Integer>();
        for(int i = 0; i < N; i++){
            bales.add(Integer.parseInt(bf.readLine()));
        }
        Collections.sort(bales);
        while(min != max){
            int mid = ((max + min)/2);
            int used = 0;
            int number = 0;
            int index = 0;
            while(index < N && number < bales.get(bales.size()-1) ){
                used++;
                number = bales.get(index);
                number += mid*2;
                index = findnext(bales, number);
            }
            if(used > K)  min = mid + 1;
            if(used <= K) max = mid;
        }
        pw.println(min);
        pw.close();
    }
    public static int findnext(ArrayList<Integer> arr, int num){
        int max = arr.size()-1;
        int min = 0;
        while(min != max){
            int mid = (max + min)/2;
            if(arr.get(mid) <= num) min = mid + 1;
            else max = mid;
        }
        return min;
    }
}