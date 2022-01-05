import java.util.*;
import java.io.*;
public class cowroute {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("cowroute.in"));
        PrintWriter pw = new PrintWriter("cowroute.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int A = Integer.parseInt(stk.nextToken())-1;
        int B = Integer.parseInt(stk.nextToken())-1;
        int N = Integer.parseInt(stk.nextToken());
        pair[][] mincost = new pair[1010][1010];
        pair[] d = new pair[1010];
        Arrays.fill(d, new pair(Long.MAX_VALUE-10000, Long.MAX_VALUE-10000));
        for(int i = 0; i < 1010; i++){
            Arrays.fill(mincost[i], new pair(Integer.MAX_VALUE, Integer.MAX_VALUE));
            mincost[i][i] = new pair(0, 0);
        }
        TreeSet<Integer> cities = new TreeSet<>();
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            int cost = Integer.parseInt(stk.nextToken()), amount = Integer.parseInt(stk.nextToken());
            ArrayList<Integer> route = new ArrayList<>();
            stk = new StringTokenizer(bf.readLine());
            for(int c = 0; c < amount; c++){
                route.add(Integer.parseInt(stk.nextToken())-1);
                cities.add(route.get(route.size()-1));
            }
            for(int c = 0; c < amount; c++){
                for(int z = 0; z < c; z++){
                    long minc = mincost[route.get(z)][route.get(c)].a;
                    if(cost<minc){
                        mincost[route.get(z)][route.get(c)] = new pair(cost, (c-z));
                    }
                    if(cost==minc){
                        long lesstrip = Math.min(mincost[route.get(z)][route.get(c)].b, c-z);
                        mincost[route.get(z)][route.get(c)] = new pair(cost, lesstrip);
                    }
                }
            }
        }
        TreeSet<Integer> visited = new TreeSet<>();
        d[A] = new pair(0, 0);
        while(visited.size()!=cities.size()){
            int lessnode = 0;
            long lessnodeval = Long.MAX_VALUE;
            for(int i:cities){
                if(d[i].a<lessnodeval && !visited.contains(i)){
                    lessnodeval = d[i].a;
                    lessnode = i;
                }
            }
            visited.add(lessnode);
            for(int i:cities){
                long cost = d[i].a;
                if(mincost[lessnode][i].a!=Integer.MAX_VALUE){
                    long newcost = mincost[lessnode][i].a+d[lessnode].a;
                    if(newcost<cost)
                        d[i] = new pair(newcost, d[lessnode].b+mincost[lessnode][i].b);
                    else if(newcost==cost)
                        d[i] = new pair(cost, Math.min(d[lessnode].b+mincost[lessnode][i].b, d[i].b));
                }
            }
        }
        if(d[B].a==(Long.MAX_VALUE-10000)||d[B].b==(Long.MAX_VALUE-10000))pw.println(-1 + " " + -1);
        else pw.println(d[B].a + " " + d[B].b);
        pw.close();
    }
    public static class pair{
        long a, b;
        public pair(long x, long y){
            a = x;
            b = y;
        }
    }
}
