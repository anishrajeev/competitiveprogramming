import java.io.*;
import java.util.*;
public class convention {
    static int N;
    static int M;
    static int C;
    static ArrayList<Integer> arr;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("convention.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());
        arr = new ArrayList<>();
        StringTokenizer stk2 = new StringTokenizer(bf.readLine());
        for(int i = 0; i < N; i++){
            arr.add(Integer.parseInt(stk2.nextToken()));
        }
        Collections.sort(arr);
        int start = 0, end = 1000000000;
        while(start != end){
            int mid = (start+end)/2;
            boolean b = calc(mid);
            if(b) end = mid;
            else start = mid+1;
        }
        pw.println(start);
        pw.close();
    }
    public static boolean calc(int T){
        int buses = 1;
        int index = 0;
        int carrying = 0;
        for(int i = 0; i < N; i++){
            if(carrying+1 > C || T < arr.get(i)-arr.get(index)){
                index = i;
                buses++;
                carrying=1;
            }
            else carrying++;
        }
        return buses<=M;
    }
}
