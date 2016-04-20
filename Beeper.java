import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.*;
import java.util.*;
import java.util.stream.Collectors;

class Beeper {
    public static void main(String[] args)  throws InterruptedException, ExecutionException  {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(() -> System.out.println("beep"), 3, 1, SECONDS);
        scheduler.schedule(()-> beeperHandle.cancel(true), 5 * 1, SECONDS);
        FutureTask<String> future = new FutureTask<String>(() -> "called");
        scheduler.execute(future);
        System.out.println(future.get());

        System.out.println(scheduler.schedule(()->"foo",6,SECONDS).get().length());

        scheduler.shutdown();

        List<Person> people = new ArrayList<>();
        people.add(new Person("andy",51));
        people.add(new Person("helene",49));
        people.add(new Person("rachel",15));
        people.add(new Person("justin",13));
        people.add(new Person("daniel",10));

        Set<Integer> set = people.stream().map(Person::getId).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(Objects.toString(set));

        Map<String,Object> m1 = new HashMap<>();
        m1.put("Andy",people.get(0));
        m1.put("Justin",people.get(3));
        Map<String,Object> m2 = new HashMap<>();
        m2.put("Helene",people.get(1));
        m2.put("Daniel",people.get(4));
        List<Map<String,Object>> maps = new ArrayList<>();
        maps.add(m1);
        maps.add(m2);
        List<String> list = maps.stream().reduce(new ArrayList<String>(),
                                                 (l,m)->{ArrayList l4 = new ArrayList(l); l4.addAll(m.keySet()); return l4;},
                                                 (l1,l2)->{ArrayList l3 = new ArrayList();l3.addAll(l1);l3.addAll(l2); return l3; }
        );
        System.out.println(list);
    }
}