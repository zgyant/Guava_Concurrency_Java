import java.util.concurrent.ThreadLocalRandom;
public class ThreadFactory extends Thread {
    CalcProcess cal;
    ThreadFactory(CalcProcess ca){
        cal=ca;
    }
    public void run(){
        //This object generates random number; its better than Thread interms of concurency
        ThreadLocalRandom random=ThreadLocalRandom.current();
          while(true)
          {
                int i = random.nextInt(1, 10);
                try{
                    cal.addToList(i);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
    }

}