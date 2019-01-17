class MyThread implements Runnable {


    @Override
    public void run() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("State of thread1 while it called join() method on thread2 - "
                + JavaThreadDemo.thread1.getState());

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

public class JavaThreadDemo implements Runnable {

    public static Thread thread1;
    public static JavaThreadDemo obj;

    @Override
    public void run() {
        MyThread myThread = new MyThread();
        Thread thread2 = new Thread(myThread);

        //thread1 created and it's currently in the NEW state.
        System.out.println("State of thread2 after creating it - " + thread2.getState());
        thread2.start();

        //thread2 moved to runnable state
        System.out.println("State of thread2 after calling start() method on it - " + thread2.getState());

        //moving thread1 to timed waiting state
        try {
            //moving thread1 to timed waiting state
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("State of thread2 after calling sleep() method on it - " + thread2.getState());

        try {
            // waiting for thread2 to die
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("State of thread2 when it has finished it's execution - " + thread2.getState());
    }

    public static void main(String [] args) {

        obj = new JavaThreadDemo();
        thread1 = new Thread(obj);

        //thread1 created and currently in NEW state
        System.out.println("State of thread1 after creating it - [Before the start] - " + thread1.getState());
        thread1.start();

        //thread moved to runnable state
        System.out.println("State of thread1 after calling start() method on it - " + thread1.getState());
    }
}
