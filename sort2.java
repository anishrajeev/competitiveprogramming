import java.io.*;
import java.util.Arrays;

public class sort2 {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("sort.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
        int count = 0;
        int N = Integer.parseInt(bf.readLine());
        int[] A = new int[N];
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(bf.readLine());
        }
        boolean sorted = false;
        while (!sorted){
            sorted = true;
            count++;
            System.out.println(Arrays.toString(A));
            for(int i = 0; i <= N-2; i++){
                if(A[i+1] < A[i]){
                    int temp = A[i];
                    A[i] = A[i+1];
                    A[i+1] = temp;
                    sorted = false;
                }
            }
        }
        pw.println(count);
        pw.close();
    }
}
