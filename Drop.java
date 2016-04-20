import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Drop {
    // Message sent from producer
    // to consumer.
    private String message;
    // True if consumer should wait
    // for producer to send message,
    // false if producer should wait for
    // consumer to retrieve message.
    private boolean empty = true;

    private final Lock lock;
    private final Condition condition;

    public Drop () {
        this.lock = new ReentrantLock();
        this.condition  = lock.newCondition();
    }

    public /* synchronized */ String take() {
        // Wait until message is
        // available.
        try {
            lock.lock();
            while (empty) {
                try {
                    //wait();
                    condition.await();
                } catch (InterruptedException e) {
                }
            }
            // Toggle status.
            empty = true;
            // Notify producer that
            // status has changed.
            //notifyAll();
            condition.signalAll();

            return message;
        }
        finally {
            lock.unlock();
        }
    }

    public /* synchronized */ void put(String message) {
        // Wait until message has
        // been retrieved.
        try {
            lock.lock();
            while (!empty) {
                try {
                    condition.await();
                    //wait();
                } catch (InterruptedException e) {
                }
            }
            // Toggle status.
            empty = false;
            // Store message.
            this.message = message;
            // Notify consumer that status
            // has changed.
            //notifyAll();
            condition.signalAll();
        }
        finally {
            lock.unlock();
        }
    }
}