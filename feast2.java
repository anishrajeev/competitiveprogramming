import java.util.*;
import java.io.*;
public class feast2 {
    static int T;
    static int X;
    static int Y;
    static int max;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("feast.in"));
        PrintWriter pw = new PrintWriter("feast.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        T = Integer.parseInt(stk.nextToken());
        X = Integer.parseInt(stk.nextToken());
        Y = Integer.parseInt(stk.nextToken());
        max = 0;
        recurse(0, true);
        pw.println(max);
        pw.close();
    }
    public static void recurse(int f, boolean w){
        max = Math.max(f, max);
        if(f+X <= T)recurse(f+X, w);
        if(f+Y <= T)recurse(f+Y, w);
        if(w)recurse(f/2, false);
    }
}
