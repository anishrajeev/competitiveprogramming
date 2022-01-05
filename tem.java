import java.io.*;
import java.util.*;

public class tem {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("tester.in"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int[] min = new int[1000000];
        int[] max = new int[1000000];
        int N = Integer.parseInt(bf.readLine());
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            min[i] = Integer.parseInt(stk.nextToken());
            max[i] = Integer.parseInt(stk.nextToken());
        }
        TreeSet<Integer> set = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        set.add(min[0]);
        map.put(min[0], 1);
        int left = 0, right = 0;
        int answer = 0;
        while(right < N){
            int highestmin = set.last();
            if(min[right+1]>=highestmin||highestmin<=max[right+1]){
                right++;
                if(set.contains(min[right]))map.replace(min[right], map.get(min[right])+1);
                else{
                    set.add(min[right]);
                    map.put(min[right], 1);
                }
            }
            else{
                if(right==left){
                    right++;
                    left++;
                    set.remove(min[left-1]);
                    map.remove(min[left-1]);
                    set.add(min[left]);
                    map.put(min[left], 1);
                }
                else{
                    left++;
                    map.replace(min[left-1], map.get(min[left-1])-1);
                    if(map.get(min[left-1])==0){
                        set.remove(min[left-1]);
                        map.remove(min[left-1]);
                    }
                }
            }
            answer = Math.max(answer, right-left+1);
        }
        System.out.println(answer);
    }
}
