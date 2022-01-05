import java.util.*;
public class codequest3 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int lineamount = scanner.nextInt();
        for(int i = 0; i < lineamount; i++){
            ArrayList<Double> arr = new ArrayList<>();
            arr.add(scanner.nextDouble());
            arr.add(scanner.nextDouble());
            arr.add(scanner.nextDouble());
            double x = arr.get(0)-180;
            double y = arr.get(1)-180;
            double z = arr.get(2)-180;
            if(x < 0) x = 360 + x;
            if(y < 0) y = 360 + y;
            if(z < 0) z = 360+z;
            arr.set(0, Math.round(x*100.0)/100.0);
            arr.set(1, Math.round(y*100.0)/100.0);
            arr.set(2, Math.round(z*100.0)/100.0);
            for(int c = 0; c < 3; c++){
                System.out.print(arr.get(c) + " ");
            }
            System.out.println();
        }
    }
}
