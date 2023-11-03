import java.util.concurrent.*;

public class Main3 {

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
            d.addRow("AAAAAAAAAAAA  "+i);
            nap(200);
            d.addRow("BBBBBBBBBBBB  "+i);
            nap(300);
//            d.deleteRow(0);
//            nap(500);
        }
   }

    private static void deleteProc(HighLevelDisplay d) {
	
	// Add a sequence of deletions of row 0 with short random naps.
        for(int i=0; i < 30; i++) {
            nap(1500);
            d.deleteRow(0);
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