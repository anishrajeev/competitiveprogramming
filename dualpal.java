import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
ID: anish.rd
LANG: JAVA
TASK: dualpal
*/
public class dualpal {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("dualpal.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int S = Integer.parseInt(stk.nextToken());
        for(int i = 0; i < N; i++){
            S++;
            int counter = 0;
            for(int z = 2; z <= 10; z++){
                List<String> num = basecalculator(z, S);
                List<String> reversenum = basecalculator(z, S);
                Collections.reverse(reversenum);
                if(reversenum.equals(num)) counter++;
            }
            if(counter >= 2) pw.println(S);
            else i--;
        }
        pw.close();
    }
    public static ArrayList<String> basecalculator(int b, int num){
        ArrayList<String> numbase = new ArrayList<>();
        while(num > 0){
            int remainder = num%b;
            String remainderstring;
            if(remainder > 9){
                char temp = (char)(remainder+55);
                remainderstring = Character.toString(temp);
            }
            else{
                remainderstring = Integer.toString(remainder);
            }
            numbase.add(remainderstring);
            num = num/b;
        }
        Collections.reverse(numbase);
        return numbase;
    }
}
