import java.io.*;
import java.util.*;

public class day11A {
    public static void main(String[] args) {
        List<Long> nums = new ArrayList<>();

        String s;
        try(BufferedReader br = new BufferedReader(new FileReader("day11input.txt"))){
            s = br.readLine();
            for(String i: s.split(" ")){
                nums.add(Long.parseLong(i));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        long count = 0;
        while(count<75){
            long len = nums.size();
            for(int i=0;i<len;i++){
                Long val = nums.get(i);
                if(val==0){
                    nums.set(i, (long) 1);
                }
                else if((long) Math.log10(val) % 2 != 0){
                    Long center = ((long) Math.log10(val) + 1)/2;
                    Long divisor = (long) Math.pow(10, center);
                    Long num1 = val/divisor;
                    Long num2 = val%divisor;
                    nums.set(i, num1);
                    nums.add(num2);
                }
                else{
                    nums.set(i, val*2024);
                }
            }

 /*            for(Long i : nums)
                System.out.print(i+" ");
            System.out.println(); */
            count++;
        }

        System.out.println("Total numbers = "+nums.size());

    }    
}
