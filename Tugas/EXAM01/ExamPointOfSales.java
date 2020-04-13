import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;
import java.io.File;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.*;

public class ExamPointOfSales {

  public static void main(String args[]) {
    int flagLoop = 0;
    while (flagLoop == 0) {
      Scanner in = new Scanner(System.in);
      int menu = landingPage(in);
      Menu Menu[] = makeMenu();
      InventoriItems Invent[] = makeInventori();
      System.out.println(Menu[0]);
      if (menu == 1) {
        // backoffice
        backoffice(in, Menu, Invent);
      } else if (menu == 2) {
        // kasir
        MediumPos x = new MediumPos();
        x.posHelp();
      } else if (menu == 3) {
        System.out.println("EXIT");
        flagLoop = 1;
      } else {
        System.out.println("##########################");
        System.out.println("Salah Input");
        System.out.println("##########################");
      }

    }

  }

  private static Menu[] makeMenu() {
    Menu Menu[] = new Menu[100];

    try {
      System.out.println("--------------------");
      File myObj = new File("menu.txt");
      Scanner myReader = new Scanner(myObj);
      String readdata = "";
      int idgen = 0;

      while (myReader.hasNextLine()) {
        String[] data = myReader.nextLine().split("/");
        String[] ingridients = data[3].split(",");
        InventoriItems[] x = new InventoriItems[ingridients.length];
        for (int i = 0; i < ingridients.length; i++) {
          InventoriItems s = new InventoriItems(ingridients[0], i, 1);
          x[i] = s;
        }
        Menu makantoarray = new Menu(data[1], Integer.parseInt(data[0]), Integer.parseInt(data[2]), x);
        Menu[idgen] = makantoarray;
        idgen++;
      }

    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return Menu;
  }

  private static InventoriItems[] makeInventori() {
    InventoriItems Invent[] = new InventoriItems[100];

    try {
      System.out.println("--------------------");
      File myObj = new File("inventori.txt");
      Scanner myReader = new Scanner(myObj);
      String readdata = "";
      int idgen = 0;

      while (myReader.hasNextLine()) {
        String[] data = myReader.nextLine().split("/");
        InventoriItems inventtoarray = new InventoriItems(data[1], Integer.parseInt(data[0]),
            Integer.parseInt(data[2]));
        Invent[idgen] = inventtoarray;

        System.out.println(Invent[idgen]);
        idgen++;
      }

    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return Invent;
  }

  private static int landingPage(Scanner in) {
    System.out.println("--------------------");
    System.out.println("1.BackOffice");
    System.out.println("2.Kasir");
    System.out.println("3.Exit");
    System.out.println("--------------------");
    int menu = Integer.parseInt(in.nextLine());
    return menu;
  }

  public static void backoffice(Scanner in, Menu[] menuAr, InventoriItems[] inv) {
    System.out.println("--------------------");
    System.out.println("1.Inventori");
    System.out.println("2.Menu");
    System.out.println("--------------------");
    int menu = Integer.parseInt(in.nextLine());
    if (menu == 1) {
      System.out.println("--------------------");
      System.out.println("1.Add Inventori ");
      System.out.println("2.Edit Inventori");
      System.out.println("--------------------");
      int inmenu = Integer.parseInt(in.nextLine());
      if (inmenu == 1) {
        System.out.println("Nama Inventori Baru?");
        String nama = in.nextLine();
        System.out.println("Jumlah");
        int jumlah = Integer.parseInt(in.nextLine());
        addInventori(nama, jumlah);
      }
      // if (inmenu == 2) {
      // System.out.println("Nama dan jumlah?");
      // deleteInventori(in.nextLine(), inv, Integer.parseInt(in.nextLine()));
      // }
    } else if (menu == 2) {
      System.out.println("--------------------");
      System.out.println("1.Add Menu ");
      System.out.println("--------------------");
      int inmenu = Integer.parseInt(in.nextLine());
      if (inmenu == 1) {
        System.out.println("Nama Menu Baru?");
        String nama = in.nextLine();
        System.out.println("Harga");
        String harga = in.nextLine();
        System.out.println("Ingridients?");
        String ingridients = in.nextLine();
        addMenu(nama, harga, ingridients);
      }
      // kasir
    } else {
      System.out.println("##########################");
      System.out.println("Salah Input");
      System.out.println("##########################");
    }
  }

  public static void addInventori(String nama, int jumlah) {
    try {
      System.out.println("--------------------");
      File myObj = new File("inventori.txt");
      Scanner myReader = new Scanner(myObj);
      String readdata = "";
      int idgen = 0;

      while (myReader.hasNextLine()) {
        readdata += myReader.nextLine() + "\n";
        idgen++;
      }

      readdata += (idgen + 1) + "/" + nama + "/" + jumlah + "\n";

      // System.out.print(readdata);

      FileWriter myWriter = new FileWriter("inventori.txt");
      myWriter.write(readdata);
      myWriter.close();

    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static void addMenu(String nama, String harga, String ingridients) {
    try {
      System.out.println("--------------------");
      File myObj = new File("menu.txt");
      Scanner myReader = new Scanner(myObj);
      String readdata = "";
      int idgen = 0;

      while (myReader.hasNextLine()) {
        readdata += myReader.nextLine() + "\n";
        idgen++;
      }

      readdata += (idgen + 1) + "/" + nama + "/" + harga + "/" + ingridients + "\n";

      // System.out.print(readdata);

      FileWriter myWriter = new FileWriter("menu.txt");
      myWriter.write(readdata);
      myWriter.close();

    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

}

// public static void deleteInventori(String delete, InventoriItems[] inv, int
// jumlahbaru) {
// try {
// System.out.println("--------------------");
// File myObj = new File("inventori.txt");
// Scanner myReader = new Scanner(myObj);
// String readdata = "";
// int idgen = 0;

// for (int i = 0; i < inv.length; i++) {
// if (inv[i].getName().equals(delete)) {
// inv[i].setStock(jumlahbaru);
// }
// }

// for (int i = 0; i < inv.length; i++) {
// if (inv[i].getName().isEmpty()) {
// readdata += inv[i].forData();
// }
// }

// while (myReader.hasNextLine()) {
// if (myReader.nextLine().contains("pisang")) {
// readdata += "\n";
// } else {
// readdata += myReader.nextLine() + "\n";
// }

// idgen++;
// }

// // System.out.print(readdata);

// FileWriter myWriter = new FileWriter("inventori.txt");
// myWriter.write(readdata);
// myWriter.close();

// } catch (FileNotFoundException e) {
// System.out.println("An error occurred.");
// e.printStackTrace();
// } catch (IOException e) {
// System.out.println("An error occurred.");
// e.printStackTrace();
// }
// }
// }