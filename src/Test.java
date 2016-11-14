import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        for(int j=1; j < 16; j++){
            countPrimes(j);
        }
    }

    public static synchronized void countPrimes(int target) throws InterruptedException {
        Counter counter = new Counter();
        ExecutorService service = Executors.newCachedThreadPool();
        long startTime =System.nanoTime();

        for(int i=0;i<target;i++){
            String name=""+i;

            /*
            Per definition er 1 ikke et primtal, hvorfor vi ikke behøver tjekke denne og kan sætte start til 10000000/target)*i+1.

             */
            int start=(10000000/target)*i+1;
            int end=(10000000/target)*(i+1);
            if(i == target-1){
                if(end != 10000000){
                    end = 10000000;
                }
            }
            Thread t = new Thread(new CountPrimes(name,start,end,counter));
            t.join();
            t.start();
            service.submit(t);

        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS); // or longer.
        long time = System.nanoTime() - startTime;
        System.out.printf("Tasks took %.3f ms to run%n", time/1e6);

    }
}
