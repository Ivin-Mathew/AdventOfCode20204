import java.util.*;
import java.io.*;

public class day8A {
    public static void main(String[] args) {
        List<List<Character>> input = new ArrayList<>();
        Map<Character, List<Integer[]>> map = new HashMap<>();

        int rows = 0;
        int cols = 0;

        try(BufferedReader br = new BufferedReader(new FileReader("day8input.txt"))){
            String line;
            rows = 0;
            while((line=br.readLine())!=null){
                char[] temp = line.toCharArray();
                List<Character> points = new ArrayList<>();
                cols=0;

                for(char c : temp){
                    points.add(c);
                    if(c!='.'){
                        if(map.containsKey(c)){
                            map.get(c).add(new Integer[]{rows,cols});
                        }
                        else{
                            List<Integer[]> position = new ArrayList<>();
                            position.add(new Integer[]{rows,cols});
                            map.put(c, position);
                        }
                    }
                    cols++;
                }
                input.add(points);
                rows++;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        int count = 0;
        for(Character key : map.keySet()){
            List<Integer[]> points = map.get(key);
            List<List<Character>> antinodes = input;
            for(Integer[] p1 : points){
                for(Integer[] p2 : points){
                    if(p1==p2)
                        continue;
                    else{
                        Integer[] diff = new Integer[]{p1[0]-p2[0],p1[1]-p2[1]};
                        Integer[] new1 = new Integer[]{p1[0]+diff[0],p1[1]+diff[1]};
                        Integer[] new2 = new Integer[]{p2[0]-diff[0],p2[1]-diff[1]};

                        if(new1[0]<rows && new1[0]>=0 && new1[1]<cols && new1[1]>=0){
                            if(antinodes.get(new1[0]).get(new1[1])!='#'){
                                antinodes.get(new1[0]).set(new1[1],'#');
                                count++;
                            }
                        }

                        if(new2[0]<rows && new2[0]>=0 && new2[1]<cols && new2[1]>=0){
                            if(antinodes.get(new2[0]).get(new2[1])!='#'){
                                antinodes.get(new2[0]).set(new2[1],'#');
                                count++;
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Total count = "+count);
    }
}
