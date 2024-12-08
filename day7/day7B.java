import java.util.*;
import java.io.*;

public class day7B  {
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
        long max = (long) Math.pow(3,nums.size()-2);

        while(count < max){
            long val = nums.get(1);
            String signs = toBase3String(count);
            char[] sign = String.format("%"+(nums.size()-2)+"s",signs).replace(' ','0').toCharArray();
            
            for(int i=2;i<nums.size();i++){
                if(sign[i-2]=='0')
                    val+=nums.get(i);
                else if(sign[i-2]=='1')
                    val*=nums.get(i);
                else{
                    char[] len = Long.toString((nums.get(i))).toCharArray();
                    val = val*((int) Math.pow(10,len.length)) + nums.get(i);
                }
            }
            if(total == val){
                return total;
            }
            count++;
        }
        return 0;
    }

    public static String toBase3String(long number) {
        if (number == 0) {
            return "0";
        }

        StringBuilder base3 = new StringBuilder();
        while (number > 0) {
            base3.append(number % 3);
            number /= 3;
        }

        return base3.reverse().toString();
    }

}
