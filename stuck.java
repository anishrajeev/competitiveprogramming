import java.io.*;
import java.util.*;
public class stuck {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int N = Integer.parseInt(bf.readLine());
        TreeSet<pair> East = new TreeSet<>(new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                return Integer.compare(o1.y, o2.y);
            }
        });
        HashMap<pair, Integer> cownum = new HashMap<>();
        pair[] cows = new pair[N];
        HashMap<pair, Integer> dir = new HashMap<>();
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            String dirstr = stk.nextToken();
            int dirint = 0;
            if(dirstr.equals("N"))dirint = 1;
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            cows[i] = new pair(x, y);
            dir.put(cows[i], dirint);
            graph[i] = new ArrayList<>();
            cownum.put(cows[i], i);
        }
        Arrays.sort(cows);
        for(int i = 0; i < N; i++){
            //Cow going east
            pair cow = cows[i];
            if(dir.get(cow)==0){
                East.add(cows[i]);
            }
            //Cow going north
            else{
                pair mintime = new pair(Integer.MAX_VALUE, 0);
                TreeSet<pair> toremove = new TreeSet<>();
                for(pair p:East){
                    if(p.y<cow.y)continue;
                    pair collision = new pair(cow.x, p.y);
                    int timenorth = p.y-cow.y;
                    int timeeast = cow.x-p.x;
                    if(timenorth<timeeast){
                        graph[cownum.get(p)].add(cownum.get(cow));
                        toremove.add(p);
                    }
                    else if(timenorth>timeeast){
                        graph[cownum.get(cow)].add(cownum.get(p));
                        break;
                    }
                }
                East.removeAll(toremove);
            }
        }
        //System.out.println(Arrays.toString(graph));
        int[] answer = new int[N];
        for(int i = 0; i < N; i++){
            Stack<Integer> s = new Stack<>();
            s.push(i);
            while(!s.isEmpty()){
                int n = s.pop();
                for(int c = 0; c < graph[n].size(); c++){
                    s.push(graph[n].get(c));
                    answer[graph[n].get(c)] = answer[graph[n].get(c)]+1;
                }
            }
        }
        for(int i = 0; i < N; i++){
            System.out.println(answer[i]);
        }
    }
    public static class pair implements Comparable<pair>{
        int x, y;
        public pair(int a, int b){
            x = a;
            y = b;
        }

        @Override
        public int compareTo(pair o) {
            return Integer.compare(x, o.x);
        }
    }
}
