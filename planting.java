import java.io.*;
import java.util.*;

public class planting {
    static int N;
    static int[] seeds;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("planting.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
        N = Integer.parseInt(bf.readLine());
        seeds = new int[N];
        Arrays.fill(seeds, -1);
        graph = new ArrayList<>();
        for (int i = 0; i < N; i++){
            ArrayList<Integer> z = new ArrayList<>();
            graph.add(z);
        }
        for(int i = 0; i < N-1; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        seeds[0] = 1;
        for(int i = 1; i < N; i++){
            ArrayList<Integer> needToVisit = new ArrayList<>();
            needToVisit.addAll(graph.get(i));
            int size = needToVisit.size();
            for(int c = 0; c < size; c++){
                ArrayList<Integer> temp = visit(needToVisit.get(c));
                needToVisit.addAll(temp);
            }
            while(needToVisit.indexOf(i) != -1) needToVisit.remove(needToVisit.indexOf(i));
            LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(needToVisit);
            ArrayList<Integer> neighbors = new ArrayList<>(hashSet);
            ArrayList<Integer> neighborseeds = new ArrayList<>();
            for(int c = 0; c < neighbors.size(); c++){
                neighborseeds.add(seeds[neighbors.get(c)]);
            }
            int ans = 1;
            for(int c = 1; c <= Collections.max(neighborseeds)+1; c++){
                if(neighborseeds.indexOf(c) == -1){
                    ans = c;
                    break;
                }
            }
            seeds[i] = ans;
        }
        ArrayList<Integer> visited = new ArrayList<>();
        int ans = 0;
        for(int i = 0; i < N; i++){
            if(visited.indexOf(seeds[i]) == -1){
                visited.add(seeds[i]);
                ans++;
            }
        }
        pw.println(ans);
        pw.close();
    }
    public static ArrayList<Integer> visit(int c){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.addAll(graph.get(c));
        return arr;
    }
}
