import java.io.*;
import java.util.*;

public class day5B {
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
            if(!check(update,orderRules)){
                sum += correction(update, orderRules);
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

    public static int correction(List<Integer> update, List<int[]> rules){
        Map<Integer, List<Integer>> graph =  new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        List<Integer> sortedUpdate = new ArrayList<>();

        for(int page: update){
            graph.put(page, new ArrayList<>());
            inDegree.put(page,0);
        }

        for(int[] rule:rules){
            int a = rule[0];
            int b = rule[1];

            if(update.contains(a) && update.contains(b)){
                graph.get(a).add(b);
                inDegree.put(b, inDegree.get(b)+1);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int page : inDegree.keySet()){
            if(inDegree.get(page)==0)
                q.add(page);
        }

        while(!q.isEmpty()){
            int front = q.poll();
            sortedUpdate.add(front);

            for(int neighbor : graph.get(front)){
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    q.add(neighbor);
                }
            }
        }

        for(int page:update){
            if(!sortedUpdate.contains(page))
                sortedUpdate.add(page);
        }

        return sortedUpdate.get(sortedUpdate.size()/2);
        
    }
}