import java.io.File;
import java.util.Scanner;

class day2A{
    public static void main(String[] args) {
     try{
        File input = new File("./day2input.txt");
        Scanner sc = new Scanner(input);
        int safe = 0;


        while(sc.hasNextLine()){
            String data =sc.nextLine();
            String[] numChars = data.split(" ");
            int[] num = new int[numChars.length];
            

            //checking the conditions
            int temp=1;
            int asc = 1;
            for(int i = 0;i<numChars.length;i++){
                num[i] = Integer.parseInt(numChars[i]);
                int diff;
                if(i==1){
                    diff = num[i] - num[i-1];
                    if(diff>0) asc=1;
                    else asc=-1;
                    if(Math.abs(diff)<1 || Math.abs(diff)>3)
                        continue;
                    else
                        temp+=1;
                }
                else if(i>1){
                    diff = num[i] - num[i-1];
                    if((diff*asc)<1 || (diff*asc)>3)
                        break;
                    else
                        temp+=1;
                }
            }
            
            if(temp == numChars.length)
            safe+=1;
        }

        System.out.println("Safe sequences = "+safe);

        sc.close();
     }  
     catch(Exception e){
        System.out.println(e);
     } 
    }
}