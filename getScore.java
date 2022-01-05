import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class getScore
{
    public static void main (String [] args)
    {
        Scanner scan = new Scanner(System.in);
        boolean one = true;

        HashMap<String, Integer> table = new HashMap<>();

        while(one){
            System.out.print("# Correct: ");
            int correct = scan.nextInt();
            System.out.print("# Blank: ");
            int blank = scan.nextInt();
            scan.nextLine();
            System.out.print("Name: ");
            String name = scan.nextLine();
            System.out.println("");
            int score = (6 * correct) + (blank);
            System.out.print("Score: " + score);
            System.out.println("");
            table.put(name, score);
            System.out.print("Again?");
            one = scan.nextBoolean();
        }
        Map<String, Integer> sorted = table.entrySet().stream().sorted(comparingByValue()).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
        //before sorting
        System.out.println(table);
        //after sorting
        System.out.println(sorted);
    }
}