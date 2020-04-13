import org.json.simple.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MenuRestaurant {
    private static Socket s;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread() {
            public void run() {
                readinput("config.properties");
            }
        };

        Thread thread2 = new Thread() {
            public void run() {
                openConnection("config.properties");
                Scanner in = new Scanner(System.in);







                    System.out.println("-----Login-------");
                    System.out.print("Username:");
                    String username = in.nextLine();
                    System.out.print("Password:");
                    String password = in.nextLine();


            String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{6,}$";
            String regexEmail = "^(.+)@(.+)$";

            if (Pattern.matches(regexEmail, username) && Pattern.matches(regexPassword, password) ) {
                JSONObject ld = new JSONObject();
                ld.put("type", "login");
                ld.put("username", username);
                ld.put("password", password);
                sendchat(ld.toString());

                int flag = 0;
                while (flag == 0) {

                    System.out.println("1.Lihat Menu");
                    System.out.println("2.Pesan Makanan");
                    System.out.println("3. Exit");


                    String sendchat = in.nextLine();
                    if (sendchat.equals("2")) {
                        System.out.println("----Pesan Makan ----");
                        System.out.print("Pesanan: ");
                        String pesanan = in.nextLine();
                        System.out.print("Harga:");
                        String harga = in.nextLine();
                        JSONObject ps = new JSONObject();
                        ps.put("type", "pesanmakan");
                        ps.put("name", username);
                        ps.put("harga", harga);
                        ps.put("pesanan", pesanan);
                        sendchat(ps.toString());
                    }
                    if (sendchat.equals("1")) {
                        JSONObject sm = new JSONObject();
                        sm.put("type", "readmenu");
                        sendchat(sm.toString());

                    }
                    if (sendchat.equals("3")) {
                        sendchat("exit");
                        flag = 1;
                    }
                }
            }else {
                System.out.println("Username or password does not meet our standards");
                System.out.println("Password  has a minimum of 6 char with special and one capital character");
                System.out.println("Username must be in an email template");

            }



                }

        };

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


    }
    private static void readinput(String args) {
        try {
            Properties prop = new Properties();
            InputStream input = null;
            input = new FileInputStream(args);
            // load a properties file
            prop.load(input);
            String socket = "8888";
            ServerSocket ss = new ServerSocket(Integer.parseInt(socket));
            Socket s = ss.accept();
            DataInputStream dis = new DataInputStream(s.getInputStream());

            int flag = 0;
            while (flag == 0) {
                String str = (String) dis.readUTF();
                if (str.equals("exit")) {
                    System.out.println("It is your turn to talk :D");
                    flag = 1;
                } else {
                    System.out.println("Server says: " + str);
                    ss.close();
                    readinput("config.properties");
                }
            }
            ss.close();

        } catch (Exception e) {
            System.out.println(e);
        }
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
