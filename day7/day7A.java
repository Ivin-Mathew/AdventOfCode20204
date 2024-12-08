import java.util.*;
import java.io.*;

public class day7A  {
    public static void main(String[] args){
        List<List<Long>> input = new ArrayList<>();
        String line;
        long sum = 0;

        try(BufferedReader br = new BufferedReader(new FileReader("day7input.txt"))){
            while((line=br.readLine())!=null){
                List<Long> numbers = new ArrayList<Long>();
                String[] n1 = line.split(": ");
                numbers.add(Long.parseLong(n1[0]));

                String[] n2 = n1[1].split(" ");
                for(String i:n2){
                    numbers.add(Long.parseLong(i));
                }
                input.add(numbers);
            }
        }
        catch(IOException  e){
            e.printStackTrace();
        }

        for(List<Long> l1 : input){
            sum = sum + check(l1);
        }

        System.out.println("Total sum = "+sum);
    }

    public static long check(List<Long> nums){
        long total = nums.get(0);
        long count = 0;
        long max = (long) Math.pow(2,nums.size()-2);

        while(count < max){
            long val = nums.get(1);

            char[] sign = String.format("%"+(nums.size()-2)+"s",Long.toBinaryString(count)).replace(' ','0').toCharArray();
            
            for(int i=2;i<nums.size();i++){
                if(sign[i-2]=='0')
                    val+=nums.get(i);
                else
                    val*=nums.get(i);
            }
            if(total == val){
                return total;
            }
            count++;
        }
        return 0;
    }

    


}
