import java.io.*;
import java.util.*;
public class rental {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("rental.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int R = Integer.parseInt(stk.nextToken());
        long[] cows = new long[N];
        Shop[] shops = new Shop[M];
        long[] rent = new long[R];
        long[] rentpsum = new long[R+1];
        long[] totalmilkpsum = new long[N+1];
        long totalshopquota = 0;
        long answer = 0;
        for(int i = 0; i < N; i++){
            cows[i] = Long.parseLong(bf.readLine());
        }
        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(bf.readLine());
            shops[i] = new Shop(Long.parseLong(stk.nextToken()), Long.parseLong(stk.nextToken()));
            totalshopquota+=shops[i].quantity;
        }
        for(int i = 0; i < R; i++){
            rent[i] = Long.parseLong(bf.readLine());
        }
        Arrays.sort(cows);
        Arrays.sort(shops);
        Arrays.sort(rent);
        for(int i = 0; i < rent.length/2; i++){
            long temp = rent[i];
            rent[i] = rent[rent.length -i -1];
            rent[rent.length -i -1] = temp;
        }
        rentpsum[0] = 0;
        for(int i = 1; i <= R; i++){
            rentpsum[i]=rentpsum[i-1]+rent[i-1];
        }
        totalmilkpsum[0] = 0;
        for(int i = 1; i <= N; i++){
            totalmilkpsum[i]=totalmilkpsum[i-1]+cows[N-i];
        }
        //CALCULATING ARRAY FOR PRICES COMPARED TO MILK SOLD
        long[] prices = new long[(int) (totalshopquota+1)];
        prices[0]=0;
        long totalmoney = 0;
        int currshop = 0;
        long currtotal = shops[0].quantity;
        for(int i = 1; i <= totalshopquota; i++){
            if(currtotal<i){
                currshop++;
                currtotal+=shops[currshop].quantity;
            }
            totalmoney += shops[currshop].price;
            prices[i]=totalmoney;
        }
        //System.out.println(Arrays.toString(prices));
        //System.out.println(totalshopquota);
        for(int i = 0; i <= N; i++){
            int gi = N-i;
            int cowstorent = Math.min(gi, R);
            long gitotal = rentpsum[cowstorent];
            long totalmilk = totalmilkpsum[i];
            totalmilk = Math.min(totalmilk, totalshopquota);
            long fitotal = prices[(int) totalmilk];
            long money = gitotal+fitotal;
            answer = Math.max(money, answer);
        }
        pw.println(answer);
        pw.close();
    }
    static class Shop implements Comparable<Shop> {
        public long quantity, price;
        public Shop(long a, long b) {
            quantity=a;
            price=b;
        }
        public int compareTo(Shop s) {
            return Long.compare(s.price, price);
        }
        @Override
        public String toString() {
            return "Q: " + quantity + " P: " + price;
        }
    }

}