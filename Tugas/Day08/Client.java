
// Java program to illustrate
// ThreadPool
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Client
public class Client implements Runnable {
  private String threadChooser;
  private String input;

  public Client(String s, String in) {
    threadChooser = s;
    input = in;
  }

  // Cek input pesanan atau tujuan alamat
  // Thread akan menwrite detail yang sudah masuk ketika sudah di
  // masukan detail
  public void run() {
    try {
      System.out.println("===========================================");
      if (threadChooser.equalsIgnoreCase("pesanan")) {
        Date d = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
        FileWriter myWriter = new FileWriter("filethread.txt");
        myWriter.write(input);
        myWriter.close();
        System.out.println("Pesanan: " + input + " is made at " + ft.format(d));

        // prints the initialization time for every task
      } else if (threadChooser.equals("kirim")) {
        Date d = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
        FileWriter myWriter = new FileWriter("kirim.txt");
        myWriter.write(input);
        myWriter.close();
        System.out.println("Tujuan: " + input + " is made at " + ft.format(d));
        // prints the execution time for every task
      }
      System.out.println("===========================================");
      Thread.sleep(4999);

    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}