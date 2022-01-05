import java.util.*;
import java.io.*;
public class citystate {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("citystate.in"));
        PrintWriter pw = new PrintWriter("citystate.out");
        int N = Integer.parseInt(bf.readLine());
        HashMap<String, Integer> map = new HashMap<>();
        int answer = 0;
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            String city = stk.nextToken();
            String State = stk.nextToken();
            String[] arr = city.split("");
            String cityinitials = arr[0]+arr[1];
            if(cityinitials.equals(State))continue;
            String citystate = cityinitials+State;
            if(map.containsKey(citystate)){
                map.replace(citystate, map.get(citystate)+1);
            }
            else{
                map.put(citystate, 1);
            }
        }
        for(String citystate:map.keySet()){
            String[] s = citystate.split("");
            String reversed = s[2]+s[3]+s[0]+s[1];
            if(!map.containsKey(reversed))continue;
            int amount = map.get(reversed)*map.get(citystate);
            answer+=amount;
            map.replace(citystate, 0);
            map.replace(citystate, 0);
        }
        pw.println(answer);
        pw.close();
    }
}