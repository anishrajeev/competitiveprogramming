import java.io.*;
import java.util.HashMap;
public class utrka {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader bf = new BufferedReader(new FileReader("tester.in"));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());
        HashMap<String, Integer> names = new HashMap<>();
        for(int i = 0; i < N; i++){
            String name = bf.readLine();
            if(names.containsKey(name))names.put(name, names.get(name)+1);
            else names.put(name, 1);
        }
        for(int i = 0; i < N-1; i++){
            String name = bf.readLine();
            names.replace(name, names.get(name)-1);
        }
        for(String s:names.keySet()){
            if(names.get(s)!=0)System.out.println(s);
        }
    }
}
