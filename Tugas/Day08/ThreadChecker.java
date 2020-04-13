
// Java program to illustrate
// ThreadPool
import java.io.File;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Task class to be executed (Step 1)
public class ThreadChecker {

  static final int MAX_T = 1;

  public static void main(String[] args) {
    try {

      Scanner in = new Scanner(System.in);

      FileWriter myWriter = new FileWriter("filethread.txt");
      FileWriter myWriter2 = new FileWriter("kirim.txt");

      int text = 0;
      while (text != 1) {
        System.out.println("1.New Pesanan");
        System.out.println("2.Close Pesanan");
        System.out.println("3.Print Pesanan");
        Integer input = Integer.parseInt(in.nextLine());

        // input 1 menulis
        if (input == 1) {
          newPesanan(in, myWriter, myWriter2);
        } else if (input == 2) {
          closePesanan(myWriter, myWriter2);
        } else if (input == 3) {
          printDetail(myWriter);
        } else {
          System.out.println("Salah Input bro");
        }
      }

    } catch (IOException e) {
      File myObj = new File("filethread.txt");
      Runnable printpesanan = new Server();
      printpesanan.run();
    }

  }

  private static void printDetail(FileWriter myWriter) throws IOException {
    myWriter.flush();
    if (myWriter != null) {
      System.out.println("Please close the pesanan sebelum print detail");
    }
  }

  private static void closePesanan(FileWriter myWriter, FileWriter myWriter2) throws IOException {
    System.out.println("Pesanan Finalized");
    myWriter.close();
    myWriter2.close();
  }

  private static void newPesanan(Scanner in, FileWriter myWriter, FileWriter myWriter2) throws IOException {
    System.out.println("1.New Pesanan");
    System.out.println("Makanan?");
    myWriter.write(in.nextLine());
    System.out.println("Tujuan?");
    myWriter2.write(in.nextLine());
    System.out.println("Jangan lupa untuk finalized pesanan sebelum print detail!");
  }
}