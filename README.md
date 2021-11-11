# Java Executor Basics
## Some notes and explanation code pertaining to Java Executors

***

[Official Tutorial](https://docs.oracle.com/javase/tutorial/essential/concurrency/executors.html)

[Official Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executors.html)

#### Corresponding Classes:

* [Executor Service](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html)
* [Thread](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Thread.html)
* [Runnable](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)

#### Intro Video:

* <https://www.youtube.com/watch?v=6Oo-9Can3H8>

### Overview

The Executor interface provides a single method, execute, that is a replacement for thread creation.
It allows for a thread pool that regular threads don't come with support. 

### Models:

The Executor Design Pattern is should generally be used over the "Runnable" design pattern since it allows for better 
flexibility and utility. 

    import java.util.concurrent.*;

    public class ExecutorServiceExample implements Runnable {

        public void run() {
            System.out.println("Thread has ended");
        }   

        public static void main(String[] args) {
            ExecutorServiceExample example = new ExecutorServiceExample();
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for(int x = 0; x < 100; x++){
                executorService.execute(example);
            }
    
            executorService.shutdown();
            System.out.println("Hi");
        }
    } 

See how the "runnable" design pattern offers less flexible

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

### Summary

Executors are sophisticated tools, which let you choose how many concurrent tasks may be running, and tune different aspects of the execution context. They also provide facilities to monitor the tasks' executions, by returning a token (called a Future or sometimes a promise) which let the code requesting the task execution to query for that task completion.
Threads are less elaborate (or more barebone) a solution to executing code asynchronously. You can still have them return a Future by hand, or simply check if the thread is still running.

So depending on how much sophistication you require, you will pick one, or the other: Executors for more streamlined requirements (many tasks to execute and monitor), Threads for one shot or simpler situations.