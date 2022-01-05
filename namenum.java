import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
/*
ID: anish.rd
LANG: JAVA
TASK: namenum
*/
public class namenum {
    public static void main(String args[]) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("namenum.in"));
        long num = Long.parseLong(bf.readLine());
        ArrayList<Long> nums = new ArrayList<>();
        while(num != 0){
            nums.add(num%10);
            num = num/10;
        }
        Collections.reverse(nums);
        BufferedReader bfname = new BufferedReader(new FileReader("dict.txt"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
        String line = "";
        HashMap<Long, List<String>> Key = new HashMap<Long, List<String>>();
        for(long i = 2; i < 10; i++){
            List<String> letters = new ArrayList<String>();
            for(int c = 0; c < 3; c++){
                if(i == 7){
                    letters.add("P");
                    letters.add("R");
                    letters.add("S");
                    break;
                }
                else if(i == 8){
                    letters.add("T");
                    letters.add("U");
                    letters.add("V");
                    break;
                }
                else if(i == 9){
                    letters.add("W");
                    letters.add("X");
                    letters.add("Y");
                    break;
                }
                else{
                    long number = 59 + i*3 + c;
                    char character = (char)number;
                    letters.add(Character.toString(character));
                }
            }
            Key.put(i, letters);
        }
        List<String> possible = new ArrayList<String>();
        List<String> letters = new ArrayList<>(Key.get(nums.get(0)));
        while((line = bfname.readLine()) != null){
            if(line.length() == nums.size() && letters.contains(line.substring(0,1))){
               possible.add(line);
            }
        }
        for(int i = 1; i < nums.size(); i++){
            List<String> possibleletters = Key.get(nums.get(i));
            for(int z = 0; z < possible.size(); z++){
                String[] word = possible.get(z).split("");
                if(!(possibleletters.contains(word[i]))){
                    possible.remove(z);
                    z--;
                }
            }
        }
        Collections.sort(possible);
        if(possible.size() == 0) pw.println("NONE");
        else{
            for(int i = 0; i < possible.size(); i++){
                pw.println(possible.get(i));
            }
        }
        pw.close();
    }
}
