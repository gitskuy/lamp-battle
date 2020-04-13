import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle

public class SendData implements Runnable {
  private String pesanan;

  public SendData(String pesanan) {
    this.pesanan = pesanan;
  }

  public void run() {
    try {
      System.out.println("Data has been sent to backend :D");
      FileWriter myWriter = new FileWriter("backendData.txt");
      myWriter.write(pesanan);
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

}