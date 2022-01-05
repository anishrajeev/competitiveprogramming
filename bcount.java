import java.io.*;
import java.util.*;
public class bcount {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("bcount.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
        ArrayList<Integer> line = new ArrayList<>();
        StringTokenizer stkN = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stkN.nextToken());
        int Q = Integer.parseInt(stkN.nextToken());
        ArrayList<Integer> Holsteins = new ArrayList<>();
        ArrayList<Integer> Guernseys = new ArrayList<>();
        ArrayList<Integer> Jerseys = new ArrayList<>();
        int Htotal = 0;
        int Gtotal = 0;
        int Jtotal = 0;
        for(int i = 0; i < N; i++){
            line.add(Integer.parseInt(bf.readLine()));
        }
        for(int i = 0; i < line.size(); i++){
            if(line.get(i) == 1){
                Htotal++;
            }
            else if(line.get(i) == 2){
                Gtotal++;
            }
            else{
                Jtotal++;
            }
            Holsteins.add(Htotal);
            Guernseys.add(Gtotal);
            Jerseys.add(Jtotal);
        }
        for(int i = 0; i < Q; i++){
            StringTokenizer stkQ = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stkQ.nextToken());
            int b = Integer.parseInt(stkQ.nextToken());
            int h = Holsteins.get(b-1) - Holsteins.get(a-1);
            if(line.get(a-1) == 1) h++;
            int g = Guernseys.get(b-1) - Guernseys.get(a-1);
            if(line.get(a-1) == 2) g++;
            int j = Jerseys.get(b-1) - Jerseys.get(a-1);
            if(line.get(a-1) == 3) j++;
            pw.println(h + " " + g + " " + j);
        }
        pw.close();
    }
}
