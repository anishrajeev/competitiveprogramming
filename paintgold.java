import java.util.*;
import java.io.*;
public class paintgold {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("paint.in"));
        PrintWriter pw = new PrintWriter("paint.out");
        ArrayList<move> steps = new ArrayList<>();
        TreeSet<Integer> points = new TreeSet<>();
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int bessiepos = 0;
        for(int i = 0; i < N; i++){
            points.add(bessiepos);
            stk = new StringTokenizer(bf.readLine());
            int amount = Integer.parseInt(stk.nextToken());
            String dir = stk.nextToken();
            if(dir.equals("R"))bessiepos+=amount;
            else bessiepos-=amount;
            steps.add(new move(amount, dir));
        }
        points.add(bessiepos);
        int[] psum = new int[points.size()];
        HashMap<Integer, Integer> index = new HashMap<>();
        int counter = 0;
        for(int i:points){
            index.put(i, counter);
            counter++;
        }
        bessiepos = 0;
        for(move m:steps){
            int amount = m.a;
            String dir = m.d;
            int lastpos = bessiepos;
            if(dir.equals("R"))bessiepos+=amount;
            else bessiepos-=amount;
            int lastposr = Math.min(lastpos, bessiepos);
            int bessieposr = Math.max(lastpos, bessiepos);
            psum[index.get(lastposr)]++;
            psum[index.get(bessieposr)]--;
        }
        ArrayList<Integer> rindex = new ArrayList<>();
        rindex.addAll(points);
        int[] realpsum = new int[psum.length];
        realpsum[0] = psum[0];
        for(int i = 1; i < psum.length; i++)realpsum[i] = realpsum[i-1]+psum[i];
        int size = 0;
        long answer = 0;
        for(int i = 0; i < realpsum.length; i++){
            if(realpsum[i]>=K){
                size++;
            }
            else{
                int ogindex = i-size;
                int length = rindex.get(ogindex+size)-rindex.get(ogindex);
                answer+=length;
                size = 0;
            }
        }
        pw.println(answer);
        pw.close();
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