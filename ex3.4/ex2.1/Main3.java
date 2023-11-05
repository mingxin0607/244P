import java.util.concurrent.*;

public class Main3 {
    final static Semaphore semaphore = new Semaphore(1);
   private static void nap(int millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void addProc(HighLevelDisplay d) {

	// Add a sequence of addRow operations with short random naps.
        for(int i=0; i < 20; i++) {
            try{
                semaphore.acquire();
            } catch (Exception e){
                e.printStackTrace();
            }
            try {
                d.addRow("AAAAAAAAAAAA  " + i);
            } finally {
                semaphore.release();
            }
            nap(200);
            try{
                semaphore.acquire();
            } catch (Exception e){
                e.printStackTrace();
            }
            try {
                d.addRow("BBBBBBBBBBBB  "+i);
            } finally {
                semaphore.release();
            }
            nap(300);
        }
   }

    private static void deleteProc(HighLevelDisplay d) {
	
	// Add a sequence of deletions of row 0 with short random naps.
        for(int i=0; i < 30; i++) {
            nap(1500);
            try{
                semaphore.acquire();
            } catch (Exception e){
                e.printStackTrace();
            }
            try {
                d.deleteRow(0);
            } finally {
                semaphore.release();
            }

        }
    }

    public static void main(String [] args) {
	final HighLevelDisplay d = new JDisplay2();

	new Thread () {
	    public void run() {
		addProc(d);
	    }
	}.start();


	new Thread () {
	    public void run() {
		deleteProc(d);
	    }
	}.start();

    }
}