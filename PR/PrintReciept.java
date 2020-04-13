import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle

public class PrintReciept implements Runnable {
  private String pesanan;

  public PrintReciept(String pesanan) {
    this.pesanan = pesanan;
  }

  public void run() {
    try {
      System.out.println("Reciept has been printed :D");
      FileWriter myWriter = new FileWriter("reciept.txt");
      myWriter.write(pesanan);
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

}