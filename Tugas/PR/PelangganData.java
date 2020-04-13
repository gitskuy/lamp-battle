import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors

public class PelangganData implements Runnable {
  private String data;
  private ArrayList<Pelanggan> pelangan = new ArrayList<Pelanggan>();

  public PelangganData() {
    try {
      System.out.println("--------------------");
      File myObj = new File("userpoin.txt");
      Scanner myReader = new Scanner(myObj);
      String readdata = "";
      while (myReader.hasNextLine()) {
        String[] data = myReader.nextLine().split("/");
        Pelanggan toData = new Pelanggan(data[1], Integer.parseInt(data[2]), Integer.parseInt(data[0]));
        pelangan.add(toData);
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static void main(String args[]) {
    PelangganData pd = new PelangganData();
    System.out.println(pd.pelangan.get(0).printDetails());
  }

  public void run() {
    try {
      String readdata = "";
      for (int i = 0; i < pelangan.size(); i++) {
        readdata += pelangan.get(i).getId() + "/" + pelangan.get(i).getNamapelangan() + "/" + pelangan.get(i).getPoin()
            + "\n";
      }
      System.out.print(readdata);
      FileWriter myWriter = new FileWriter("userpoin.txt");
      myWriter.write(readdata);
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public ArrayList<Pelanggan> getPelangan() {
    return pelangan;
  }

  public Boolean cekPoin(String nama) {
    for (int i = 0; i < pelangan.size(); i++) {
      if (pelangan.get(i).getNamapelangan().equals(nama)) {
        pelangan.get(i).setPoin(pelangan.get(i).getPoin() + 10);
        System.out.println("Selamat " + nama + " poin anda bertambah 10");
        System.out.println("Poin Sekarang " + pelangan.get(i).getPoin());
        return true;
      }
      ;
    }
    return false;
  }

  public Boolean editNama(String nama, String namabaru) {
    for (int i = 0; i < pelangan.size(); i++) {
      if (pelangan.get(i).getNamapelangan().equals(nama)) {
        pelangan.get(i).setNamapelangan(namabaru);
        System.out.println("Selamat " + nama + " nama anda telah diganti menjadi " + namabaru);
        updateData();
        return true;
      }
      ;
    }
    System.out.println("Nama tidak di temukan");
    return false;
  }

  public Boolean editPoin(String nama, Integer poinbaru) {
    for (int i = 0; i < pelangan.size(); i++) {
      if (pelangan.get(i).getNamapelangan().equals(nama)) {
        pelangan.get(i).setPoin(poinbaru);
        System.out.println("Poin Sekarang " + pelangan.get(i).getPoin());
        updateData();
        return true;
      }
      ;
    }
    System.out.println("Nama tidak di temukan");
    return false;
  }

  public void updateData() {
    try {
      String readdata = "";
      for (int i = 0; i < pelangan.size(); i++) {
        readdata += pelangan.get(i).getId() + "/" + pelangan.get(i).getNamapelangan() + "/" + pelangan.get(i).getPoin()
            + "\n";
      }
      System.out.print(readdata);
      FileWriter myWriter = new FileWriter("userpoin.txt");
      myWriter.write(readdata);
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    // System.out.println(readdata);
    // readdata += idgenerator + " " + namapelanggan + " " + 0;

    // myWriter.close();

    // myReader.close();
  }

  public void setPelangan(ArrayList<Pelanggan> pelangan) {
    this.pelangan = pelangan;
  }

}