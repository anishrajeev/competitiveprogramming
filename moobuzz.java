import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class moobuzz {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("moobuzz.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));
        int N = Integer.parseInt(bf.readLine());
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(14);
        numbers.add(1);
        numbers.add(2);
        numbers.add(4);
        numbers.add(7);
        numbers.add(8);
        numbers.add(11);
        numbers.add(13);
        int row = (N-1)/8;
        row = row*15;
        int col = N%8;
        int ans = numbers.get(col) + row;
        pw.println(ans);
        pw.close();
    }
}
