import java.util.*;
import java.io.*;
public class lazy {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("lazy.in"));
        PrintWriter pw = new PrintWriter("lazy.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());
        ArrayList<patch> patch = new ArrayList<patch>();
        for(int i = 0; i < n; i++){
            stk = new StringTokenizer(bf.readLine());
            int g = Integer.parseInt(stk.nextToken());
            int x = Integer.parseInt(stk.nextToken());
            patch.add(new patch(x, g));
        }
        Collections.sort(patch);
        int used = 0;
        int maxx = patch.get(n-1).x;
        for(int i = 0; i < maxx; i++){
            if(i < patch.get(used).x)patch.add(new patch(i, 0));
            else if(i == patch.get(used).x)used++;
        }
        Collections.sort(patch);
        int[] psum = new int[maxx+1];
        psum[0] = patch.get(0).g;
        for(int i = 1; i <= maxx; i++) psum[i] = psum[i-1] + patch.get(i).g;
        int p1 = 1-k;
        int p2 = k+1;
        int x = 0;
        int sum = 0;
        int answer = 0;
        if(k > maxx) answer = psum[maxx];
        else{
            sum = psum[k-1];
            answer = sum;
            while(x<=maxx){
                int nsum = 0;
                if(p1<=0) nsum = psum[p2];
                else if(p2>maxx) nsum = psum[maxx]-psum[p1-1];
                else nsum = psum[p2]-psum[p1-1];
                answer = Math.max(answer, nsum);
                x++;
                p1++;
                p2++;
            }
        }
        pw.println(answer);
        pw.close();
    }
    public static class patch implements Comparable{
        int x, g;
        public patch(int y, int z){
            x = y;
            g = z;
        }

        @Override
        public int compareTo(Object o) {
            return Integer.compare(x, ((patch)o).x);
        }
    }
}
