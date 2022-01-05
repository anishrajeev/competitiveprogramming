import java.util.*;
import java.io.*;
public class cownomicsbruteforce {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter pw = new PrintWriter("cownomics.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        TreeSet<Character>[] unspotted = new TreeSet[M];
        ArrayList<Character[]> unspottedv2 = new ArrayList<>();
        ArrayList<String> spotted = new ArrayList<>();
        int answer = 0;
        for(int i = 0;i < M; i++){
            unspotted[i]=new TreeSet<>();
        }
        for(int i = 0; i < N; i++){
            spotted.add(bf.readLine());
            unspottedv2.add(new Character[M]);
        }
        for(int i = 0; i < N; i++){
            String s = bf.readLine();
            for(int c = 0; c < M; c++){
                unspotted[c].add(s.charAt(c));
                unspottedv2.get(i)[c]=s.charAt(c);
            }
        }
        for (int i = 0; i < M; i++) {
            for (int c = i + 1; c < M; c++) {
                for (int d = c + 1; d < M; d++) {
                    boolean pos = true;
                    for(int word = 0; word<N; word++){
                        String s = spotted.get(word);
                        Character ci = s.charAt(i);
                        Character cc = s.charAt(c);
                        Character cd = s.charAt(d);
                        for(int z = 0; z < N; z++){
                            if(unspottedv2.get(z)[i]==ci&&unspottedv2.get(z)[c]==cc&&unspottedv2.get(z)[d]==cd)pos = false;
                        }
                    }
                    if(pos){
                        answer++;
                        //System.out.println(i + " " + c + " " + d);
                    }
                }
            }
        }
        pw.println(answer);
        pw.close();
    }
}
