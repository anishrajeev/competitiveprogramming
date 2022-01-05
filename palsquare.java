import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
/*
ID: anish.rd
LANG: JAVA
TASK: palsquare
*/
public class palsquare {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("palsquare.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
        int b = Integer.parseInt(bf.readLine());
        for(int i = 1; i <= 300; i++){
            int square = i*i;
            ArrayList<String> numbase = new ArrayList<>();
            numbase = basecalculator(b, square);
            ArrayList<String> reversenumbase = new ArrayList<>();
            reversenumbase.addAll(numbase);
            Collections.reverse(reversenumbase);
            if(reversenumbase.equals(numbase)){
                String number = "";
                String Ithnumber = "";
                ArrayList<String> Ith = new ArrayList<>();
                Ith = basecalculator(b, i);
                for(int z = 0; z < numbase.size(); z++){
                    number = number + numbase.get(z);
                }
                for(int z = 0; z < Ith.size(); z++){
                    Ithnumber = Ithnumber + Ith.get(z);
                }
                pw.println(Ithnumber + " " + number);
            }
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
