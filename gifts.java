import java.util.*;
import java.io.*;
public class gifts {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("gifts.in"));
        PrintWriter pw = new PrintWriter("gifts.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int B = Integer.parseInt(stk.nextToken());
        gift[] gifts = new gift[N];
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            gifts[i] = new gift(a, b);
        }
        Arrays.sort(gifts);
        long[] psum = new long[N];
        psum[0] = gifts[0].P+gifts[0].S;
        int answer = 0;
        for(int i = 1; i < N; i++)psum[i] = psum[i-1]+gifts[i].P+gifts[i].S;
        for(int i = 0; i < N; i++){
            int newprice = B-(gifts[i].P/2)-gifts[i].S;
            if(newprice<0)continue;
            int num = binsearch(psum, newprice);
            if(num==-1)continue;
            answer = Math.max(answer, num+2);
        }
        pw.println(answer);
        pw.close();
    }
    public static class gift implements Comparable{
        int P, S;
        public gift(int pi, int si){
            P = pi;
            S = si;
        }
        @Override
        public int compareTo(Object o) {
            return Integer.compare(P+S, ((gift)o).P+((gift)o).S);
        }
    }
    public static int binsearch(long[] psum, int target){
        int start = 0, end = psum.length;
        int answer = -1;
        while(start!=end){
            int mid = (start+end+1)/2;
            long midval = psum[mid];
            if(midval>target)end = mid-1;
            else start = mid;
        }
        if(start == 0 && psum[start]>target)return answer;
        return start;
    }
}
