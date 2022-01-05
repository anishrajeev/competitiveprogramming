import java.util.*;
import java.io.*;
public class cowcode {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("cowcode.in"));
        PrintWriter pw = new PrintWriter("cowcode.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        String s = stk.nextToken();
        String[] arr = s.split("");
        long N = Long.parseLong(stk.nextToken());
        pw.println(search(s, N-1));
        pw.close();
    }
    public static char search(String s, long index){
        if(index < s.length())return s.charAt((int) index);
        long length = s.length();
        while(length*2<=index){
            length*=2;
        }
        if(index == length)return search(s, index-1);
        return search(s, index-length-1);
    }
}
