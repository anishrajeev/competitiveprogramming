import java.io.*;
import java.util.*;

/*
ID: anish.rd
LANG: JAVA
TASK: barn1
*/
public class barn1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("barn1.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int M = Integer.parseInt(stk.nextToken());
        int S = Integer.parseInt(stk.nextToken());
        int C = Integer.parseInt(stk.nextToken());
        ArrayList<Integer> boards = new ArrayList<Integer>();
        ArrayList<Integer> cows = new ArrayList<Integer>();
        HashMap<Integer, Integer> gaps = new HashMap<>();
        for(int i = 0; i < C; i++){
            cows.add(Integer.parseInt(bf.readLine()));
        }
        Collections.sort(cows);
        if(cows.size() < M){
            pw.println(cows.size());
            pw.close();
        }
        else {
            for (int i = 1; i <= S; i++) {
                boards.add(i);
            }
            for (int i = 0; i < cows.size() - 1; i++) {
                gaps.put(boards.indexOf(cows.get(i) + 1), cows.get(i + 1) - cows.get(i) - 1);
            }
            System.out.println(gaps);
            for (int i = 0; i < M - 1; i++) {
                int key = Collections.max(gaps.entrySet(), Map.Entry.comparingByValue()).getKey();
                int k = boards.indexOf(key + 1);
                System.out.println("Removing gap after " + key);
                for (int c = key + 1; c <= gaps.get(key) + key; c++) {
                    boards.remove(k);
                }
                gaps.remove(key);
            }
            for (int i = 1; i < cows.get(0); i++) {
                boards.remove(0);
            }
            for (int i = S; i > cows.get(cows.size() - 1); i--) {
                boards.remove(boards.size() - 1);
            }
            System.out.println(boards);
            pw.println(boards.size());
            pw.close();
        }
    }
}
