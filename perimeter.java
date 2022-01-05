import java.util.*;
import java.io.*;

public class perimeter {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("perimeter.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
        int N = Integer.parseInt(bf.readLine());
        char[][] arr = new char[N][N];
        for(int i = 0; i < N; i++){
            String line = bf.readLine();
            for(int c = 0; c < N; c++){
                arr[i][c] = line.charAt(c);
            }
        }
        int[] dx = new int[]{0, 0, -1, 1};
        int[] dy = new int[]{1, -1, 0, 0};
        ArrayList<Integer> area = new ArrayList<>();
        ArrayList<Integer> perimeter = new ArrayList<>();
        ArrayList<int[]> pair = new ArrayList<>();
        for(int i = 0; i < N; i++){
            for(int c = 0; c < N; c++){
                if(arr[i][c] == '.') continue;
                Stack<Point> graph = new Stack<>();
                graph.push(new Point(i, c));
                int count = 1;
                int perimetercount = 0;
                boolean[][] visited = new boolean[N][N];
                while(graph.size() != 0){
                    Point p = graph.pop();
                    int x = p.getX();
                    int y = p.getY();
                    arr[x][y] = '.';
                    for(int z = 0; z < dx.length; z++){
                        if(x+dx[z] >= 0 && x+dx[z]< N && y+dy[z] >= 0 && y+dy[z] < N && arr[x+dx[z]][y+dy[z]] == '#'){
                            graph.push(new Point(x+dx[z], y+dy[z]));
                            count++;
                            arr[x+dx[z]][y+dy[z]] = '.';
                            visited[x+dx[z]][y+dy[z]] = true;
                        }
                        else{
                            if(x+dx[z] < 0 || y+dy[z] < 0 || x+dx[z] >= N || y+dy[z] >= N)
                            perimetercount++;
                            else if(!visited[x+dx[z]][y+dy[z]])
                                perimetercount++;
                        }
                        visited[x][y] = true;
                    }
                }
                area.add(count);
                perimeter.add(perimetercount);
                pair.add(new int[]{count, perimetercount});
            }
        }
        if(area.size() > 1){
            Collections.sort(area);
            int finalarea = area.get(area.size()-1);
            int min = Integer.MAX_VALUE;
            for(int i = 0; i < pair.size(); i++){
                if(pair.get(i)[0] == finalarea) min = Math.min(min, pair.get(i)[1]);
            }
            pw.println(finalarea + " " + min);
            pw.close();
        }
        pw.println(area.get(0) + " " + perimeter.get(0));
        pw.close();
    }
    public static class Point{
        private int x;
        private int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getY(){return y;}
        public int getX(){return x;}
    }
}
