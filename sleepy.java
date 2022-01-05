import java.util.*;
import java.io.*;
public class sleepy {
    static int[] ft;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("sleepy.in"));
        PrintWriter pw = new PrintWriter("sleepy.out");
        N = Integer.parseInt(bf.readLine());
        ArrayList<Integer> cows = new ArrayList<>();
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < N; i++)cows.add(Integer.parseInt(stk.nextToken()));
        int K = 1;
        ft = new int[N+1];
        int numtest123123321321 = cows.get(N-1);
        for(int i = N-2; i >= 0; i--){
            if(cows.get(i)<numtest123123321321){
                K++;
                numtest123123321321 = cows.get(i);
            }
            else break;
        }
        pw.println(N-K);
        ArrayList<Integer> answers = new ArrayList<>();
        ArrayList<Integer> prefix = new ArrayList<>();
        for(int i = N-K; i < N; i++){
            update(cows.get(i), 1);
        }
        for(int i = 0; i < N-K; i++)prefix.add(cows.get(i));
        for(int i = 0; i < N-K; i++){
            int prefixsize = prefix.size()-1;
            //prefixsize-1 + index in suffix
            answers.add(query(prefix.get(0))+prefixsize);
            update(prefix.get(0), 1);
            prefix.remove(0);
        }
        for(int i = 0; i < answers.size()-1; i++){
            pw.print(answers.get(i) + " ");
        }
        pw.println(answers.get(answers.size()-1));
        pw.close();
    }
    public static void update(int x, int v) {
        while(x<=N){
            ft[x]+=v;
            x+=(x&-x);
        }
    }
    public static int query (int x){
        return x>0 ? ft[x]+query(x-(x&-x)):0;
    }
}
