import java.util.Random;
import java.util.concurrent.SynchronousQueue;

public class Producer implements Runnable {
    private SynchronousQueue<String> drop;

    public Producer(SynchronousQueue<String> drop) {
        this.drop = drop;
    }

    public void run() {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };
        Random random = new Random();

        try {
            java.util.Arrays.stream(importantInfo).forEach(
                    s -> {
                        try {
                            drop.put(s);
                            try {
                                Thread.sleep(random.nextInt(5000));
                            } catch (InterruptedException e) {
                            }
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
            drop.put("DONE");
        }
        catch (Exception e) {}
    }
}