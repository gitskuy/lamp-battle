import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException;
import java.util.Scanner;

// class server yang merupakan class yang implement runable
public class Server implements Runnable {

  // Sebuah thread yang membaca input dari
  // file yang akan mengeluarkan nya pada console
  public void run() {
    try {
      System.out.println("##############################################");
      System.out.println("PESANAN:");
      File myObj = new File("filethread.txt");

      Scanner myReader = new Scanner(myObj);
      String readdata = "";
      while (myReader.hasNextLine()) {
        System.out.println(myReader.nextLine());
      }
      System.out.println("KIRIM KE:");
      File myObj2 = new File("kirim.txt");
      Scanner myReader2 = new Scanner(myObj2);
      String readdata2 = "";
      while (myReader2.hasNextLine()) {
        System.out.println(myReader2.nextLine());
      }
      System.out.println("##############################################");
      Thread.sleep(5000);
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}