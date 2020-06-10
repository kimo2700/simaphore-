
class main {
	public static visitor[] tor;
	public static  clock cl;
	public static Flag flag;
	public static speaker sp;
	public static void main(String args[])
	        throws InterruptedException {
		flag= new Flag();
		tor= new visitor[Integer.parseInt(args[0])];
		 cl=   new clock("1",flag);
		 
		sp =  new speaker("1",flag);
		
		cl.start();
		
		sp.start();
		
		for (int x=0; x<Integer.parseInt(args[0]); x++) {
	    
			tor[x]= new visitor(Integer.toString(x),flag,sp,x);
	        
	   }
		for (int i=0; i<Integer.parseInt(args[0]); i++) {
			tor[i].start();
		
}
	
		
		
		
}
}
