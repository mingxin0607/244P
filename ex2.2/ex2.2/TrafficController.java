public class TrafficController  {
    private static boolean bridgeTaken = false;
    public synchronized void enterLeft() {
        try {
            while (bridgeTaken == true) {
                this.wait();
            }
            bridgeTaken = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void enterRight() {
        try {
            while (bridgeTaken == true) {
                this.wait();
            }
            bridgeTaken = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void leaveLeft() {
        bridgeTaken = false;
        this.notify();
    }
    public synchronized void leaveRight() {
        bridgeTaken = false;
        this.notify();
    }

}