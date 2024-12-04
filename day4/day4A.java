import java.io.*;
import java.util.*;

public class day4A{
    public static void main(String[] args) {
        List<char[]> matrix = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("day4input.txt"));
            String s;
            while((s = br.readLine())!=null){
                matrix.add(s.toCharArray());
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        char[][] inputData = new char[matrix.size()][];
        for(int i=0; i<matrix.size() ; i++){
            inputData[i] = matrix.get(i);
        }

        int rows = inputData.length;
        int cols = inputData[0].length;

        String word = "XMAS";
        int len = 4;
        int count =0;


        int[][] dir = {
            {0,1},{0,-1},
            {1,0},{-1,0},
            {1,1},{1,-1},
            {-1,1},{-1,-1}
        };


        for(int row = 0;row<rows;row++){
            for(int col =0;col<cols;col++){
                for(int[] direction : dir ){
                    int rowDir = direction[0];
                    int colDir = direction[1];
                    int k;
                    for(k=0;k<len;k++){
                        int newRow = row + k*rowDir;
                        int newCol = col + k*colDir;

                        if( newRow<0 || newRow>=rows || newCol<0 || newCol >= cols || inputData[newRow][newCol] != word.charAt(k)){
                            break;
                        }
                    }
                    if(k==len)
                        count++;
                }
            }
        }

        System.out.println("Count = "+count);
    }
}