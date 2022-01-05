import java.io.*;
import java.util.*;
public class lemonade {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("lemonade.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
        int N = Integer.parseInt(bf.readLine());
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < N; i++){
            arr.add(Integer.parseInt(stk.nextToken()));
        }
        Collections.sort(arr);
        ans.add(arr.get(arr.size()-1));
        for(int i = arr.size()-2; i>=0; i--){
            if(arr.get(i)>=ans.size()) ans.add(arr.get(i));
        }
        pw.println(ans.size());
        pw.close();
    }
}
