import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class MyServer {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {

        // Create two threads:
        Thread thread1 = new Thread() {
            public void run() {
                Scanner in = new Scanner(System.in);
                int flag = 0;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
                    //here sonoo is the database name, root is the username and root is the password
                    Statement stmt = con.createStatement();
                    System.out.println("MyServer reading attendance in the background");
                    readinput("/home/ahmad/DANA/belajarbareng/ServerClientData/src/config.properties",stmt);
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2 = new Thread() {
            public void run() {
                Scanner in = new Scanner(System.in);
                int flag = 0;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
                    //here sonoo is the database name, root is the username and root is the password
                    Statement stmt = con.createStatement();
                    while(flag == 0){
                        System.out.println("=================================");
                        System.out.println("1.Close Connection");
                        System.out.println("2.Exit");
                        System.out.println("=================================");
                        Integer input = Integer.parseInt(in.nextLine());
                        if(input == 1){
                            show(stmt,in);
                        }
                        if(input == 2){
                            flag = 1;
                        }

                    }
                } catch (ClassNotFoundException | SQLException  e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

// Start the downloads.
        thread1.start();
        thread2.start();

// Wait for them both to finish
        thread1.join();
        thread2.join();

    }

    private static void readinput(String args,Statement stmt) {
        try {
            Properties prop = new Properties();
            InputStream input = null;
            input = new FileInputStream(args);
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
                    insertData(str,stmt);
                }
            }
            ss.close();

        } catch (Exception e) {
            System.out.println(e);

        }
    }
    private static void insertData(String fromclient,Statement stmt) throws SQLException {
        String[] fc = fromclient.split(",");

        System.out.println("ID");
        int id = Integer.parseInt(fc[0]);
        System.out.println("Name?");
        String time = fc[1];
        System.out.println("Age?");
        String date = fc[2];



        int querry =  stmt.executeUpdate("insert into absen(e_id,tanggal,jam) values ("+id+",\""+date+"\","+time+");");

        if(querry == 1){
            System.out.println("Absen Recorded!");
            System.out.println("Table Updated");
        }else{
            System.out.println("Transaction failed please redo ");
        }

    }
    private static void show(Statement stmt,Scanner in) throws SQLException, IOException {
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("/home/ahmad/DANA/belajarbareng/ServerClientData/src/config.properties");
        // load a properties file
        prop.load(input);
        Integer socket = Integer.parseInt(prop.getProperty("port"));
        String server = prop.getProperty("server");
        Socket s = new Socket(server, socket);
        System.out.println("Closing Connection");
        System.out.println("Choose Exit to Terminate Program");
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        String read = "exit";
        dout.writeUTF(read);
        dout.flush();
        dout.close();
        s.close();
    }

}