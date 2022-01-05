import java.util.*;
import java.io.*;
public class where {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new FileReader("where.in"));
        PrintWriter pw = new PrintWriter("where.out");
        int N = Integer.parseInt(bf.readLine());
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        char[][] arr = new char[N][N];
        ArrayList<Rectangle> PCL = new ArrayList<>();
        for(int i = 0; i < N; i++){
            String s = bf.readLine();
            for(int c = 0; c < N; c++){
                arr[i][c]=s.charAt(c);
            }
        }
        for(int x1 = 0; x1 < N; x1++){
            for(int y1 = 0; y1 < N; y1++){
                for(int x2 = x1; x2 < N; x2++){
                    for(int y2 = y1; y2 < N; y2++){
                        TreeSet<Character> containspieces = new TreeSet<>();
                        int leftx = x1;
                        int rightx = x2;
                        int lefty = y1;
                        int righty = y2;
                        for(int i = leftx; i <= rightx; i++){
                            for(int c = lefty; c <= righty; c++){
                                containspieces.add(arr[i][c]);
                            }
                        }
                        if(containspieces.size()!=2)continue;
                        boolean[][] visited = new boolean[N][N];
                        HashMap<Character, Integer> map = new HashMap<>();
                        int containspiecessize = containspieces.size();
                        for(int i = 0; i < containspiecessize; i++)map.put(containspieces.pollFirst(), 0);
                        for(int i = leftx; i <= rightx; i++){
                            for(int c = lefty; c <= righty; c++){
                                if(visited[i][c])continue;
                                visited[i][c]=true;
                                Stack<Point> s = new Stack<>();
                                s.push(new Point(i, c));
                                map.replace(arr[i][c], map.get(arr[i][c])+1);
                                while(!s.isEmpty()){
                                    Point p = s.pop();
                                    int x = p.x;
                                    int y = p.y;
                                    for(int d = 0; d < dx.length; d++){
                                        int newx = x+dx[d];
                                        int newy = y+dy[d];
                                        if(newx<=rightx&&newx>=leftx&&newy<=righty&&newy>=lefty&&!visited[newx][newy]&&arr[newx][newy]==arr[i][c]){
                                            visited[newx][newy]=true;
                                            s.push(new Point(newx, newy));
                                        }
                                    }
                                }
                            }
                        }
                        boolean one = false;
                        boolean two = false;
                        for(Character c:map.keySet()){
                            if(map.get(c)==1)one = true;
                            if(map.get(c)>1)two = true;
                        }
                        if(!one || !two)continue;
                        PCL.add(new Rectangle(leftx, lefty, rightx, righty));
                    }
                }
            }
        }
        TreeSet<Integer> toremove = new TreeSet<>();
        for(int i = 0; i < PCL.size(); i++){
            if(toremove.contains(i))continue;
            for(int c = 0; c < PCL.size(); c++){
                if(i==c)continue;
                //if(toremove.contains(i)||toremove.contains(c))continue;
                Rectangle ri = PCL.get(i);
                Rectangle rc = PCL.get(c);
                if(ri.x2>=rc.x2&&rc.x1>=ri.x1&&ri.y2>=rc.y2&&rc.y1>=ri.y1){
                    toremove.add(c);
                }
            }
        }
        pw.println(PCL.size()-toremove.size());
        pw.close();
    }
    public static class Point{
        int x, y;
        public Point(int a, int b){
            x = a;
            y = b;
        }
        @Override
        public String toString(){
            return x+":"+y;
        }
    }
    public static class Rectangle{
        int x1, y1, x2, y2;
        public Rectangle(int x1, int y1, int x2, int y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
        @Override
        public String toString(){
            return x1+","+y1+":"+x2+","+y2;
        }
    }
}
