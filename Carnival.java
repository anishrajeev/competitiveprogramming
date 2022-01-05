import java.io.*;

public class Carnival {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int N = Integer.parseInt(bf.readLine());
        StringBuilder party = new StringBuilder(N + " ");
        for(int i = 1; i <= N; i++){
            party.append(i);
        }
        System.out.println(party);
        int unique = Integer.parseInt(bf.readLine());

    }
}
