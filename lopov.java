import java.io.*;
import java.util.*;
public class lopov {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("tester.in"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        Jewel[] jewels = new Jewel[N];
        TreeSet<Integer> bags = new TreeSet<>();
        HashMap<Integer, Integer> amount = new HashMap<>();

        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            jewels[i] = new Jewel(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
        }
        //System.out.println("Test1");
        for(int i = 0; i < K; i++){
            int x = Integer.parseInt(bf.readLine());
            if(bags.contains(x))amount.replace(x, amount.get(x)+1);
            else{
                bags.add(x);
                amount.put(x, 1);
            }
        }
        //System.out.println("Test2");
        Arrays.sort(jewels, Jewel.price());
        long answer = 0;
        for(int i = 0; i < jewels.length; i++){
            //System.out.println(i);
            Jewel j = jewels[i];
            Integer value = bags.higher(j.x-1);
            if(value==null)continue;
            answer+=j.y;
            amount.replace(value, amount.get(value)-1);
            if(amount.get(value)==0)bags.remove(value);
        }
        System.out.println(answer);
    }
    public static class Jewel {
        int x, y;
        public Jewel(int a, int b){
            x = a;
            y = b;
        }
        static Comparator<Jewel> price() {
            return (one, two) -> {
                if(one.y == two.y)return Integer.compare(one.x, two.x);
                return Integer.compare(two.y, one.y);
            };
        }
    }
}
