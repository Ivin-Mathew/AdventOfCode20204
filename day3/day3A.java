import java.io.*;
import java.util.regex.*;


public class day3A{
    public static void main(String[] args) {
        int sum = 0;
        File input = new File("./day3input.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(input))){
            String line;
            Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

            while((line = br.readLine()) !=null){
                Matcher matcher = pattern.matcher(line);

                while(matcher.find()){
                    int n1 = Integer.parseInt(matcher.group(1));
                    int n2 = Integer.parseInt(matcher.group(2));

                    sum+=n1*n2;
                }
            }
            System.out.println("Sum = "+sum);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}