import java.io.*;
import java.util.*;
public class stove {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int[] guests = new int[N];
        for(int i = 0; i < N; i++)guests[i] = Integer.parseInt(bf.readLine());
        Arrays.sort(guests);
        PriorityQueue<Integer> gaps = new PriorityQueue<>();
        for(int i = 0; i < N-1; i++)gaps.add(guests[i+1] - guests[i]);
        int matchesusing = N;
        long answer = N;
        while(matchesusing != K){
            answer += gaps.poll()-1;
            matchesusing--;
        }
        System.out.println(answer);
    }
}
