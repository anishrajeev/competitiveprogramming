import java.util.*;
import java.io.*;
public class dishes {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("dishes.in"));
        PrintWriter pw = new PrintWriter("dishes.out");
        int N = Integer.parseInt(bf.readLine());
        int[] dishes = new int[N];
        for(int i = 0; i < N; i++) dishes[i] = Integer.parseInt(bf.readLine());
        int maxremoved = 0;
        int answer = 0;
        ArrayList<Stack<Integer>> soapy = new ArrayList<>();
        Stack<Integer> clean = new Stack<>();
        ArrayList<Integer> base = new ArrayList<>();
        soapy.add(new Stack<>());
        soapy.get(0).push(dishes[0]);
        base.add(dishes[0]);
        for(int i = 1; i < N; i++){
            int dish = dishes[i];
            if(dish<maxremoved) {
                answer = i;
                break;
            }
            int stack = next(base, dish);
            if(stack==-1){
                soapy.add(new Stack<>());
                soapy.get(soapy.size()-1).push(dish);
                base.add(dish);
            }
            else if(soapy.get(stack).peek()<dish){
                for(int c = 0; c < stack; c++){
                    ArrayList<Integer> temparr = new ArrayList<>();
                    temparr.addAll(soapy.get(0));
                    Collections.reverse(temparr);
                    clean.addAll(temparr);
                    Collections.sort(temparr);
                    maxremoved = Math.max(maxremoved, temparr.get(temparr.size()-1));
                    soapy.remove(0);
                    base.remove(0);
                }
                while(!soapy.get(0).isEmpty()&&soapy.get(0).peek()<dish){
                    clean.push(soapy.get(0).pop());
                    maxremoved = Math.max(maxremoved, clean.peek());
                }
                soapy.get(0).push(dish);
            }
            else{
                soapy.get(stack).push(dish);
            }
        }
        if(answer==0)answer = N;
        pw.println(answer);
        pw.close();
    }
    private static int next(ArrayList<Integer> arr, int target)
    {
        int start = 0, end = arr.size()- 1;

        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr.get(mid) <= target) {
                start = mid + 1;
            }
            else {
                ans = mid;
                end = mid - 1;
            }
        }
        return ans;
    }
}
