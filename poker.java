import java.io.*;
import java.util.*;

public class poker {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("poker.in"));
        PrintWriter pw = new PrintWriter("poker.out");
        int N = Integer.parseInt(bf.readLine());
        int[] cards = new int[N];
        for(int i = 0; i < N; i++){
            cards[i] = Integer.parseInt(bf.readLine());
        }
        long answer = 0;
        int counter = 0;
        for(int i = 0; i < N; i++){
            if(cards[i]>counter){
                answer += cards[i]-counter;
            }
            counter = cards[i];
        }
        pw.println(answer);
        pw.close();
    }
}
