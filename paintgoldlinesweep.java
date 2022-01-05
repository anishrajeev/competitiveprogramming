import java.util.*;
import java.io.*;
public class paintgoldlinesweep {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("paint.in"));
        PrintWriter pw = new PrintWriter("paint.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        ArrayList<move> steps = new ArrayList<>();
        ArrayList<Integer> moves = new ArrayList<>();
        int bessiepos = 0;
        for(int i = 0; i < N; i++){
            moves.add(bessiepos);
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken());
            String d = stk.nextToken();
            steps.add(new move(a, d));
            if(d.equals("R"))bessiepos+=a;
            else bessiepos-=a;
        }
        moves.add(bessiepos);
        TreeSet<event> set = new TreeSet<>(new Comparator<event>() {
            @Override
            public int compare(event o1, event o2) {
                if(o1.x==o2.x)return Integer.compare(o1.id, o2.id);
                return Integer.compare(o1.x, o2.x);
            }
        });
        for(int i = 0; i < N; i++){
            int one = Math.min(moves.get(i), moves.get(i+1));
            int negativeone = Math.max(moves.get(i), moves.get(i+1));
            set.add(new event(one, 1, i));
            set.add(new event(negativeone, -1, i));
        }
        int curr = 0;
        long answer = 0;
        ArrayList<event> arr = new ArrayList<>();
        arr.addAll(set);
        for(int i = 0; i < arr.size(); i++){
            event e = arr.get(i);
            curr+=e.z;
            if(curr>=K && i!=N-1){
                answer+=(arr.get(i+1).x-e.x);
            }
        }
        pw.println(answer);
        pw.close();
    }
    public static class event{
        int x, z, id;
        public event(int c, int d, int i){
            x = c;
            z = d;
            id = i;
        }
    }
    public static class move{
        int a;
        String d;
        public move(int am, String dir){
            a = am;
            d = dir;
        }
    }
}