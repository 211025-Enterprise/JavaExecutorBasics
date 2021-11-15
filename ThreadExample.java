/**
base forms of threadings
*/
public class ThreadExample{
	public static void main(String[] args){
		
		// == Using the Runnable interface == //
		Task task = new Task();
		Thread thread = new Thread(task);
		thread.start();
		System.out.println("From main: "+Thread.currentThread().getName());
		try{
			thread.join(); //end the thread once possible.
		} catch(InterruptedException e){}
		
		// == Extending the Thread class == //
		TaskThread threadTask = new TaskThread();
		threadTask.start();
		
		try{
			threadTask.sleep(3000);//also. pause thread exe. for 3 seconds
			threadTask.join();//wait for the completion of another thread. current exe. pause until threadTask terminates
		} catch(InterruptedException e){}
		
		//even more simply and more esoteric
		(new TaskThread()).start();
	}
	
	private static class Task implements Runnable{ //more flexible but cumbersome.
		@Override
		public void run(){
			System.out.println("From Task: "+Thread.currentThread().getName());
			System.out.println("Print from task thread");
		}
	}
	
	private static class TaskThread extends Thread { //less flexible but easier to setup.
		//Don't *need* to override the run() method
		// as Thread itself implements Runnable
	}
	
}