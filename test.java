import java.io.*;
import java.util.*;
public class test {
    public static void main(String[] args){
        int n = 5;
        int S = 15;
        int[] a = {1, 2, 3, 4, 4};
        boolean happened = false;
        for(int subset = 0; subset < 1<<n; subset++){
            int possiblesum = 0;
            for(int i = 0; i < n; i++){
                if((subset&(1<<i))!=0){
                    possiblesum+=a[i];
                }
            }
            if(possiblesum==S){
                System.out.println("YES");
                happened = true;
                break;
            }
        }
        if(!happened) System.out.println("NO");
    }
}