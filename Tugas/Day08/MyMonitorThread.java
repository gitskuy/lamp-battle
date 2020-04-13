import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyMonitorThread implements Runnable {
  private ThreadPoolExecutor executor;
  private int seconds;
  private boolean run = true;

  public static void main(String[] args) throws InterruptedException {
    // RejectedExecutionHandler implementation
    RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
    // Get the ThreadFactory implementation to use
    ThreadFactory threadFactory = Executors.defaultThreadFactory();
    // creating the ThreadPoolExecutor
    ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,
        new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);
    // start the monitoring thread
    MyMonitorThread monitor = new MyMonitorThread(executorPool, 3);
    Thread monitorThread = new Thread(monitor);
    monitorThread.start();
    // submit work to the thread pool
    for (int i = 0; i < 10; i++) {
      executorPool.execute(new WorkerThread("cmd" + i));
    }

    Thread.sleep(30000);
    // shut down the pool
    executorPool.shutdown();
    // shut down the monitor thread
    Thread.sleep(5000);
    monitor.shutdown();
  }

  public MyMonitorThread(ThreadPoolExecutor executor, int delay) {
    this.executor = executor;
    this.seconds = delay;
  }

  public void shutdown() {
    this.run = false;
  }

  @Override
  public void run() {
    while (run) {
      System.out.println(
          String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
              this.executor.getPoolSize(), this.executor.getCorePoolSize(), this.executor.getActiveCount(),
              this.executor.getCompletedTaskCount(), this.executor.getTaskCount(), this.executor.isShutdown(),
              this.executor.isTerminated()));
      try {
        Thread.sleep(seconds * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}
