import java.io.*;
import java.util.*;

public class cipele {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("tester.in"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        ArrayList<Integer> less = new ArrayList<>();
        ArrayList<Integer> more = new ArrayList<>();
        if(N < M){
            stk = new StringTokenizer(bf.readLine());
            for(int i = 0; i < N; i++)less.add(Integer.parseInt(stk.nextToken()));
            stk = new StringTokenizer(bf.readLine());
            for(int i = 0; i < M; i++)more.add(Integer.parseInt(stk.nextToken()));
        }
        else{
            stk = new StringTokenizer(bf.readLine());
            for(int i = 0; i < N; i++)more.add(Integer.parseInt(stk.nextToken()));
            stk = new StringTokenizer(bf.readLine());
            for(int i = 0; i < M; i++)less.add(Integer.parseInt(stk.nextToken()));
        }
        Collections.sort(less);
        Collections.sort(more);
        int start = 0, end = Integer.MAX_VALUE;
        while(start!=end){
            int mid = (start+end)/2;
            if(verify(less,more, mid)) end = mid;
            else start = mid+1;
        }
        System.out.println(start);
    }
    public static boolean verify(ArrayList<Integer> less, ArrayList<Integer> more, int target){
        int l = 0;
        int m = 0;
        while(l < less.size()){
            boolean matched = false;
            int matchnum = 0;
            for(int i = m; i < more.size(); i++){
                if(Math.abs(more.get(i)-less.get(l)) <= target){
                    matched = true;
                    matchnum = i;
                    break;
                }
            }
            if(matched)m = matchnum+1;
            else return false;
            l++;
        }
        return true;
    }
}
