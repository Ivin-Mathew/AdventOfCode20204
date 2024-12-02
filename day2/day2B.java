import java.io.File;
import java.util.Scanner;

public class day2B {
    public static void main(String[] args) {
        try{
        File input = new File("./day2input.txt");
        Scanner sc = new Scanner(input);
        int safe = 0;


        while(sc.hasNextLine()){
            String data = sc.nextLine();
            String numChars[] = data.split(" ");
            int len = numChars.length;
            int[] num = new int[len];
            for(int i=0;i<len;i++){
                num[i] = Integer.parseInt(numChars[i]);
            }

            
            for(int i=0;i<len;i++){
                int good = 1;
                int curr;
                int prev = -1;
                int asc = 0;
                for(int j=0;j<len;j++){
                    if(j==i)
                        continue;
                    else{
                        curr = num[j];
                        if(prev!=-1){
                            int diff = curr - prev;
                            if(asc==0){
                                if(diff>0) asc = 1;
                                else asc = -1;
                            }

                            if(diff*asc<1 || diff*asc>3){
                                good=0;
                                break;
                            }
                        }
                        prev = num[j];
                    }
                }
                if(good==1){
                    safe+=1;
                    break;
                }
            }
        }

        System.out.println("Safe sequences = "+safe);
        sc.close();
        }  
        catch(Exception e){
        System.out.println(e);
        } 
    }   
}
