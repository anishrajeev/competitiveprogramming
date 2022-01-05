import java.util.*;
import java.io.*;
public class cardgame {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("cardgame.in"));
        PrintWriter pw = new PrintWriter("cardgame.out");
        int N = Integer.parseInt(bf.readLine());
        boolean[] marked = new boolean[N*2+1];
        ArrayList<Integer> Elsie = new ArrayList<>();
        ArrayList<Integer> Bessie = new ArrayList<>();
        ArrayList<Integer> Bessiefirst = new ArrayList<>();
        ArrayList<Integer> Bessiesecond = new ArrayList<>();
        int answer = 0;
        for(int i = 0; i < N; i++){
            Elsie.add(Integer.parseInt(bf.readLine()));
            marked[Elsie.get(Elsie.size()-1)] = true;
        }
        for(int i = 1; i <= N*2; i++) if(!marked[i])Bessie.add(i);
        Collections.sort(Bessie);
        for(int i = 0; i < N/2; i++)Bessiesecond.add(Bessie.get(i));
        for(int i = N/2; i < N; i++)Bessiefirst.add(Bessie.get(i));
        for(int i = 0; i < N/2; i++){
            int num = binsearchgreater(Bessiefirst, Elsie.get(i));
            if(num==-1)continue;
            else{
                answer++;
                Bessiefirst.remove(num);
            }
        }
        for(int i = N/2; i < N; i++){
            int num = binsearchsmaller(Bessiesecond, Elsie.get(i));
            if(num==-1)continue;
            else{
                answer++;
                Bessiesecond.remove(num);
            }
        }
        pw.println(answer);
        pw.close();
    }
    public static int binsearchgreater(ArrayList<Integer> cards, int target){
        int start = 0, end = cards.size() - 1;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (cards.get(mid) <= target) {
                start = mid + 1;
            }
            else {
                ans = mid;
                end = mid - 1;
            }
        }
        return ans;
    }
    public static int binsearchsmaller(ArrayList<Integer> cards, int target){
        int start = 0, end = cards.size() - 1;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (cards.get(mid) >= target) {
                end = mid - 1;
            }
            else {
                ans = mid;
                start = mid + 1;
            }
        }
        return ans;
    }
}
