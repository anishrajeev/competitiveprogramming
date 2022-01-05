import java.io.*;
import java.util.*;

public class cbarn2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
        int N = Integer.parseInt(bf.readLine());
        int[] cows = new int[N];
        long ans = Long.MAX_VALUE;
        for (int i=0; i<N; i++){
            cows[i] = Integer.parseInt(bf.readLine());
        }
        for(int i = 0; i < N; i++){
            //System.out.println("DOOR " + i);
            int[] rotated = new int[N];
            for(int c = 0; c < N; c++){
                rotated[c]=cows[(i+c)%N];
            }
            //System.out.println(Arrays.toString(rotated));
            boolean visited[] = new boolean[N];
            Arrays.fill(visited, false);
            ArrayList<Integer> arr = new ArrayList<>();
            long energy = 0;
            int nextlocation = 0;
            boolean backwardsmode = false;
            for(int c = 0; c < N; c++){
                //System.out.println();
                //System.out.println("C IS  " + c);
                int amount = rotated[c];
                for(int d = 0; d < amount; d++){
                    if(!backwardsmode)energy+=Math.pow(nextlocation-c,2);
                    if(backwardsmode)energy+=Math.pow((N-c+nextlocation),2);
                    visited[nextlocation] = true;
                    nextlocation++;
                    Collections.sort(arr);
                    if((nextlocation>=N || backwardsmode) && arr.size()>0){
                        nextlocation=arr.remove(0);
                        backwardsmode = true;
                    }
                }
                if(nextlocation <= c && !backwardsmode)nextlocation = c+1;
                if(!visited[c])arr.add(c);
            }
            //System.out.println("ENERGY IS " + energy);
            ans = Math.min(energy, ans);
        }
        pw.println(ans);
        pw.close();
    }
}
