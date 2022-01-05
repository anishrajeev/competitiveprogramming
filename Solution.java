import java.util.regex.Pattern;
import java.util.*;
public class Solution {
    public static void main(String[] args){
        System.out.println(5/2);
    }
    public static int hailstoneLength(int n){
        int counter = 1;
        while(n!=1){
            if(n%2==0)n = n/2;
            else n = 3*n+1;
            counter++;
        }
        return counter;
    }
}