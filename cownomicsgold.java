import java.util.*;
import java.io.*;
public class cownomicsgold {
    static ArrayList<String> spot;
    static ArrayList<String> plain;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter pw = new PrintWriter("cownomics.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        spot = new ArrayList<>();
        plain = new ArrayList<>();
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        for(int i = 0; i < N; i++){
            String s = bf.readLine();
            spot.add(s);
        }
        for(int i = 0; i < N; i++){
            String s = bf.readLine();
            plain.add(s);
        }
        int start = 0, end = M-1;
        boolean[] visiited = new boolean[M];
        while(start!=end){
            int mid = (start+end)/2;
            boolean b = valid(mid);
            if(b)end = mid;
            else start = mid+1;
        }
        pw.println(start);
        pw.close();
    }
    public static boolean valid(int n){
        int p1 = 0, p2 = n;
        boolean answer = false;
        while(p2<=M){
            TreeSet<String> spotset = new TreeSet<>();
            TreeSet<String> plainset = new TreeSet<>();
            for(int i = 0; i < N; i++){
                spotset.add(spot.get(i).substring(p1, p2));
            }
            for(int i = 0; i < N; i++){
                plainset.add(plain.get(i).substring(p1, p2));
            }
            boolean valid = true;
            for(String s:spotset){
                if (plainset.contains(s)) {
                    valid = false;
                    break;
                }
            }
            if(valid){
                answer = true;
                break;
            }
            p1++;
            p2++;
        }
        return answer;
    }
}
