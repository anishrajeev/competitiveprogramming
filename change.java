import java.io.*;
import java.util.*;

public class change {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int five = Integer.parseInt(stk.nextToken());
        int ten = Integer.parseInt(stk.nextToken());
        int twenty = Integer.parseInt(stk.nextToken());
        int fifty = Integer.parseInt(stk.nextToken());
        int have = 5*five + 10*ten + twenty*20 + fifty*50;
        int spending = Integer.parseInt(stk.nextToken());
        if(have < spending)System.out.println(-1);
        else{
            ArrayList<Integer> even = solve(spending, five, ten, twenty, fifty, true);
            ArrayList<Integer> odd = solve(spending, five, ten, twenty, fifty, false);
            if(even.get(4)==Integer.MAX_VALUE&&odd.get(4)==Integer.MAX_VALUE)System.out.println(-1);
            else if(even.get(4) < odd.get(4)){
                System.out.println(even.get(0) + " " + even.get(1) + " " + even.get(2) + " " + even.get(3) + " " + even.get(4));
            }
            else System.out.println(odd.get(0) + " " + odd.get(1) + " " + odd.get(2) + " " + odd.get(3) + " " + odd.get(4));
        }
    }
    public static ArrayList<Integer> solve(int spending, int five, int ten, int twenty, int fifty, boolean even){
        if(!even){
            spending-=50;
            fifty--;
        }
        int hundreds = fifty/2;
        ArrayList<Integer> answer = new ArrayList<>();
        if(!even&&spending < 50){
            answer.add(Integer.MAX_VALUE);
            answer.add(Integer.MAX_VALUE);
            answer.add(Integer.MAX_VALUE);
            answer.add(Integer.MAX_VALUE);
            answer.add(Integer.MAX_VALUE);
        }
        int hundredspent = Math.min(spending/100, hundreds);
        spending = spending - 100*hundredspent;
        int twentyspent = Math.min(spending/20, twenty);
        spending = spending - 20*twentyspent;
        int tenspent = Math.min(spending/10, ten);
        spending = spending - 10*tenspent;
        int fivespent = Math.min(spending/5, five);
        spending = spending - 5*fivespent;
        if(spending!=0){
            answer.add(Integer.MAX_VALUE);
            answer.add(Integer.MAX_VALUE);
            answer.add(Integer.MAX_VALUE);
            answer.add(Integer.MAX_VALUE);
            answer.add(Integer.MAX_VALUE);
        }
        else{
            answer.add(fivespent);
            answer.add(tenspent);
            answer.add(twentyspent);
            int fiftyspent = hundredspent*2;
            if(!even)fiftyspent++;
            answer.add(fiftyspent);
            answer.add(fivespent+tenspent+twentyspent+fiftyspent);
        }
        return answer;
    }
}
