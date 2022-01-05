import java.util.*;
import java.io.*;
public class proximity {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("proximity.in"));
        PrintWriter pw = new PrintWriter("proximity.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int[] cows = new int[N];
        TreeSet<Integer> breeds = new TreeSet<>();
        for(int i = 0; i < N; i++){
            int a = Integer.parseInt(bf.readLine());
            breeds.add(a);
            cows[i] = a;
        }
        int maxcow = breeds.pollLast();
        int[] maxdistance = new int[maxcow+2];
        ArrayList<Integer>[] distances = new ArrayList[maxcow+2];
        for(int i = 0; i < distances.length; i++)distances[i] = new ArrayList<>();
        for(int i = 0; i < N; i++){
            int breed = cows[i];
            distances[breed].add(i);
        }
        for(int i = 0; i < N; i++){
            int breed = cows[i];
            int index = Collections.binarySearch(distances[breed], i);
            if(distances[breed].size()==1)continue;
            if(index == 0){
                int distance = Math.abs(distances[breed].get(1)-distances[breed].get(0));
                if(distance <= K) maxdistance[breed] = Math.max(maxdistance[breed], distance);
            }
            else if(index == distances[breed].size()-1){
                int distance = Math.abs(distances[breed].get(index)-distances[breed].get(index-1));
                if(distance <= K) maxdistance[breed] = Math.max(maxdistance[breed], distance);
            }
            else{
                int distance = Math.abs(distances[breed].get(index)-distances[breed].get(index-1));
                int distance2 = Math.abs(distances[breed].get(index+1)-distances[breed].get(index));
                if(distance <= K && distance2 <= K) maxdistance[breed] = Math.max(maxdistance[breed], Math.max(distance, distance2));
                else if(distance <= K) maxdistance[breed] = Math.max(maxdistance[breed], distance);
                else if(distance <= K) maxdistance[breed] = Math.max(maxdistance[breed], distance2);
            }
        }
        long answer = 0;
        for(int i = 0; i < maxdistance.length; i++){
            if(maxdistance[i]!=0)answer = i;
        }
        pw.println(answer);
        pw.close();
    }
}
