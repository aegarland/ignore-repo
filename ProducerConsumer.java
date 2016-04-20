import java.util.concurrent.SynchronousQueue;
import java.util.stream.IntStream;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProducerConsumer {
    public static void main(String[] args) {
        SynchronousQueue<String> drop = new SynchronousQueue<String>();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
        List<String> l = (new Random()).ints(50000L).mapToObj(i->String.format("entry %1$d",i)).collect(
                Collectors.toCollection(ArrayList::new));
        long n1 = 0L, n2 = 0L;
        for (int i = 100; --i > 0 ; ) {
            long start1 = System.nanoTime();
            l.stream().filter(s -> s.contains("11")).count();
            long end1 = System.nanoTime();
            n1+=(end1-start1);
            long start2 = System.nanoTime();
            l.parallelStream().filter(s -> s.contains("11")).count();
            long end2 = System.nanoTime();
            n2+=(end2-start2);
        }

        System.out.println("n1=" + n1 );
        System.out.println("n2=" + n2 );
    }
}