import java.util.*;
public class codequest2 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        System.out.println();
        for(int i = 0; i < N; i++){
            int A = scanner.nextInt();
            ArrayList<String> words = new ArrayList<>();
            for(int c = 0; c < A; c++){
                words.add(scanner.next().toLowerCase());
            }
            ArrayList<Integer> wrongs = new ArrayList<>();
            for(int c = 0; c < words.size(); c++){
                String word = words.get(c);
                int counter = word.length()-1;
                for(int z = 0; z < word.length(); z++){
                    if(!(word.charAt(z) == word.charAt(counter))){
                        wrongs.add(c+1);
                        break;
                    }
                    counter--;
                }
            }
            if(wrongs.size() == 0)System.out.println("True");
            else{
                String s = ("False - ");
                for(int c = 0; c<wrongs.size()-1; c++){
                    s = s + wrongs.get(c) + ", ";
                }
                s+=wrongs.get(wrongs.size()-1);
                System.out.println(s);
            }
        }
    }
}
