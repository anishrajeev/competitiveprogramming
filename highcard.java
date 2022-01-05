import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class highcard {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("highcard.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
        int N = Integer.parseInt(bf.readLine());
        int counter = 0;
        ArrayList<Integer> Elsie = new ArrayList<>();
        ArrayList<Integer> Bessie = new ArrayList<>();
        ArrayList<Integer> Elsie2 = new ArrayList<>();
        for(int i = 0; i < N; i++){
            Elsie.add(Integer.parseInt(bf.readLine()));
        }
        Elsie2.addAll(Elsie);
        Collections.sort(Elsie2);
        System.out.println(Elsie2);
        for(int i = 1; i < Elsie2.get(0); i++){
            Bessie.add(i);
        }
        for(int i = 0; i < N-1; i++){
            int one = Elsie2.get(i);
            int two = Elsie2.get(i+1);
            for(int z = one+1; z < two; z++){
                Bessie.add(z);
            }
        }
        for(int i = Elsie2.get(Elsie2.size()-1) + 1; i <= 2*N; i++){
            Bessie.add(i);
        }
        System.out.println(Bessie);
        for(int i = 0; i < N; i++){
            int ElsieCard = Elsie.get(i);
            int index = findLowestValueGreater(ElsieCard, Bessie);
            if(index != -1){
                Bessie.remove(index);
                counter++;
            }
            else{
                Bessie.remove(0);
            }
        }
        pw.println(counter);
        pw.close();
    }
    public static int findLowestValueGreater(int target, ArrayList<Integer> arr){
        int start = 0, end = arr.size() - 1;
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (arr.get(mid) <= target) {
                start = mid + 1;
            }
            else {
                ans = mid;
                end = mid - 1;
            }
        }
        return ans;
    }
}
