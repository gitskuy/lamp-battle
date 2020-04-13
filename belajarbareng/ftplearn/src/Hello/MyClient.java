package Hello;
import java.net.*;
import java.util.Properties;
import java.util.Scanner;
import java.io.*;

public class MyClient {
    private static Socket s;

    public MyClient(String args,int flag){
       openConnection(args);
    }

    public static void openConnection(String args){
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
                }else{
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