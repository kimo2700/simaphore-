import java.util.concurrent.Semaphore;
public class Flag {// semaphore class
	static int numberOfgroup;
	static int ter;
	static int visturn;
	static int showNumber=0;
	public static visitor[] vis =new visitor[30];
	static Semaphore semVisitors = new Semaphore(5);
	static Semaphore semEndofmovie = new Semaphore(0);
	static Semaphore Mutex1 = new Semaphore(1);
	static Semaphore Mutex2 = new Semaphore(1);
	static Semaphore Mutex3 = new Semaphore(1);
	static Semaphore gr1 = new Semaphore(0);
	static Semaphore gr2 = new Semaphore(0);
	static Semaphore spGr1 = new Semaphore(0);
	static Semaphore spGr2 = new Semaphore(0);
	static Semaphore speakerSignal = new Semaphore(0);
	static Semaphore showOn = new Semaphore(0);
	static Semaphore giftsRecieved = new Semaphore(1);
	public void addVisitor(int i,visitor a){// add a visitor to array
		vis[i]=a;}



	
	
}


