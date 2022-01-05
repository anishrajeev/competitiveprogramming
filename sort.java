import java.io.*;
import java.util.*;
public class sort {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("sort.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
        int N = Integer.parseInt(bf.readLine());
        ArrayList<Integer> A = new ArrayList<Integer>();
        ArrayList<Integer> B = new ArrayList<Integer>();
        ArrayList<Integer> C = new ArrayList<Integer>();
        for(int i = 0; i < N; i++){
            int a = Integer.parseInt(bf.readLine());
            A.add(a);
            B.add(a);
        }
        Collections.sort(B);
        for(int i = 0; i < N; i++){
            C.add(Collections.binarySearch(B, A.get(i)));
        }
        int max = 0;
        for(int i = 0; i < N; i++){
            if(i-C.get(i) > max)max = i-C.get(i);
        }
        max++;
        pw.println(max);
        pw.close();
    }
}
