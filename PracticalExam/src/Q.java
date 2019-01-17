import java.util.concurrent.Semaphore;

public class Q {

    // an item
    int item;

    // semCon initialised with 0 permits
    // to ensure put() execute first
    static Semaphore semCon = new Semaphore(0);

    static Semaphore semProd = new Semaphore(1);

    // to get an item from buffer
    void get() {
        try {
            // before consumer can consume an item,
            // it must acquire a permit from semCon
            semCon.acquire();

        } catch(InterruptedException e) {
            System.out.println("InterruptedException caught");
        }

        // consumer consuming an item
        System.out.println("Consumer consumed an item : " + item);

        // after consumer consumes an item ,
        // it release semProd to notify producer
        semProd.release();
    }

    // to put an item to the buffer
    void put(int item) {
        try {
            // before producer can produce an item,
            // it must acquire a permit from semProd
            semProd.acquire();

        } catch(InterruptedException e) {
            System.out.println("InterruptedException caught");
        }

        // producer producing an item
        this.item = item;
        System.out.println("Producer produced an item : " + item);

        // after producer produced an item ,
        // it release semCon to notify consumer
        semCon.release();
    }
}

// Producer class
class Producer implements Runnable {
    Q q;
    Producer(Q q) {
        this.q = q;
        new Thread(this, "PRoducer").start();
    }
    
    public void run() {
        for(int i = 0; i <5; i++) {
            // producer put items
            q.put(i);
        }
    }
}


//Consumer class
class Consumer implements Runnable {
    Q q;
    Consumer(Q q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        for(int i = 0; i <5; i++) {
            // Consumer get items
            q.get();
        }
    }
}

// driver class
class PC {

    public static void main(String[] args) {

        //creating buffer queue
        Q q = new Q();

        //starting consumer thread
        new Consumer(q);

        //starting producer thread
        new Producer(q);
    }
}


