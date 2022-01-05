import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class hitthelottery {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
       int counter = 0;
       int amount = scanner.nextInt();
        for(int i = 0; amount > 0; i++){
            if(amount >= 100){
                counter++;
                amount -= 100;
            }
            else if(amount >= 20){
                counter++;
                amount -= 20;
            }
            else if(amount >= 10){
                counter++;
                amount -= 10;
            }
            else if(amount >= 5){
                counter++;
                amount -= 5;
            }
            else if(amount >= 1){
                counter++;
                amount -= 1;
            }
        }
        System.out.println(counter);
    }
}
