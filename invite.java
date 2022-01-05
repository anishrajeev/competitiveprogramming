import java.util.*;
import java.io.*;
public class invite {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("invite.in"));
        PrintWriter pw = new PrintWriter("invite.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int G = Integer.parseInt(stk.nextToken());
        TreeSet<Integer>[] groups = new TreeSet[G];
        ArrayList<Integer>[] cows = new ArrayList[N];
        for(int i = 0; i < G; i++){
            groups[i] = new TreeSet<>();
        }
        for(int i = 0; i < N; i++){
            cows[i] = new ArrayList<>();
        }
        for(int i = 0; i < G; i++){
            stk = new StringTokenizer(bf.readLine());
            int size = Integer.parseInt(stk.nextToken());
            for(int c = 0; c < size; c++){
                int num = Integer.parseInt(stk.nextToken())-1;
                groups[i].add(num);
                cows[num].add(i);
            }
        }
        TreeSet<Integer> answers = new TreeSet<>();
        LinkedList<Integer> q = new LinkedList<>();
        q.add(0);
        TreeSet<Integer> visited = new TreeSet<>();
        answers.add(0);
        while(!q.isEmpty()){
            int cow = q.poll();
            answers.add(cow);
            if(visited.contains(cow))continue;
            visited.add(cow);
            for(int i:cows[cow]){
                TreeSet<Integer> copy = groups[i];
                copy.remove(cow);
                groups[i] = copy;
                if(copy.size()==1){
                    int newcow = copy.pollFirst();
                    q.add(newcow);
                }
            }
        }
        pw.println(answers.size());
        pw.close();
    }
}
