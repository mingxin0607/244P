import java.util.concurrent.ArrayBlockingQueue;

public class MessageQueue {
    private static int n_ids;

    public static void main(String[] args) {
		task1();
//		task2();
//		task3();
		int n = Integer.parseInt(args[0]);
		int m = Integer.parseInt(args[1]);
//		task4(n, m);
    }
	private static void task1(){
		ArrayBlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
		Producer p1 = new Producer(queue, n_ids++);
		Consumer c1 = new Consumer(queue, n_ids++);

		new Thread(p1).start();
		new Thread(c1).start();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		p1.stop();
	}
	private static void task2(){
		ArrayBlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
		Producer p1 = new Producer(queue, n_ids++);
		Consumer c1 = new Consumer(queue, n_ids++);
		Consumer c2 = new Consumer(queue, n_ids++);

		new Thread(p1).start();
		new Thread(c1).start();
		new Thread(c2).start();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		p1.stop();
	}
	private static void task3(){
		ArrayBlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
		Producer p1 = new Producer(queue, n_ids++);
		Producer p2 = new Producer(queue, n_ids++);
		Consumer c1 = new Consumer(queue, n_ids++);
		Consumer c2 = new Consumer(queue, n_ids++);

		new Thread(p1).start();
		new Thread(p2).start();
		new Thread(c1).start();
		new Thread(c2).start();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		p1.stop();
		p2.stop();
	}
	private static void task4(int n, int m){
		ArrayBlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
		Producer[] producers = new Producer[m];
		Consumer[] consumers = new Consumer[n];
		for (int i = 0; i < producers.length; i++){
			producers[i] = new Producer(queue, n_ids++);
			new Thread(producers[i]).start();
		}
		for (int i = 0; i < consumers.length; i++){
			consumers[i] = new Consumer(queue, n_ids++);
			new Thread(consumers[i]).start();
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < producers.length; i++){
			producers[i].stop();
		}
	}
}
