import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.*;

class Beeper {
    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public static void main(String[] args)  throws InterruptedException, ExecutionException  {
        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(() -> System.out.println("beep"), 3, 1, SECONDS);
        scheduler.schedule(()-> beeperHandle.cancel(true), 5 * 1, SECONDS);
        FutureTask<String> future =
                new FutureTask<String>(new Callable<String>() {
                    public String call() {
                        return "called";
                    }});
        //Future<String> f =
                scheduler.execute(future);
        System.out.println(future.get());

        System.out.println(scheduler.schedule(()->"foo",6,SECONDS).get().length());
    }
}