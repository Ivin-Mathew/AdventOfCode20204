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

        List<int[]> locs = new ArrayList<int[]>();
        
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

        while(posy<rows && posx<cols){
            if(!containsArray(locs, new int[]{posx, posy})){
                locs.add(new int[]{posx,posy});
            }
            else
                count++;
            int newx = posx + direction[dir][0];
            int newy = posy + direction[dir][1];
            int stuck = 0;
            while ((newx >= 0 && newx < cols && newy >= 0 && newy < rows) && grid.get(newy)[newx] == '#') {
                dir = (dir + 1) % 4;
                stuck++;

                if(stuck==4)
                    break;

                newx = posx + direction[dir][0];
                newy = posy + direction[dir][1];
            }

            if (newx >= 0 && newx < cols && newy >= 0 && newy < rows && stuck<4) {
                posx = newx;
                posy = newy;
            }
            else
                break;
        }

        System.out.println("Distinct positions = "+count);
        
    }

    public static boolean containsArray(List<int[]> list, int[] target) {
        for (int[] array : list) {
            if (Arrays.equals(array, target)) {
                return true;
            }
        }
        return false;
    }
}
