/**
 Basic Executor Design Pattern
 */
import java.util.concurrent.*;

public class ExecutorExample{

    public static void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(10);
        //Even More methods available

        for(int i = 0; i<100;i++){
            service.execute(new Task());
        }
        System.out.println("Thread Name: "+Thread.currentThread().getName());
        service.shutdown();
    }

    static class Task implements Runnable{
        public void run(){
            System.out.println("Thread Name: " + Thread.currentThread().getName());
        }
    }
}