
// Java program to illustrate
// ThreadPool
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Task class to be executed (Step 1)
class Task implements Runnable {
  private String name;

  public Task(String s) {
    name = s;
  }

  // Prints task name and sleeps for 1s
  // This Whole process is repeated 5 times
  public void run() {
    try {
      for (int i = 0; i <= 5; i++) {
        if (i == 0) {
          Date d = new Date();
          SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
          System.out.println("Initialization Time for" + " task name - " + name + " = " + ft.format(d));
          // prints the initialization time for every task
        } else {
          Date d = new Date();
          SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
          System.out.println("Executing Time for task name - " + name + " = " + ft.format(d));
          // prints the execution time for every task
        }
        Thread.sleep(1000);
      }
      System.out.println(name + " complete");
    }

    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

public class PersonThread {
  // Maximum number of threads in thread pool
  static final int MAX_T = 3;

  public static void main(String[] args) {
    // creates five tasks
    Date d = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
    Runnable r1 = new Person("lia", "male", d);
    Runnable r2 = new Person("relia", "male", d);
    Runnable r3 = new Person("lida", "male", d);
    Runnable r4 = new Person("viba", "male", d);
    Runnable r5 = new Person("Ahmad", "male", d);
    Runnable r6 = new Person("Yazid", "male", d);
    Runnable r7 = new Person("Harharha", "male", d);
    Runnable r8 = new Person("Dino", "male", d);
    Runnable r9 = new Person("Desi", "male", d);

    // creates a thread pool with MAX_T no. of
    // threads as the fixed pool size(Step 2)
    ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

    // passes the Task objects to the pool to execute (Step 3)
    pool.execute(r1);
    pool.execute(r2);
    pool.execute(r3);
    pool.execute(r4);
    pool.execute(r5);
    pool.execute(r6);
    pool.execute(r7);
    pool.execute(r8);
    pool.execute(r9);

    // pool shutdown ( Step 4)
    pool.shutdown();
  }
}