import java.net.*;
import java.util.Properties;
import java.util.Scanner;
import java.io.*;

public class MyPelanggan {
  private static Socket s;

  public MyPelanggan(String args, int flag) {
    openConnection(args);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    MyPelanggan mc = new MyPelanggan("/home/ahmad/DANA/belajarbareng/ServerClientData/src/config.properties", 1);

    int flag = 0;
    while (flag == 0) {
      String input = in.nextLine();

      if (input.equals("1")) {
        // newPelanggan();
      }
      if (input.equals("2")) {

        System.out.println("ID,Pesanan,Harga?");
        String sendchat = in.nextLine();

        if (sendchat.equals("exit")) {
          mc.sendchat(sendchat);
          flag = 1;
        }
        mc.sendchat(sendchat);
      }
      if (input.equals("3")) {
        // querypoin;
      }

    }
  }

  public static void getPoin(int idpel) {

  }

  public static void openConnection(String args) {
    try {
      Properties prop = new Properties();
      InputStream input = null;
      input = new FileInputStream(args);
      // load a properties file
      prop.load(input);
      Integer socket = Integer.parseInt(prop.getProperty("port"));
      String server = prop.getProperty("server");
      s = new Socket(server, socket);
      System.out.println("Connection Established");
    } catch (Exception e) {
      System.out.println(e);
      System.out.println("Make sure you have run MyServer.java");
    }
  }

  public static void sendchat(String args) {
    try {
      DataOutputStream dout = new DataOutputStream(s.getOutputStream());

      if (args.equalsIgnoreCase("exit")) {
        dout.close();
        s.close();
        System.out.println("My Server Terminated. Be sure to turn MyServer on before next connection :D");
      } else {
        String read = args;
        dout.writeUTF(read);
        dout.flush();
        System.out.println("Cek my Server.java for what you sent :D");
      }

    } catch (Exception e) {
      System.out.println(e);
      System.out.println("Make sure you have run MyServer.java");
    }
  }
}