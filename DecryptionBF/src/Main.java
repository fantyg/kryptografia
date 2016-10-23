
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Decryption d = new Decryption();
		DecBFThread t1 = new DecBFThread(d);
		DecBFThread t2 = new DecBFThread(d);
		DecBFThread t3 = new DecBFThread(d);
		DecBFThread t4 = new DecBFThread(d);
		DecBFThread t5 = new DecBFThread(d);
		DecBFThread t6 = new DecBFThread(d);
		DecBFThread t7 = new DecBFThread(d);
		DecBFThread t8 = new DecBFThread(d);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
	}

}
