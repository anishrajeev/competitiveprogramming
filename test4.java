import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class test4 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        double firstterm = 0.5*(1+Math.sqrt(5));
        firstterm = Math.pow(firstterm, Math.pow(10, 18));
        System.out.println(firstterm);
    }
    public static long exponent(int b, int e){
        if(e == 0) return 1;
        return (e%2 == 0) ?
                exponent(b, e/2)*exponent(b, e/2)
                :b*exponent(b,(e-1)/2)*exponent(b, (e-1)/2);
    }
}
