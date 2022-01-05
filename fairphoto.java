import java.util.*;
import java.io.*;
public class fairphoto {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("fairphoto.in"));
        PrintWriter pw = new PrintWriter("fairphoto.out");
        int N = Integer.parseInt(bf.readLine());
        long answer = 0;
        ArrayList<Cow> cows = new ArrayList<>();
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(stk.nextToken());
            String breed = stk.nextToken();
            if(breed.equals("H")) cows.add(new Cow(x, 1));
            else cows.add(new Cow(x, -1));
        }
        Collections.sort(cows);
        int[] psum = new int[cows.size()];
        psum[0] = cows.get(0).breed;
        for(int i = 1; i < N; i++)psum[i] = psum[i-1] + cows.get(i).breed;
        //System.out.println(Arrays.toString(psum));
        HashMap<Integer, Cow> map = new HashMap<>();
        TreeSet<Integer> contains = new TreeSet<>();
        for(int i = 0; i < psum.length; i++){
            if(contains.contains(psum[i])){
                int startx = map.get(psum[i]).x;
                int newx = cows.get(i).x;
                map.replace(psum[i], new Cow(startx, newx));
            }
            else{
                contains.add(psum[i]);
                if(i != N-1) map.put(psum[i], new Cow(cows.get(i+1).x, cows.get(i+1).x));
            }
        }
        for(int i:map.keySet()){
            long distance = map.get(i).breed-map.get(i).x;
            answer = Math.max(answer, distance);
        }
        long counter = 1;
        long lastdist = cows.get(0).x;
        boolean isH = (cows.get(0).breed == 1);
        for(int i = 1; i < N; i++){
            int currbreed = cows.get(i).breed;
            if(currbreed==1&&isH){
                counter+=(cows.get(i).x-lastdist);
                lastdist = cows.get(i).x;
            }
            else if(currbreed==1&&!isH){
                isH = true;
                answer = Math.max(answer, counter);
                counter = 0;
                lastdist = cows.get(i).x;
            }
            else if(currbreed==-1&&isH){
                isH = false;
                answer = Math.max(answer, counter);
                counter = 0;
                lastdist = cows.get(i).x;
            }
            else if(currbreed==-1&&!isH){
                counter+=(cows.get(i).x-lastdist);
                lastdist = cows.get(i).x;
            }
        }
        pw.println(answer);
        pw.close();
    }
    public static class Cow implements Comparable{
        public int x;
        public int breed;
        public Cow(int loc, int b){
            x = loc;
            breed = b;
        }
        public void setBreed(int b){
            breed = b;
        }
        @Override
        public int compareTo(Object o) {
            return Integer.compare(x, ((Cow)o).x);
        }
    }
}
