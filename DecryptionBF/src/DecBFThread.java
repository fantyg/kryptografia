
public class DecBFThread extends Thread {
	
	private Decryption d;
	
	public DecBFThread(Decryption d){
		super();
		this.d = d;
	}
	
	public void run(){
		d.bf();
	}
}
