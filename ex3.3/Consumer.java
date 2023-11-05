import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
    private ArrayBlockingQueue<Message> queue;
    private int id;

    public Consumer(ArrayBlockingQueue<Message> q, int n) {
	queue = q;
	id = n;
    }
    
    public void run() {
	Message msg = null;
	int count = 0;
	do {
	    try {
		msg = queue.poll(5,  TimeUnit.SECONDS); // Get a message from the queue
		count++;
			if (msg == null){
				break;
			}
		RandomUtils.print("Consumed " + msg.get(), id);
		Thread.sleep(RandomUtils.randomInteger());
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	} while (msg.get() != "stop");
	// Don't count the "stop" message
	count--;
	RandomUtils.print("Messages received: " + count, id);
    }
}
