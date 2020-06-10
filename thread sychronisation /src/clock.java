
public class clock implements Runnable{
	int T = 60*30*1000;

	private Flag flag;
	private String num;
	private Thread thread;
	public clock(String num,Flag flag) {
		this.num="clock_"+num;
		this.thread=new Thread(this,num);
		this.flag= flag;
		}
	public void start() {
	    thread.start();
	  }
	public void join(long t) {
		try {
			thread.join(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static long time = System.currentTimeMillis();
	int  seat = 5;
	public  void msg(String m) {
		 System.out.println(time +num+m);
		 }
	
	
	public void run() {
		
		for(int i=0; i<=2; i++) {// the total 3 shows
			try {
				
				Flag.giftsRecieved.acquire();// 
				msg("start the show");
				Flag.showNumber++;
				for(int i1=0;i1<5;i1++) {
				Flag.showOn.release();// show is started for  whose  got a seat 
				}
	
	
		
		Thread.sleep(6000);// show time 
		}
		catch(Exception e) {
			msg("....");		
			
		}
		msg("end of the show");// end of the show
		
		Flag.semEndofmovie.release();// Signal the speaker that movie finish	

}
		Flag.semEndofmovie.release();// releasing the speaker 
		Flag.spGr1.release();// releasing the speaker 
		Flag.spGr2.release();// releasing the speaker 
while(Flag.semVisitors.hasQueuedThreads()) {// releasing what visitors left without seeing a show
	Flag.semVisitors.release();
}
msg("museum closing");
 
}
}
