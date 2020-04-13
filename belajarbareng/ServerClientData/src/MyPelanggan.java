import java.net.*;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.io.*;

public class MyPelanggan {
    private static Socket s;

    public MyPelanggan(String args, int flag) {
        openConnection(args);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner in = new Scanner(System.in);
        MyPelanggan mc = new MyPelanggan("/home/ahmad/DANA/belajarbareng/ServerClientData/src/config.properties", 1);

        int flag = 0;
        while (flag == 0) {
            printMenu();
            String input = in.nextLine();
            if (input.equals("1")) {
                newPelanggan(in);
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
                getPoin(in);
            }

        }
    }

    private static void printMenu() {
        System.out.println("--------------------------");
        System.out.println("1. Add New Pelanggan");
        System.out.println("2. Pesan Makan");
        System.out.println("3. Query Poin");
        System.out.println("--------------------------");
    }

    public static void getPoin(Scanner in) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
        //here sonoo is the database name, root is the username and root is the password
        Statement stmt = con.createStatement();

        System.out.println("Pelangan ID?");
        String id = in.nextLine();

        ResultSet rs = stmt.executeQuery("select * from transactions where customer_id =" + id);

        // ngambil nama dari pelangan
        System.out.println("-----------------------------");
        rs = stmt.executeQuery("select count(*) * 10 from transactions where customer_id =" + id);
        rs.next();
        String poin = rs.getString(1);
        System.out.println("Total Poin: " + poin);
        System.out.println("-----------------------------");
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
            System.out.println("Make sure you have run MyCashier.java");
        }
    }

    public static void newPelanggan(Scanner in) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
        //here sonoo is the database name, root is the username and root is the password
        Statement stmt = con.createStatement();

        System.out.println("ID");
        int id = Integer.parseInt(in.nextLine());
        System.out.println("Name?");
        String name = in.nextLine();
        System.out.println("Age?");
        int age = Integer.parseInt(in.nextLine());

        int querry = stmt.executeUpdate("insert into pelangan(customer_id,customer_name,age) values ("+id+",\""+name+"\","+age+");");

        if(querry == 1){
            System.out.println(name + " Has Been Added To the Database");
            System.out.println("Table Updated");
        }else{
            System.out.println("Transaction failed please redo ");
        }
    }

    public static void sendchat(String args) {
        try {
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            if (args.equalsIgnoreCase("exit")) {
                dout.close();
                s.close();
                System.out.println("My Cashier Terminated. Be sure to turn  on before next connection :D");
            } else {
                String read = args;
                dout.writeUTF(read);
                dout.flush();
                System.out.println("Cek my MyCashier.java for what you sent :D");
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Make sure you have run MyCashier.java");
        }
    }
}