import java.util.*;
import java.io.*;

public class day6B{
    public static void main(String[] args) {
        List<char[]> grid = new ArrayList<char[]>();
        
        int[][] direction = {{0,-1},{1,0},{0,1},{-1,0}};
        int dir = 0;
        int posx,posy;
        
        int[] start = new int[2];
        int lnum = 0;

        
        try(BufferedReader br = new BufferedReader(new FileReader("day6sample.txt"))){
            String line;
            
            while((line=br.readLine())!=null){
                grid.add(line.toCharArray());
                if(line.indexOf("^")!=-1){
                    start[1]=line.indexOf("^");
                    start[0]=lnum;
                }
                lnum++;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        int rows = grid.size();
        int cols = grid.get(0).length;
        posy = start[0];
        posx = start[1];

        System.out.println("Starting position:"+posx+","+posy);
        int count=0;

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if (grid.get(i)[j] == '^' || grid.get(i)[j] == '#') {
                    continue;
                }
                posx = start[1];
                posy = start[0];
                List<int[]> visited = new ArrayList<>();
                List<char[]> testGrid = new ArrayList<>();

                for (char[] row : grid) {
                    testGrid.add(row.clone());
                }
                testGrid.get(i)[j] = '#';

                dir = 0;

                while(posy<rows && posx<cols && posy>=0 && posx>=0){
                    int[] currentState = {posx, posy, dir};

                    if(containsArray(visited, currentState)){
                        count++;
                        break;
                    }
                    visited.add(currentState);
                    
        
                    int newx = posx + direction[dir][0];
                    int newy = posy + direction[dir][1];

                    if (newx < 0 || newx >= cols || newy < 0 || newy >= rows || testGrid.get(newy)[newx] == '#') {
                        dir = (dir + 1) % 4; // Turn right
                    } else {
                        posx = newx;
                        posy = newy; // Move forward
                    }
                }
            }
        }
        System.out.println("Distinct positions = "+ count);
        
    }

    public static boolean containsArray(List<int[]> list, int[] target) {
        for (int[] array : list) {
            if (Arrays.equals(array, target)) {
                return true;
            }
        }
        return false;
    }

    public static void displayGrid(List<char[]> grid){
        System.out.println("\n\n");
        for(char[] i:grid){
            System.out.println(i);
        }
        System.out.println("\n\n");
    }
}
