import java.util.Random;
import java.util.concurrent.SynchronousQueue;

public class Consumer implements Runnable {
    private SynchronousQueue<String> drop;

    public Consumer(SynchronousQueue<String> drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        try {
            for (String message = drop.take();
                 !message.equals("DONE");
                 message = drop.take()) {
                System.out.format("MESSAGE RECEIVED: %s%n", message);
                try {
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {
                }
            }
        }
            catch (InterruptedException e) {}
    }
}