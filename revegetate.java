import java.util.*;
import java.io.*;
public class revegetate {
    static int M;
    static int N;
    static boolean[] visited;
    static ArrayList<Integer>[] fields;
    static ArrayList<Boolean>[] diff;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("revegetate.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        visited = new boolean[N];
        fields = new ArrayList[N];
        diff = new ArrayList[N];
        int count = 0;
        for(int i = 0; i < N; i++){
            fields[i] = new ArrayList<Integer>();
            diff[i] = new ArrayList<Boolean>();
        }
        for(int i = 0; i < M; i++){
            StringTokenizer stk2 = new StringTokenizer(bf.readLine());
            String s = stk2.nextToken();
            int f1 = Integer.parseInt(stk2.nextToken())-1;
            int f2 = Integer.parseInt(stk2.nextToken())-1;
            fields[f1].add(f2);
            fields[f2].add(f1);
            if(s.equals("S")){
                diff[f1].add(true);
                diff[f2].add(true);
            }
            else{
                diff[f1].add(false);
                diff[f2].add(false);
            }
        }
        for(int i = 0; i < fields.length; i++){
            if(!visited[i]){
                recurse(i);
                count++;
            }
        }
        pw.print(1);
        for(int i = 0; i < count; i++){
            pw.print(0);
        }
        pw.close();
    }
    public static void recurse(int node){
        visited[node] = true;
        for(int i = 0; i < fields[node].size(); i++){
            if(!visited[fields[node].get(i)] && fields[node].get(i)==0)
                recurse(fields[node].get(i));
        }
    }
}