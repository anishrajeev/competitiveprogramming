import java.io.*;
import java.util.*;
//Thinking starts: 1:38
//Coding starts at: 1:41
public class geteven {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader(("geteven.in")));
        PrintWriter pw = new PrintWriter("geteven.out");
        int[][] arr = new int[84][2];
        int ans = 0;
        for(int i = 0; i < 84; i++){
            Arrays.fill(arr[i], 0);
        }
        int N = Integer.parseInt(bf.readLine());
        for(int i = 0; i < N; i++){
            StringTokenizer stk = new StringTokenizer(bf.readLine());
            char c = stk.nextToken().charAt(0);
            int num = Integer.parseInt(stk.nextToken())%2;
            num = (num+2)%2;
            arr[c][num]++;
        }
        for(int B = 0; B < 2; B++){
            for(int E = 0; E < 2; E++){
                for(int S = 0; S < 2; S++){
                    for(int I = 0; I < 2; I++){
                        for(int G = 0; G < 2; G++){
                            for(int O = 0; O < 2; O++){
                                for(int M = 0; M < 2; M++){
                                    if((B+E+S+S+I+E)*(G+O+E+S)*(M+O+O)%2 == 0)
                                        ans+=(arr['B'][B]*arr['E'][E]*arr['S'][S]*arr['I'][I]*arr['G'][G]*arr['O'][O]*arr['M'][M]);
                                }
                            }
                        }
                    }
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
