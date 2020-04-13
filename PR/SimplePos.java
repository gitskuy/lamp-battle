import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle
import java.util.Scanner;

public class SimplePos {

  public static void main(String args[]) {
    Scanner input = new Scanner(System.in);
    int flag = 0;
    String pesanan = "";

    while (flag == 0) {
      System.out.println("--------------------");
      System.out.println("1.Tambah Pesanan");
      System.out.println("2.Print Reciept");
      System.out.println("3.Pesanan Baru");
      System.out.println("4.End");

      String inputforPos = input.nextLine();
      if (inputforPos.equals("1")) {
        System.out.println("Pesan apa?");
        pesanan += input.nextLine() + "\n";
      } else if (inputforPos.equals("2")) {
        endTransaction(pesanan);
      } else if (inputforPos.equals("3")) {
        pesanan = "";
      } else if (inputforPos.equals("4")) {
        System.out.println("Thank you :D");
        flag = 1;
      } else {
        System.out.println("Salah input bro");
      }
    }

  }

  public static void endTransaction(String pesanan) {
    PrintReciept t1 = new PrintReciept(pesanan);
    SendData t2 = new SendData(pesanan);
    t1.run();
    t2.run();
  }
}