import java.util.*;
public class Main{
    public static void main(String []args){
        new Main().userEntry();
    }
    //function that asks user for the input...
    public  void  userEntry(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number of threads you want to generate: ");
        int inputValue=sc.nextInt();
        CalcProcess amc=new CalcProcess();
        //loop to generate the number of threads based on users input
        for(int i=0;i<inputValue;i++){
            ThreadFactory tf=new ThreadFactory(amc);
            tf.start(); //starts the newly created thread
        }
    }

}

