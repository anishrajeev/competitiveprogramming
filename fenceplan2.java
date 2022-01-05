import java.io.*;
import java.util.*;
public class fenceplan2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("fenceplan.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        boolean[] visited = new boolean[N];
        ArrayList<Integer> p = new ArrayList<>();
        //ArrayList<Boolean[]> connected2 = new ArrayList<Boolean[]>();
        ArrayList<Integer>[] connected = new ArrayList[N];
        ArrayList<Point> cows = new ArrayList<>();
        for(int c = 0; c < N; c++){
            //connected2.add(new Boolean[N]);
            connected[c] = new ArrayList<>();
        }
        //for(int c = 0; c < N; c++){
        //    Arrays.fill(connected2.get(c), false);
        //}
        for(int i = 0; i < N; i++){
            StringTokenizer stkxy = new StringTokenizer(bf.readLine());
            cows.add(new Point(Integer.parseInt(stkxy.nextToken()), Integer.parseInt(stkxy.nextToken())));
        }
        for(int i = 0; i < M; i++){
            StringTokenizer stkconnect = new StringTokenizer(bf.readLine());
            int cow1 = Integer.parseInt(stkconnect.nextToken())-1;
            int cow2 = Integer.parseInt(stkconnect.nextToken())-1;
            //connected2.get(cow1)[cow2] = true;
            //connected2.get(cow2)[cow1] = true;
            connected[cow1].add(cow2);
            connected[cow2].add(cow1);
        }
        for(int i = 0; i < N; i++){
            if(!visited[i]){
                visited[i] = true;
                int maxX = cows.get(i).x;
                int minX = cows.get(i).x;
                int maxY = cows.get(i).y;
                int minY = cows.get(i).y;
                Stack<Integer> stack = new Stack<>();
                for(int c = 0; c < N; c++){
                    if(connected[i].indexOf(c)!=-1){
                        stack.push(c);
                    }
                }
                while(!stack.isEmpty()){
                    int cow = stack.pop();
                    if(visited[cow]) continue;
                    visited[cow] = true;
                    if(cows.get(cow).x > maxX) maxX = cows.get(cow).x;
                    else if(cows.get(cow).x < minX) minX = cows.get(cow).x;
                    if(cows.get(cow).y > maxY) maxY = cows.get(cow).y;
                    else if(cows.get(cow).y < minY) minY = cows.get(cow).y;
                    for(int c = 0; c < N; c++){
                        if(connected[cow].indexOf(c)!=-1 && !visited[c]){
                            stack.push(c);
                        }
                    }
                }
                p.add(2*(maxX-minX) + 2*(maxY-minY));
            }
        }
        Collections.sort(p);
        pw.println(p.get(0));
        pw.close();
    }
    public static class Point{
        private int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}