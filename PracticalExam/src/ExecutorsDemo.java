import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecutorsDemo {

    public static void main(String []args) {
        System.out.println("Inside : " + Thread.currentThread().getName());

        System.out.println("Creating Executor Service...");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //ExecutorService executorService = Executors.newFixedThreadPool(2);

        System.out.println("Creating a Runnable...");
        Runnable runnable = () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Logger.getLogger(ExecutorsDemo.class.getName()).log(Level.SEVERE, null, e);
            }
            System.out.println("Inside : " + Thread.currentThread().getName());
        };

        Runnable runnable1 = () -> {
            System.out.println("Inside : " + Thread.currentThread().getName());
        };

        System.out.println("Submit the task specified by the runnable to the executor service.");
        executorService.submit(runnable);
        executorService.submit(runnable1);
    }
}
