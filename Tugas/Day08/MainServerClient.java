// Java program to illustrate
// ThreadPool
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Task class to be executed (Step 1)
public class MainServerClient {

  static final int MAX_T = 2;

  public static void main(String[] args) {
    // array list untuk randomized makan dan tujuan
    ArrayList<String> makanan = new ArrayList<String>();
    makanan.add("Nasi Padang");
    makanan.add("Nasi Goreng");
    makanan.add("Nasi Goreng Gila");
    makanan.add("Nasi Kuning");
    makanan.add("Nasi Uduk");

    ArrayList<String> tujuan = new ArrayList<String>();
    tujuan.add("Condet");
    tujuan.add("Bekasi");
    tujuan.add("Tanah Abang");
    tujuan.add("Depok");
    tujuan.add("Bandung");

    // executor service pool thread.
    ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
    ExecutorService pool2 = Executors.newFixedThreadPool(1);

    // randomizer for input.
    Random randomNumber = new Random();
    System.out.println(randomNumber.nextInt(5));

    // loop untuk 100 kali jalan
    int i = 1;
    while (i <= 100) {

      Runnable r1 = new Client("pesanan", makanan.get(randomNumber.nextInt(5)));
      Runnable r2 = new Client("kirim", tujuan.get(randomNumber.nextInt(5)));
      Runnable printpesanan = new Server();
      // Execute pesanan dan tujuan berbarengan
      pool.execute(r1);
      pool.execute(r2);

      // Execute print pesanan setelah tujuan dan pesanan datang
      pool2.execute(printpesanan);

      i++;
    }

  }
}