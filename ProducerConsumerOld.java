import java.util.concurrent.SynchronousQueue;
import java.util.stream.IntStream;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.concurrent.locks.*;

public class ProducerConsumerOld {
    public static void main(String[] args) {
         Drop drop = new Drop();
        (new Thread(new ProducerOld(drop))).start();
        (new Thread(new ConsumerOld(drop))).start();
    }
}