import java.util.*;
import java.io.*;
public class paint {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("paint.in"));
        PrintWriter pw = new PrintWriter("paint.out");
        int bessiepos = 0;
        int N = Integer.parseInt(bf.readLine());
        TreeSet<Integer> interesting = new TreeSet<>();
        ArrayList<movement> steps = new ArrayList<>();
        for(int i = 0; i < N; i++){
            interesting.add(bessiepos);
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int amount = Integer.parseInt(stk.nextToken());
            String dir = stk.nextToken();
            if(dir.equals("R"))bessiepos+=amount;
            else bessiepos-=amount;
            steps.add(new movement(amount, dir));
        }
        interesting.add(bessiepos);
        int[] psum = new int[interesting.size()];
        HashMap<Integer, Integer> index = new HashMap<>();
        int counter = 0;
        for(int i:interesting){
            index.put(i, counter);
            counter++;
        }
        bessiepos = 0;
        for(movement m:steps){
            int amount = m.amount;
            String dir = m.dir;
            int lastpos = bessiepos;
            if(dir.equals("R"))bessiepos+=amount;
            else bessiepos-=amount;
            int lastposr = Math.min(lastpos, bessiepos);
            int bessieposr = Math.max(lastpos, bessiepos);
            psum[index.get(lastposr)]++;
            psum[index.get(bessieposr)]--;
        }
        ArrayList<Integer> rindex = new ArrayList<>();
        rindex.addAll(interesting);
        int[] realpsum = new int[psum.length];
        realpsum[0] = psum[0];
        for(int i = 1; i < psum.length; i++)realpsum[i] = realpsum[i-1]+psum[i];
        long answer = 0;
        int size = 0;
        for(int i = 0; i < realpsum.length; i++){
            if(realpsum[i]>=2){
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
    public static class movement{
        int amount;
        String dir;
        public movement(int a, String d){
            amount = a;
            dir = d;
        }
    }
}
