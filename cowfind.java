import java.util.*;
import java.io.*;
public class cowfind {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("cowfind.in"));
        PrintWriter pw = new PrintWriter("cowfind.out");
        String s = bf.readLine();
        String[] grass = s.split("");
        long answer = 0;
        int[] dp = new int[grass.length];
        for(int i = 1; i < grass.length; i++){
            if(grass[i].equals("(") && grass[i-1].equals("("))
                dp[i] = dp[i-1]+1;
            else dp[i] = dp[i-1];
        }
        for(int i = 0; i < grass.length-1; i++){
            if(grass[i].equals(")")&&grass[i+1].equals(")") && i != 0)
                answer+=dp[i-1];
        }
        pw.println(answer);
        pw.close();
    }
}
