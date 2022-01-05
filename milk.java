import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
ID: anish.rd
LANG: JAVA
TASK: milk
*/
public class milk {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("milk.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        ArrayList<Integer> price = new ArrayList<>();
        ArrayList<Integer> unitsAvailable = new ArrayList<>();
        int counter = 0;
        int totalCents = 0;
        for(int i = 1; i <= M; i++){
            StringTokenizer stk2 = new StringTokenizer(bf.readLine());
            price.add(Integer.parseInt(stk2.nextToken()));
            unitsAvailable.add(Integer.parseInt(stk2.nextToken()));
        }
        while(counter < N){
           int minindex = 0;
           int min = 1001;
           for(int i = 0; i < unitsAvailable.size(); i++){
               if(price.get(i) < min){
                   minindex = i;
                   min = price.get(i);
               }
           }
           if(unitsAvailable.get(minindex) + counter > N){
               totalCents = totalCents + (N - counter)*price.get(minindex);
               counter = (N - counter) + counter;
           }
           else{
               counter += unitsAvailable.get(minindex);
               totalCents += price.get(minindex)*unitsAvailable.get(minindex);
               price.remove(minindex);
               unitsAvailable.remove(minindex);
           }
        }
        pw.println(totalCents);
        pw.close();
    }
}
