import java.util.concurrent.SynchronousQueue;

public class ProducerConsumer {
    public static void main(String[] args) {
        SynchronousQueue<String> drop = new SynchronousQueue<String>();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
}