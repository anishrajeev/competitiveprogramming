import java.io.*;
import java.util.*;
public class cowpatibility {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("cowpatibility.in"));
        PrintWriter pw = new PrintWriter("cowpatibility.out");
        int N = Integer.parseInt(bf.readLine());
        long answer = (N*(N-1))/2;
        int[][] flavors = new int[N][5];
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            for(int c = 0; c < 5; c++){
                flavors[i][c] = Integer.parseInt(stk.nextToken());
            }
        }
        HashMap<Integer, Integer> ones = new HashMap<>();
        HashMap<TreeSet, Integer> twos = new HashMap<>();
        HashMap<TreeSet, Integer> threes = new HashMap<>();
        HashMap<TreeSet, Integer> fours = new HashMap<>();
        HashMap<TreeSet, Integer> fives = new HashMap<>();
        for(int i = 0; i < N; i++){
            for(int c = 0; c < 5; c++){
                if(ones.keySet().contains(flavors[i][c]))ones.replace(flavors[i][c], ones.get(flavors[i][c])+1);
                else ones.put(flavors[i][c], 1);
            }
        }
        for(int i = 0; i < N; i++){
            for(int c = 0; c < 5; c++){
                for(int z = c+1; z < 5; z++){
                    TreeSet<Integer> set = new TreeSet<>();
                    set.add(flavors[i][c]);
                    set.add(flavors[i][z]);
                    if(twos.keySet().contains(set))twos.replace(set, twos.get(set)+1);
                    else twos.put(set, 1);
                }
            }
        }
        for(int i = 0; i < N; i++){
            for(int c = 0; c < 5; c++){
                for(int z = c+1; z < 5; z++){
                    for(int d = z+1; d < 5; d++){
                        TreeSet<Integer> set = new TreeSet<>();
                        set.add(flavors[i][c]);
                        set.add(flavors[i][z]);
                        set.add(flavors[i][d]);
                        if(threes.keySet().contains(set))threes.replace(set, threes.get(set)+1);
                        else threes.put(set, 1);
                    }
                }
            }
        }
        for(int i = 0; i < N; i++){
            for(int c = 0; c < 5; c++){
                for(int z = c+1; z < 5; z++){
                    for(int d = z+1; d < 5; d++){
                        for(int e = d+1; e < 5; e++){
                            TreeSet<Integer> set = new TreeSet<>();
                            set.add(flavors[i][c]);
                            set.add(flavors[i][z]);
                            set.add(flavors[i][d]);
                            set.add(flavors[i][e]);
                            if(fours.keySet().contains(set))fours.replace(set, fours.get(set)+1);
                            else fours.put(set, 1);
                        }
                    }
                }
            }
        }
        for(int i = 0; i < N; i++){
            TreeSet<Integer> set = new TreeSet<>();
            for(int c = 0; c < 5; c++){
                set.add(flavors[i][c]);
            }
            if(fives.keySet().contains(set))fives.replace(set, fives.get(set)+1);
            else fives.put(set, 1);
        }
        long subtract = 0;
        for(int i:ones.keySet()){
            long num = ones.get(i);
            long sum = (num*(num-1))/2;
            subtract+=sum;
        }
        for(TreeSet<Integer> i:twos.keySet()){
            long num = twos.get(i);
            long sum = (num*(num-1))/2;
            subtract-=sum;
        }
        for(TreeSet<Integer> i:threes.keySet()){
            long num = threes.get(i);
            long sum = (num*(num-1))/2;
            subtract+=sum;
        }
        for(TreeSet<Integer> i:fours.keySet()){
            long num = fours.get(i);
            long sum = (num*(num-1))/2;
            subtract-=sum;
        }
        for(TreeSet<Integer> i:fives.keySet()){
            long num = fives.get(i);
            long sum = (num*(num-1))/2;
            subtract+=sum;
        }
        answer-=subtract;
        pw.println(answer);
        pw.close();
    }
}
