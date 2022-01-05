import java.util.*;
public class codequest {
    public static void main(String[] arg){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> speeds = new ArrayList<>();
        ArrayList<Boolean> bdays = new ArrayList<>();
        int N = scanner.nextInt();
        for(int i = 0; i < N; i++){
            speeds.add(scanner.nextInt());
            bdays.add(scanner.nextBoolean());
        }
        for(int i = 0; i < N; i++){
            int sixty = 60;
            int eighty = 80;
            int speed = speeds.get(i);
            boolean bday = bdays.get(i);
            if(bday){
                sixty+=5;
                eighty+=5;
            }
            if(speed<=sixty)System.out.println("no ticket");
            if(speed>sixty && speed<=eighty) System.out.println("small ticket");
            if(speed > eighty) System.out.println("big ticket");
        }
    }
}
