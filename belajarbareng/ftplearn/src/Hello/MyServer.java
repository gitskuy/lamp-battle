package Hello;
import java.io.FileInputStream;
import java.net.*;
import java.util.Scanner;
import java.io.*;
import java.util.Properties;

public class MyServer {

    public static void main(String[] args) {
        readinput(args);
    }

    private static void readinput(String[] args) {
        try {
            Properties prop = new Properties();
            InputStream input = null;
            input = new FileInputStream(args[0]);
            // load a properties file
            prop.load(input);
            String socket = prop.getProperty("port");
            ServerSocket ss = new ServerSocket(Integer.parseInt(socket));
            Socket s = ss.accept();
            DataInputStream dis = new DataInputStream(s.getInputStream());

            int flag = 0;
            while (flag == 0) {
                String str = (String) dis.readUTF();
                if (str.equals("exit")) {
                    flag = 1;
                } else {
                    System.out.println("Client says: " + str);
                }
            }
            ss.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}