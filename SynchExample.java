/**
demonstrating the synchronized keyword
*/
public class SynchExample{
	public static void main(String[] args){
		
		Counter c = new Counter();
		CounterAction ca_increment = new CounterAction(c,true);
		CounterAction ca_decrement = new CounterAction(c,false);
		ca_increment.start();
	    ca_decrement.start();
		
		try{
			Thread.sleep(1); //let the program go for n microseconds
		} catch(InterruptedException e) {}
		
		c.shutdown();
		
		try{
			ca_increment.join();
			ca_decrement.join();
			System.out.println("Main Program Exiting");
		} catch(InterruptedException e) {}
	}
	
	
	//synchronized methods and statements
	/* synchronized methods
	1. synchronized methods cannot have more than one invocation on the same interleave
		i.e. two threads cannot operate on a synchronized method at once
	2. when a synchronized method exits it auto. establishes 
	" a happens-before relationship with any subsequent invocation of a synchronized method for the same object"
	(whatever the heck that means)
	*/
	
	private static class CounterAction extends Thread{
		private boolean isIncrement = false;
		private Counter counter;
		
		public CounterAction(Counter counter){
			this.counter = counter;
			
		}
		public CounterAction(Counter counter, boolean isIncrement){
			this.isIncrement = isIncrement;
			this.counter = counter;
		}
		
		public void run(){
			while(counter.isRunning()){
				if(isIncrement)
					counter.increment();
				else
					counter.decrement();
				System.out.println(Thread.currentThread().getName()+" value: "+counter.value());
			}
		}
	}
	
	private static class Counter{
		private int counter = 0;
		private boolean running = true;
		
		public synchronized void increment(){
			counter++;
		}
		
		public synchronized void decrement(){
			counter--;
		}
		
		public synchronized int value(){
			return counter;
		}
		
		public boolean isRunning(){
			return running;
		}
		
		public void shutdown(){
			running = false;
		}
	}
	
	/*
	can also just define entire synchronized blocks. as such:
	synchronized{
		
	}
	*/
	
}