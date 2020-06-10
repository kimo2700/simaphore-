

public class speaker implements Runnable {
    boolean called = false;
    private Flag flag;
    private String num;
	private Thread thread;
	public speaker(String num,Flag flag) {
		this.num="speaker_"+num;
		this.thread=new Thread(this,num);
		this.flag= flag;
		}
	
	
	public void start() {
	    thread.start();
	  }

    static int party_ticket= 3;
	
	public static long time = System.currentTimeMillis();
	
	public  void msg(String m) {
		 System.out.println(time +num+m);
}
	public void run() {
		
		do { 
	
		try
		{
		Flag.semEndofmovie.acquire();// wait until the end of the movie
		msg("show end creat a goups of size 3 ");
		for (int i1=0;i1<=5;i1++) {
			Flag.speakerSignal.release();// speaker signaling visitors in the show that they able to release  there seats
		}
		
		}
		catch(Exception e) {
		}
		
		
		try {
			Flag.spGr1.acquire();// wait for the signal of the last visitor of group 1
			
			for(int i=0;i<3;i++) {// signal as much as group1 queue
				// giving gifts to group1 members
				Flag.gr1.release();	
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Flag.spGr2.acquire();// waiting for the signal of last visitor of group 2
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (Flag.gr2.hasQueuedThreads()) {// signals as much as group2 queue 
	 Flag.gr2.release(); //giving gift too group2 members
		}
		try {
			thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Flag.giftsRecieved.release();// everybody group has his gift
			
			 
		}while(Flag.ter!=23); // terminate after every visitor terminate
		msg ("terminate");


}
}
