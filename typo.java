import java.io.*;
public class typo {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("typo.in"));
        PrintWriter pw = new PrintWriter("typo.out");
        String str = bf.readLine();
        String[] s = str.split("");
        int ans = 0;
        int depth = 0;
        int open = 0;
        int close = 0;
        for(int i = 0; i < s.length; i++){
            if(s[i].equals("(")){
                depth++;
                open++;
            }
            if(s[i].equals(")")){
                close++;
                depth--;
            }
            if(depth==-1){
                ans=close;
                break;
            }
            if(depth<=1)open=0;
        }
        if(depth > 0)ans = open;
        pw.println(ans);
        pw.close();
    }
}
