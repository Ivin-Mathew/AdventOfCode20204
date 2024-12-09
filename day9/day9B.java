import java.io.*;
import java.util.ArrayList;

public class day9B {
    public static void main(String[] args) {
        StringBuilder memory = new StringBuilder();
        ArrayList<Integer[]> data = new ArrayList<>();
        ArrayList<Integer[]> free = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("day9input.txt"))){
            int c;
            int id = 0;
            int count = 0;
            int index = 0;
            while((c=br.read())!=-1){
                int len = c-48;
                if(id==46)
                    id++;

                if(count%2==0){
                    int start = index;
                    for(int i=0;i<len;i++){
                        memory.append((char)id);
                        index++;
                    }
                    Integer[] grp = new Integer[]{start,id,len};// index, id, length
                    data.add(grp);
                    id++;
                }
                else{
                    int start = index;
                    for(int i=0;i<len;i++){
                        memory.append('.');
                        index++;
                    }
                    Integer[] grp = new Integer[]{start,len,0};//index, length, available
                    free.add(grp);
                }
                count++;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        for(int i = data.size()-1;i>=0;i--){
            Integer[] dg = data.get(i);
            int index = dg[0];
            int idd = dg[1];
            int length = dg[2];

            for(Integer[] fg : free){
                Integer[] original = fg;
                if(fg[2]==0 && fg[1]>=length && fg[0]<index){
                    int findex = fg[0];
                    int j;

                    for(j=findex;j<(findex+length);j++){
                        memory.setCharAt(j, (char) idd);
                    }
                    fg[0]=j;

                    for(j=index;j<(index+length);j++){
                        memory.setCharAt(j,'.');
                    }
                    fg[1]-=length;

                    if(fg[1]==0)
                        fg[2]=1;
                    free.set(free.indexOf(original), fg);
                    break;
                }
            }
        }
        
        int p1 = 0; 
        long checksum = 0;
        while(p1<memory.length()){
            checksum+=(long) p1*((int) memory.charAt(p1) == 46 ? 0 : ((int) memory.charAt(p1) < 46 ? (int) memory.charAt(p1) : ((int) memory.charAt(p1) - 1)));
            p1++;
        }

        System.out.println("Checksum = "+checksum);
    }
}
