import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    static Semaphore semaphore = new Semaphore(4);

    static class MyThread extends Thread {
        String name = "";

        MyThread(String name) {
            this.name = name;
        }

        public void run() {
            try {
                System.out.println(name + " : acquiring lock...");
                System.out.println(name + " : available semaphore permits now: " + semaphore.availablePermits());

                semaphore.acquire();
                System.out.println(name + " : got the permit!");

                try {
                    for(int i=1; i<=5; i++) {
                        System.out.println(name + " is performing operation " +i + ", available semaphore permits :"
                                + semaphore.availablePermits());
                        //sleep 1 second
                        Thread.sleep(1000);
                    }
                } finally {
                    //calling release after a successful acquire
                    System.out.println(name + " : releasing lock...");
                    semaphore.release();
                    System.out.println(name + " : available Semaphore permits now: " + semaphore.availablePermits()); 
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String []args) {
        System.out.println("Total available semaphore permits : " + semaphore.availablePermits());
        
        MyThread t1 = new MyThread("A");
        t1.start();

        MyThread t2 = new MyThread("B");
        t2.start();

        MyThread t3 = new MyThread("C");
        t3.start();

        MyThread t4 = new MyThread("D");
        t4.start();

        MyThread t5 = new MyThread("E");
        t5.start();

        MyThread t6 = new MyThread("F");
        t6.start();

    }
}
