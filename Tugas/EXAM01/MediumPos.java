import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner;

public class MediumPos {
  public static Integer total = 0;

  public static void main(String args[]) {
    posHelp();
  }

  public static void posHelp() {
    Scanner input = new Scanner(System.in);
    PelangganData pd = new PelangganData();
    int flag = 0;
    String pesanan = "";
    String namapelanggan = "";
    int idgenerator = 0;
    while (flag == 0) {
      System.out.println("--------------------");
      System.out.println("1.Tambah Pesanan");
      System.out.println("2.Print Reciept");
      System.out.println("3.Data Pelanggan");
      System.out.println("4.Exit");
      System.out.println("--------------------");

      String inputforPos = input.nextLine();
      if (inputforPos.equals("1")) {
        if (namapelanggan.equals("")) {
          System.out.println("Nama pelanggan?");
          namapelanggan = input.nextLine();
          pesanan = tambahPesanan(input, pesanan);
        } else {
          pesanan = tambahPesanan(input, pesanan);
        }
      } else if (inputforPos.equals("2")) {
        pesanan = "Nama Pelangan: " + namapelanggan + "\n" + pesanan;
        pesanan += "total : Rp" + total;
        endTransaction(pesanan, namapelanggan, pd);
        pesanan = "";
        total = 0;
        namapelanggan = "";
      } else if (inputforPos.equals("3")) {
        System.out.println("--------------------");
        System.out.println("1.Tambah Pelanngan");
        System.out.println("2.Edit Pelanggan");
        System.out.println("3.Main Menu");
        System.out.println("--------------------");
        int dpmenu = Integer.parseInt(input.nextLine());
        if (dpmenu == 1) {
          System.out.println("Nama Pelangan Baru?");
          String namapelangan = input.nextLine();
          String idpel = "" + namapelangan.charAt(0) + idgenerator;
          tambahPelangan(namapelangan, idpel, pd);
        } else if (dpmenu == 2) {
          editData(input, pd);
        } else {
          continue;
        }
      } else if (inputforPos.equals("4")) {
        System.out.println("Thank you :D");
        flag = 1;
      } else {
        System.out.println("Salah input bro");
      }
      idgenerator++;
    }
  }

  private static void editData(Scanner input, PelangganData pd) {
    System.out.println("Edit Siapa?");
    String namapelangan = input.nextLine();
    System.out.println("Edit Apa? (Nama,Poin)?");
    String inputuser = input.nextLine();
    if (inputuser.equalsIgnoreCase("Nama")) {
      System.out.println("Nama Baru?");
      String namabaru = input.nextLine();
      pd.editNama(namapelangan, namabaru);
    }
    if (inputuser.equalsIgnoreCase("Poin")) {
      System.out.println("Poin Baru?");
      Integer poinbaru = Integer.parseInt(input.nextLine());
      pd.editPoin(namapelangan, poinbaru);
    }

    {

    }

  }

  private static void tambahPelangan(String namapelanggan, String idgenerator, PelangganData pd) {

    System.out.println("--------------------");
    Pelanggan np = new Pelanggan(namapelanggan, 0, pd.getPelangan().size() + 1);
    pd.getPelangan().add(np);
    for (int i = 0; i < pd.getPelangan().size(); i++) {
      System.out.println(pd.getPelangan().get(i).printDetails());
    }
    pd.updateData();

    // System.out.println(readdata);
    // readdata += idgenerator + " " + namapelanggan + " " + 0;
    // FileWriter myWriter = new FileWriter("userpoin.txt");
    // myWriter.write(readdata);
    // myWriter.close();

    // myReader.close();

    System.out.println("--------------------");

  }

  private static String tambahPesanan(Scanner input, String pesanan) {
    System.out.println("Pesan apa?");
    pesanan += input.nextLine();
    System.out.println("Harga? ");
    int harga = Integer.parseInt(input.nextLine());
    pesanan += ": Rp" + harga + "\n";
    total += harga;
    System.out.println("--------------------");
    System.out.println("Pesanan Berhasil ditambahkan");
    System.out.println("--------------------");
    return pesanan;
  }

  public static void endTransaction(String pesanan, String namapelangan, PelangganData pd) {
    PrintReciept t1 = new PrintReciept(pesanan);

    if (pd.cekPoin(namapelangan) == true) {
      pd.run();
      t1.run();

    } else {
      t1.run();

    }
  }
}