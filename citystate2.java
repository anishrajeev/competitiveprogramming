import java.util.*;
import java.io.*;
public class citystate2{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("citystate.in"));
        PrintWriter pw = new PrintWriter("citystate.out");
        int N = Integer.parseInt(bf.readLine());
        TreeSet<String> set = new TreeSet<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            String city = stk.nextToken().toUpperCase();
            String state = stk.nextToken();
            String[] carr = city.split("");
            String cityinitial = carr[0]+carr[1];
            if(city.charAt(0)==state.charAt(0)&&city.charAt(1)==state.charAt(1))continue;
            StringBuilder sb = new StringBuilder();
            sb.append(cityinitial.charAt(0));
            sb.append(cityinitial.charAt(1));
            sb.append(state.charAt(0));
            sb.append(state.charAt(1));
            boolean b = set.add(sb.toString());
            if(!b){

            }
        }
    }
}
