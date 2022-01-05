import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class berries {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("berries.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(bf.readLine());
        ArrayList<Integer> trees = new ArrayList<>();
        for(int i = 0; i < N; i++){
            trees.add(Integer.parseInt(stk.nextToken()));
        }
        Collections.sort(trees);
        int max = trees.get(trees.size()-1);
        int answer = 0;
        for(int i = 1; i <= max; i++){
            int baskets = K;
            ArrayList<Integer> treesrep = new ArrayList<>();
            treesrep.addAll(trees);
            int[] basketstaken = new int[N];
            ArrayList<Integer> basketsc = new ArrayList<>();
            Collections.reverse(treesrep);
            for(int c = 0; c < treesrep.size() && baskets != 0; c++){
                int availablebaskets = treesrep.get(c)/i;
                if(baskets<availablebaskets){
                    basketstaken[c] = baskets;
                    treesrep.set(c, treesrep.get(c)-baskets*i);
                    basketsc.add(baskets*i);
                    baskets = 0;
                }
                else{
                    basketstaken[c] = availablebaskets;
                    for(int z = 0; z < availablebaskets; z++){
                        basketsc.add(i);
                    }
                    treesrep.set(c, treesrep.get(c)%i);
                    baskets -= availablebaskets;
                }
            }
            if(baskets > 0){
                Collections.sort(treesrep);
                Collections.reverse(treesrep);
                int counter = 0;
                while(baskets > 0 && counter < treesrep.size()){
                    basketsc.add(treesrep.get(counter));
                    counter++;
                    baskets--;
                }
            }
            Collections.sort(basketsc);
            int bessiesb = basketsc.size()/2;
            int bessie = 0;
            for(int c = 0; c < bessiesb; c++){
                bessie+=basketsc.get(c);
            }
            answer = Math.max(answer, bessie);
        }
        pw.println(answer);
        pw.close();
    }
}
