/**
 Basic 'Runnable' Design Pattern
 */
public class RunnableExample implements Runnable{
    public static void main(String[] args){
        Runnable runnable = new RunnableExample();
        Thread thread = new Thread(runnable);
        thread.start();
        try{
            thread.sleep(3000); //sleep for 30 seconds
        } catch(InterruptedException e){}

        try{
            thread.join(); //end the thread once possible.
        } catch(InterruptedException e){}
    }

    @Override
    public void run(){
        System.out.println("called from thread.start()!");
    }
}