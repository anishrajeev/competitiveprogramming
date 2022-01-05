import java.util.*;
import java.io.*;
public class clumsy{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("clumsy.in"));
        PrintWriter pw = new PrintWriter("clumsy.out");
        String str = bf.readLine();
        int depth = 0;
        int answer = 0;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c=='(')depth++;
            if(c==')')depth--;
            if(depth<0){
                depth+=2;
                answer++;
            }
        }
        if(depth > 0){
            answer+=depth/2;
        }
        pw.println(answer);
        pw.close();
    }
}
