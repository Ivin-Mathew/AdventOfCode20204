import java.io.*;
import java.util.regex.*;


public class day3B{
    public static void main(String[] args) {
        int sum = 0;
        File input = new File("./day3input.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(input))){
            String line;
            boolean enabled = true;
            Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
            Pattern doing = Pattern.compile("do\\(\\)");
            Pattern dont = Pattern.compile("don't\\(\\)");

            while((line = br.readLine()) !=null){
                int i=0;
                while(i<line.length()){
                    String substring = line.substring(i);

                    Matcher dontMatch = dont.matcher(substring);
                    if(dontMatch.find() && dontMatch.start()==0){
                        enabled = false;
                        i+=dontMatch.end();
                        continue;
                    }

                    Matcher doMatch = doing.matcher(substring);
                    if(doMatch.find() && doMatch.start()==0){
                        enabled = true;
                        i+=doMatch.end();
                        continue;
                    }

                    Matcher mulMatcher = pattern.matcher(substring);
                    if(enabled && mulMatcher.find() && mulMatcher.start()==0){
                        int n1 = Integer.parseInt(mulMatcher.group(1));
                        int n2 = Integer.parseInt(mulMatcher.group(2));

                        sum+= n1*n2;
                        i+=mulMatcher.end();
                        continue;
                    }

                    i++;
                }
            }
            System.out.println("Sum = "+sum);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}