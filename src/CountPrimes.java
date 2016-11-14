import java.util.concurrent.Callable;

/**
 * Created by Anders on 06-11-2016.
 */
public class CountPrimes implements Runnable{
    private int startInterval;
    private int endInterval;
    private Counter counter;
    private String name;


    public CountPrimes(String name,int startInterval,int endInterval,Counter counter){
        this.startInterval=startInterval;
        this.endInterval=endInterval;
        this.counter=counter;
        this.name =name;
    }

    public String getName(){
        return name;
    }

    @Override
    public synchronized void run() {
        while(startInterval<endInterval){
            if(isPrime(startInterval)){
                try {
                    counter.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            startInterval += 1;
        }
        System.out.println(counter.getCount());
    }

    private static boolean isPrime(int n){
        int k = 2;
        while (k*k <= n && n % k != 0)
            k ++;
        return n >=2 && k*k > n;
    }


    public static synchronized void main(String[] args){
        long count =0;
        for(int i = 1; i<10000000; i++)
            if(isPrime(i)) count++;
        System.out.println("Number of primes: " + count);

    }

}

