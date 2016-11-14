/**
 * Created by Anders on 08-11-2016.
 */
public class Counter {
    private volatile boolean isAvailable =true;
    private volatile long count =0;

    public Counter(){
        this.count=count;
    }

    public synchronized void increment() throws InterruptedException {
        while(!isAvailable){wait();}
        isAvailable=false;
        count=count+1;
        isAvailable=true;
        notifyAll();
    }

    public long getCount(){
        return count;
    }
}
