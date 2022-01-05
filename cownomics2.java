import java.util.*;
import java.io.*;
public class cownomics2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter pw = new PrintWriter("cownomics.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        TreeSet<String> unspotted[][][] = new TreeSet[M][M][M];
        ArrayList<String> arrspot = new ArrayList<>();
        ArrayList<String> arrunspot = new ArrayList<>();
        int answer = 0;
        for(int i = 0; i < N; i++){
            arrspot.add(bf.readLine());
        }
        for(int i = 0; i < N; i++){
            arrunspot.add(bf.readLine());
        }
        for(int i = 0; i < M; i++){
            for(int c = 0; c < M; c++){
                for(int z = 0; z < M; z++){
                    unspotted[i][c][z]=new TreeSet<>();
                }
            }
        }
        for(int i = 0; i < M; i++){
            for(int c = i+1; c < M; c++){
                for(int d = c+1; d < M; d++){
                    for(int words = 0; words < N; words++){
                        String s = arrunspot.get(words);
                        StringBuilder sb = new StringBuilder();
                        sb.append(s.charAt(i));
                        sb.append(s.charAt(c));
                        sb.append(s.charAt(d));
                        unspotted[i][c][d].add(sb.toString());
                    }
                }
            }
        }
        for(int i = 0; i < M; i++){
            for(int c = i+1; c < M; c++){
                for(int d = c+1; d < M; d++){
                    boolean pos = true;
                    for(int words = 0; words < N; words++){
                        String s = arrspot.get(words);
                        StringBuilder sb = new StringBuilder();
                        sb.append((s.charAt(i)));
                        sb.append((s.charAt(c)));
                        sb.append((s.charAt(d)));
                        if(unspotted[i][c][d].contains(sb.toString())){
                            pos = false;
                            break;
                        }
                    }
                    if(pos){
                        //System.out.println(i + " " + c + " "  + d);
                        answer++;
                    }
                }
            }
        }
        pw.println(answer);
        pw.close();
    }
}