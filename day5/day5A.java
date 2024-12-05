import java.io.*;
import java.util.*;

public class day5A {
    public static void main(String[] args) {
        ArrayList<String> rules = new ArrayList<String>();
        ArrayList<String> lines = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new FileReader("day5input.txt"))){
            String line;
            boolean readingRules = true;
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    readingRules = false;
                    continue;
                }
                if (readingRules) {
                    rules.add(line);
                } else {
                    lines.add(line);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }   

        List<int[]> orderRules = new ArrayList<int[]>(); // rules
        for(String rule: rules){
            String[] parts = rule.split("\\|");
            orderRules.add(new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])});
        }

        List<List<Integer>> updates = new ArrayList<List<Integer>>(); // updates
        for(String line:lines){
            String[] parts = line.split(",");
            List<Integer> partsNum = new ArrayList<Integer>();
            for(String i:parts){
                partsNum.add(Integer.parseInt(i));
            }
            updates.add(partsNum);
        }

        int sum = 0;
        for(List<Integer> update:updates){
            if(check(update,orderRules)){
                int size = update.size();
                sum += update.get(size/2);
            }
        }

        System.out.println("Sum = "+sum);


    }

    public static boolean check(List<Integer> update, List<int[]> rules){
        for(int[] rule:rules){
            int index1 = update.indexOf(rule[0]);
            int index2 = update.indexOf(rule[1]);

            if(index1==-1 || index2 == -1)
                continue;
            else if(index1 > index2 && index1!=-1 && index2 != -1){
                return false;
            }
        }
        return true;
    }
}
