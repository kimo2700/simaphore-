

public class visitor implements Runnable {


	
	
	private speaker sp;
	private Flag flag;
	private String num;
	private Thread thread;
	private int id;
	public visitor(String num,Flag flag,speaker s ,int id) {// Create a visitor constructor
		this.num="visitor_"+num;
		this.id=id;
		this.thread=new Thread(this,num);
		this.flag=flag;
		flag.addVisitor(id, this);
		
		sp=s;
		}
	public static long time = System.currentTimeMillis();
	private int showSeen=0;
	public  void msg(String m) {
		 System.out.println(time +num+m);
	}
	public void start() {
	    thread.start();
	  }
	private boolean isAlive() {
		return	thread.isAlive();		
			
		}
	private void join() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	
   
	private boolean readyTogo = false;
	private boolean getreadyTogo() {
		return readyTogo;
	}

	
	

	public void run(){
		
				try {
					
				Flag.semVisitors.acquire();// check if there is  available seat
				if (Flag.visturn>=15) {// after the 3 shows visitor wasn't able to see 
					System.out.println("visitor_"+id+"report i wasnt able to see any show");
					try {
						Flag.Mutex3.acquire();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
					Flag.ter++;// how many visitor terminate
					Flag.Mutex3.release();
					msg("terminate");
					return;
				}
				showSeen= Flag.showNumber;// passing the show number to visitor
			Flag.Mutex2.acquire();
			
		      Flag.visturn++;// counting the # of visitor had the chance to see the show
	
		    Flag.Mutex2.release();
				Flag.showOn.acquire();// show is announced by the clock
				
				msg("enter the show");
				
				thread.sleep (1000);
			
			Flag.speakerSignal.acquire();// waiting for speaker signal to release the seat
			Flag.semVisitors.release();// release the seat
			
		
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			try {
				Flag.Mutex1.acquire();
				Flag.numberOfgroup++;// # of visitor waiting for gifts
				if(Flag.numberOfgroup<3) {
					Flag.Mutex1.release();
					Flag.gr1.acquire();}//add to the 1st group queue
				else if(Flag.numberOfgroup==3) {
					Flag.spGr1.release();// notify the speaker that group 1 is ready
				    Flag.Mutex1.release();
				    Flag.gr1.acquire();}
				    
				else if(Flag.numberOfgroup<5) {
					Flag.Mutex1.release();
					Flag.gr2.acquire();}//add to the 2st group queue
				else if(Flag.numberOfgroup==5) {
					Flag.spGr2.release(); // notify the speaker that group 2 is ready
					
					Flag.numberOfgroup=0;
				Flag.Mutex1.release();}
				Flag.gr2.acquire();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
 msg("gift recieved");
readyTogo=true;// visitor is ready to leave
try {
	thread.sleep(2000);// wander around for awhile
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
readyTogo=true;// visitor is ready to leave
if(id>=1) {
if(Flag.vis[id-1]!=null&&Flag.vis[id-1].isAlive()&&Flag.vis[id-1].getreadyTogo() ){// if id-1 is alive and had received his gift wait for it to terminate first
	Flag.vis[id-1].join();
	
}}

System.out.println("visitor_"+id+"report saw show #"+showSeen);// the show the visitor had seen
try {
	Flag.Mutex3.acquire();// protecting the # of terminated visitor

} catch (InterruptedException e) {
	
	e.printStackTrace();
}
Flag.ter++;// how many visitor terminate
Flag.Mutex3.release();

msg("terminate");



}
	
		
	
}
	
