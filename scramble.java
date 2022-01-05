import java.util.*;
import java.io.*;
public class scramble {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("scramble.in"));
        PrintWriter pw = new PrintWriter("scramble.out");
        int n = Integer.parseInt(bf.readLine());
        ArrayList<Str> highlow = new ArrayList<>();
        HashMap<String, Integer> high = new HashMap<>();
        HashMap<String, Integer> low = new HashMap<>();
        ArrayList<String> normal = new ArrayList<>();
        for(int i = 0; i < n; i++){
            String s = bf.readLine();
            StringBuilder highsb = new StringBuilder();
            StringBuilder lowsb = new StringBuilder();
            String[] strarr = s.split("");
            Arrays.sort(strarr);
            for(String c:strarr)
                highsb.append(c);
            for(int c = strarr.length-1; c >= 0; c--)
                lowsb.append(strarr[c]);
            Str highstr = new Str(highsb.toString(), s,false);
            Str lowstr = new Str(lowsb.toString(), s,true);
            highlow.add(highstr);
            highlow.add(lowstr);
            normal.add(s);
        }
        Collections.sort(highlow);
        int nonreversedcount = 0;
        int reversedcount = 0;
        for(int i = 0; i < 2*n; i++){
            Str s = highlow.get(i);
            if(!s.reversed){
                nonreversedcount++;
                high.put(s.normal, reversedcount);
            }
            else{
                reversedcount++;
                low.put(s.normal, nonreversedcount-1);
            }
        }
        for(int i = 0; i < n; i++){
            int highpos = high.get(normal.get(i))+1;
            int lowpos = low.get(normal.get(i))+1;
            pw.println(highpos + " " + lowpos);
        }
        pw.close();
    }
    public static class Str implements Comparable{
        String s;
        String normal;
        boolean reversed;
        public Str(String str, String n, boolean b){
            s = str;
            normal = n;
            reversed = b;
        }

        @Override
        public int compareTo(Object o) {
            return s.compareTo(((Str)o).s);
        }
    }
}
