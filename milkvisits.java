import java.util.*;
import java.io.*;
public class milkvisits {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("milkvisits.in"));
        PrintWriter pw = new PrintWriter("milkvisits.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        ArrayList<Integer>[] component = new ArrayList[N];
        ArrayList<Integer>[] farm = new ArrayList[N];
        TreeSet<Integer> set = new TreeSet<>();
        String[] cows = new String[N];
        int[] visited = new int[N];
        boolean[] visitednodes = new boolean[N];
        Arrays.fill(visitednodes, false);;
        ArrayList<Integer> answer = new ArrayList<>();
        for(int i = 0; i < N; i++){
            component[i]=new ArrayList<>();
            farm[i]=new ArrayList<>();
        }
        String string = bf.readLine();
        cows = string.split("");
        for(int i = 0; i < N-1; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            farm[a].add(b);
            farm[b].add(a);
        }
        int num = -1;
        String s;
        for(int i = 0; i < N; i++){
            if(visitednodes[i])continue;
            num++;
            visited[i]=num;
            s = cows[i];
            visitednodes[i]=true;
            Stack<Integer> stack = new Stack<>();
            for(int c = 0; c < farm[i].size(); c++){
                int number = farm[i].get(c);
                if(!visitednodes[number]&&cows[number].equals(s)){
                    stack.push(number);
                    visited[number]=num;
                }
            }
            while(!stack.isEmpty()){
                int popped = stack.pop();
                visitednodes[popped]=true;
                for(int c = 0; c < farm[popped].size(); c++){
                    int number = farm[popped].get(c);
                    if(!visitednodes[number]&&cows[number].equals(s)){
                        stack.push(number);
                        visited[number]=num;
                    }
                }
            }
        }
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken())-1;
            String cow = stk.nextToken();
            if(cows[a].equals(cow)||cows[b].equals(cow)||visited[a]!=visited[b])answer.add(1);
            else answer.add(0);
        }
        for(int i:answer){
            pw.print(i);
        }
        pw.close();
    }
}
