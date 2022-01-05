import java.util.*;
import java.io.*;
public class art2 {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("art2.in"));
        PrintWriter pw = new PrintWriter("art2.out");
        int N = Integer.parseInt(bf.readLine());
        int[] art = new int[N];
        int[] xmin = new int[N];
        int[] xmax = new int[N];
        Arrays.fill(xmin, Integer.MAX_VALUE);
        Arrays.fill(xmax, 0);
        for(int i = 0; i < N; i++)art[i] = Integer.parseInt(bf.readLine())-1;
        for(int i = 0; i < N; i++){
            int color = art[i];
            if(color==-1)continue;
            xmin[color] = Math.min(xmin[color], i);
            xmax[color] = Math.max(xmax[color], i);
        }
        for(int i = 0; i < N; i++){
            if(xmin[i]==Integer.MAX_VALUE){
                xmin[i] = -1;
                xmax[i] = -1;
            }
        }
        ArrayList<Integer> arr = new ArrayList<>();
        int res = 0;
        for(int i = 0; i < N; i++){
            int c = art[i];
            if(c == -1)continue;
            if (i == xmin[c]) {
                arr.add(c);
                res = Math.max(res, arr.size());
            }
            if (arr.get(arr.size()-1) != c) {
                res = -1;
                break;
            }

            if (i == xmax[c]) {
                arr.remove(arr.size()-1);
            }
        }
        pw.println(res);
        pw.close();
    }
}
