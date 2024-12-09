import java.io.*;

public class day9A {
    public static void main(String[] args) {
        StringBuilder memory = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader("day9input.txt"))){
            int c;
            int id = 0;
            int count = 0;
            while((c=br.read())!=-1){
                int len = c-48;
                if(id==46)
                    id++;

                if(count%2==0){
                    for(int i=0;i<len;i++){
                        memory.append((char)id);
                    }
                    id++;
                }
                else{
                    for(int i=0;i<len;i++){
                        memory.append('.');
                    }
                }
                count++;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("String length = "+memory.toString().length());

        int p1 = 0;
        int p2 = memory.length()-1;
        

        while(p2>p1){
            if( memory.charAt(p1)=='.' && memory.charAt(p2)!='.'){
                memory.setCharAt(p1, memory.charAt(p2));
                memory.setCharAt(p2, '.');
                p1++;
                p2--;
            }
            else if(memory.charAt(p1)!='.'){
                p1++;
            }
            else if(memory.charAt(p2)=='.'){
                p2--;
            }
        }
        
        p1 = 0;
        long checksum = 0;
        while(p1<memory.length() && memory.charAt(p1)!='.'){
            checksum+=(long) p1*((int) memory.charAt(p1) > 46 ? (int) memory.charAt(p1) - 1 : (int) memory.charAt(p1));
            p1++;
        }
        System.out.println("Checksum = "+checksum);

    }
}
