import java.io.*;
import java.util.*;

public class lanci {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());
        //StringTokenizer stk = new StringTokenizer(bf.readLine());
        ArrayList<Integer> chains = new ArrayList<>();
        for(int i = 0; i < N; i++){
            chains.add(Integer.parseInt(bf.readLine()));
            //chains.add(Integer.parseInt(stk.nextToken()));
        }
        Collections.sort(chains);
        int links = 0;
        while(links < chains.size()){
            if(links + chains.get(0) <= chains.size()-1){
                links += chains.remove(0);
            }
            else{
                int nsize = chains.get(0)-(chains.size()-links);
                chains.set(0, nsize);
                links = chains.size()-1;
            }
        }
        pw.println(links);
        pw.close();
    }
}