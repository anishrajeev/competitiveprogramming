import java.util.*;
import java.io.*;
public class crowded {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("crowded.in"));
        PrintWriter pw = new PrintWriter("crowded.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int D = Integer.parseInt(stk.nextToken());
        trashcow[] trashcows = new trashcow[N];
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int h = Integer.parseInt(stk.nextToken());
            trashcows[i] = new trashcow(x, h);
        }
        Arrays.sort(trashcows);
        cow[] cows = new cow[N];
        for(int i = 0; i < N; i++){
            cows[i] = new cow(trashcows[i].x, trashcows[i].h, i);
        }
        long crowded = 0;
        TreeSet<cow> left = new TreeSet<>();
        TreeSet<cow> right = new TreeSet<>();
        TreeSet<cow> lefth = new TreeSet<>(new Comparator<cow>() {
            @Override
            public int compare(cow o1, cow o2) {
                return Integer.compare(o1.h, o2.h);
            }
        });
        TreeSet<cow> righth = new TreeSet<>(new Comparator<cow>() {
            @Override
            public int compare(cow o1, cow o2) {
                return Integer.compare(o1.h, o2.h);
            }
        });
        int counter = 0;
        while(cows[counter].x <= D){
            right.add(cows[counter]);
            righth.add(cows[counter]);
            counter++;
        }
        for(int i = 1; i < N; i++){
            right.remove(cows[i]);
            righth.remove(cows[i]);
            right.remove(cows[i-1]);
            righth.remove(cows[i-1]);
            left.remove(cows[i]);
            lefth.remove(cows[i]);
            if(right.size()==0 && i != N-1){
                counter = cows[i+1].z;
                while(counter < N && cows[counter].x <= (cows[i].x+D)){
                    right.add(cows[counter]);
                    righth.add(cows[counter]);
                    counter++;
                }
            }
            else if(i != N-1){
                counter = right.last().z;
                while(counter < N && cows[counter].x <= cows[i].x+D){
                    right.add(cows[counter]);
                    righth.add(cows[counter]);
                    counter++;
                }
            }
            left.add(cows[i-1]);
            lefth.add(cows[i-1]);

            counter = left.first().z;
            while(counter < i && cows[counter].x < (cows[i].x-D)){
                left.remove(cows[counter]);
                lefth.remove(cows[counter]);
                counter++;
            }

            if(left.size()!=0 && right.size()!=0)if(lefth.last().h >= 2*cows[i].h && 2*cows[i].h <= righth.last().h)crowded++;
        }
        pw.println(crowded);
        pw.close();
    }
    public static class trashcow implements Comparable<trashcow>{
        int x, h;
        public trashcow(int xi, int hi){
            x = xi;
            h = hi;
        }
        @Override
        public int compareTo(trashcow o) {
            return Integer.compare(x, o.x);
        }
    }
    public static class cow implements Comparable<cow>{
        int x, h, z;
        public cow(int xi, int hi, int zi){
            x = xi;
            h = hi;
            z = zi;
        }
        @Override
        public int compareTo(cow o) {
            return Integer.compare(x, o.x);
        }
    }
}
