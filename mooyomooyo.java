import java.util.*;
import java.io.*;
public class mooyomooyo {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("mooyomooyo.in"));
        PrintWriter pw = new PrintWriter("mooyomooyo.out");
        StringTokenizer stk = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int K = Integer.parseInt(stk.nextToken());
        int[][] arr = new int[N][10];
        int[][] answer = new int[N][10];
        for(int i = 0; i < N; i++){
            String s = bf.readLine();
            String[] strarray = s.split("");
            for(int c = 0; c < 10; c++){
                arr[i][c]=Integer.parseInt(strarray[c]);
                answer[i][c]=arr[i][c];
            }
        }
        while(arr[0][0]!=-1){
            for(int i = 0; i < N; i++)answer[i]=arr[i].clone();
            arr = explode(arr, K, N);
        }
        for(int[] a: answer){
            for(int i:a){
                pw.print(i);
            }
            pw.println();
        }
        pw.close();
    }
    public static int[][] explode(int arr[][], int K, int N){
        int[][] answer = new int[N][10];
        boolean[][] visited = new boolean[N][10];
        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};
        ArrayList<Point> ogpoints = new ArrayList<>();
        for(int i = 0; i < N; i++){
            answer[i]=arr[i].clone();
            Arrays.fill(visited[i], false);
        }
        //FINDING SECTIONS >= K
        for(int i = 0; i < N; i++){
            for(int c = 0; c < 10; c++){
                if(visited[i][c] || arr[i][c]==0)continue;
                visited[i][c]=true;
                int counter = 0;
                int color = arr[i][c];
                Point og = new Point(i, c);
                Stack<Point> s = new Stack<>();
                s.push(new Point(i, c));
                while(!s.isEmpty()){
                    Point p = s.pop();
                    counter++;
                    for(int d = 0; d < dx.length; d++){
                        int nx = dx[d]+p.x;
                        int ny = dy[d]+p.y;
                        if(nx>=0&&nx<N&&ny>=0&&ny<10&&arr[nx][ny]==color&&!visited[nx][ny]){
                            s.push(new Point(nx, ny));
                            visited[nx][ny]=true;
                        }
                    }
                }
                if(counter>=K)ogpoints.add(og);
            }
        }
        //DELETING SECTIONS
        if(ogpoints.size()==0){
            answer[0][0]=-1;
            return answer;
        }
        int size = ogpoints.size();
        for(int i = 0; i < size; i++){
            for(int c = 0; c < N; c++)Arrays.fill(visited[c], false);
            Point p = ogpoints.remove(0);
            int color = arr[p.x][p.y];
            answer[p.x][p.y]=0;
            Stack<Point> s = new Stack<>();
            for(int d = 0; d < dx.length; d++){
                int nx = dx[d]+p.x;
                int ny = dy[d]+p.y;
                if(nx>=0&&nx<N&&ny>=0&&ny<10&&arr[nx][ny]==color){
                    s.push(new Point(nx, ny));
                    answer[nx][ny]=0;
                    visited[nx][ny]=true;
                }
            }
            while(!s.isEmpty()){
                Point point = s.pop();
                for(int d = 0; d < dx.length; d++){
                    int nx = dx[d]+point.x;
                    int ny = dy[d]+point.y;
                    if(nx>=0&&nx<N&&ny>=0&&ny<10&&arr[nx][ny]==color&&!visited[nx][ny]){
                        s.push(new Point(nx, ny));
                        answer[nx][ny]=0;
                        visited[nx][ny]=true;
                    }
                }
            }
        }
        //GRAVITY
        for(int i = 0; i < 10; i++){
            for(int c = N-1; c > 0; c--){
                if(answer[c][i]==0&&answer[c-1][i]!=0){
                    answer[c][i]=answer[c-1][i];
                    answer[c-1][i]=0;
                    c = N;
                }
            }
        }
        return answer;
    }
    public static class Point{
        int x, y;
        public Point(int a, int b){
            x=a;
            y=b;
        }
    }
}
