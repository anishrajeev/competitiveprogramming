import java.util.*;
import java.io.*;
public class auto {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("auto.in"));
        PrintWriter pw = new PrintWriter("auto.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int W = Integer.parseInt(stk.nextToken());
        int N = Integer.parseInt(stk.nextToken());
        ArrayList<String> Dictionary = new ArrayList<>();
        ArrayList<String> Dictionary2 = new ArrayList<>();
        ArrayList<String> answers = new ArrayList<>();
        for(int i = 0; i < W; i++)Dictionary.add(bf.readLine());
        Dictionary2.addAll(Dictionary);
        Collections.sort(Dictionary);
        for(int i = 0; i < N; i++){
            stk = new StringTokenizer(bf.readLine());
            int K = Integer.parseInt(stk.nextToken())-1;
            String str = stk.nextToken();
            int num = binarysearch(str, Dictionary);
            num = num+K;
            //System.out.println(i);
            if(num>=W||Dictionary.get(num).length()<str.length()||!(Dictionary.get(num).substring(0, str.length()).equals(str)))answers.add("NOTPOSSIBLE");
            else answers.add(Dictionary.get(num));
        }
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < Dictionary2.size(); i++)map.put(Dictionary2.get(i), i+1);
        for(int i = 0; i < answers.size(); i++){
            if(answers.get(i).equals("NOTPOSSIBLE"))pw.println(-1);
            else pw.println(map.get(answers.get(i)));
        }
        pw.close();
    }
    public static int binarysearch(String target, ArrayList<String> Dictionary){
        int start = 0, end = Dictionary.size();
        while(start!=end){
            int mid = (start+end)/2;
            String midstr = Dictionary.get(mid);
            if(midstr.length()<target.length()){
                if(target.compareTo(midstr)>0)start = mid+1;
                else end = mid;
            }
            else{
                String midstrsub = midstr.substring(0, target.length());
                if(target.compareTo(midstrsub)>0)start = mid+1;
                else end = mid;
            }
        }
        return start;
    }
}
