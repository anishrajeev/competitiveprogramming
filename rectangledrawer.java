import java.util.*;
public class rectangledrawer {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for(int i = 0; i < N; i++){
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            System.out.println("x = " + x1 + " \\left\\{" + y1 + " <= y <= " + y2 + "\\right\\}");
            System.out.println("x = " + x2 + " \\left\\{" + y1 + " <= y <= " + y2 + "\\right\\}");
            System.out.println("y = " + y1 + " \\left\\{" + x1 + " <= x <= " + x2 + "\\right\\}");
            System.out.println("y = " + y2 + " \\left\\{" + x1 + " <= x <= " + x2 + "\\right\\}");
        }
    }
}
