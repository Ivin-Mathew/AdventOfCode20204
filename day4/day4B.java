import java.io.*;
import java.util.*;


public class day4B {
    public static void main(String[] args) {
        List<char[]> matrix = new ArrayList<char[]>();
        
        try{
            BufferedReader br = new BufferedReader(new FileReader("day4input.txt"));
            String s;
            while((s=br.readLine())!=null){
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

        int count = 0;
        
        for(int row=0;row<rows-2;row++){
            for(int col=0;col<cols-2;col++){
                if(inputData[row][col]=='M' && inputData[row+2][col+2]=='S' && inputData[row+2][col]=='M' && inputData[row][col+2]=='S' && inputData[row+1][col+1]=='A')
                    count++;
                else if(inputData[row][col]=='M' && inputData[row+2][col+2]=='S' && inputData[row+2][col]=='S' && inputData[row][col+2]=='M' && inputData[row+1][col+1]=='A')
                    count++;
                else if(inputData[row][col]=='S' && inputData[row+2][col+2]=='M' && inputData[row+2][col]=='M' && inputData[row][col+2]=='S' && inputData[row+1][col+1]=='A')
                    count++;
                else if(inputData[row][col]=='S' && inputData[row+2][col+2]=='M' && inputData[row+2][col]=='S' && inputData[row][col+2]=='M' && inputData[row+1][col+1]=='A')
                    count++;

            }
        }

        System.out.println("Count = "+count);
    }
}
