import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class MyCashier {
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
                    System.out.println("MyCashier is accepting orders in the background ");
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
                        System.out.println("1.Print Struct");
                        System.out.println("2.Send Data to Back Office");
                        System.out.println("3.Exit");
                        System.out.println("=================================");
                        Integer input = Integer.parseInt(in.nextLine());
                       if(input == 1){
                            show(stmt,in);
                        }
                        if(input == 2){
                            forBO(stmt,in);
                        }
                       if(input == 3){
                            flag = 1;
                        }
                    }
                } catch (ClassNotFoundException | SQLException | IOException e) {
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
                    System.out.println("Pesanan made: " + str);
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
        String name = fc[1];
        System.out.println("Age?");
        int age = Integer.parseInt(fc[2]);

        int querry = stmt.executeUpdate("insert into transactions(customer_id,item_name,price) values ("+id+",\""+name+"\","+age+");");

        if(querry == 1){
            System.out.println("Someone with the id of "+ id + " Has Made a new Transaction and has been Succesfully Inserted to Table");
            System.out.println("Table Updated");
        }else{
            System.out.println("Transaction failed please redo ");
        }

    }
    private static void show(Statement stmt,Scanner in) throws SQLException {

        System.out.println("Pelangan ID?");
        String id = in.nextLine();

        ResultSet rs = stmt.executeQuery("select * from transactions where customer_id =" + id);

        // ngambil nama dari pelangan
        System.out.println("-----------------------------");
        rs = stmt.executeQuery("select customer_name from pelangan where customer_id =" + id);
        rs.next();
        String cname = rs.getString(1);
        System.out.println("Customer Name: " + cname);
        System.out.println("-----------------------------");

        // ngambil semua transaksi
        rs = stmt.executeQuery("select * from transactions where customer_id =" + id);
        while(rs.next())
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

        System.out.println("-----------------------------");

        //ngitung total
        rs = stmt.executeQuery("select sum(price) from transactions where customer_id =" + id);
        rs.next();
        String total = rs.getString(1);
        System.out.println("Total: " + total);

        System.out.println("-----------------------------");
    }

    private static void forBO(Statement stmt,Scanner in) throws Exception {
        ResultSet rs = stmt.executeQuery("SET sql_mode = '' ");

        rs = stmt.executeQuery("select p.customer_id as ID,p.customer_name as Name,p.age,sum(t.price) as Total,count(*) * 10 as Point from pelangan p,transactions t where p.customer_id = t.customer_id group by p.age order by p.customer_id;");

        System.out.println("|+----+----------+------+-------+-------+\n" +
                "| ID | Name     | age  | Total | Point |\n" +
                "+----+----------+------+-------+-------+");
        //System.out.println("|  "+rs.getInt(1)+" | "+rs.getString(2)+" |   "+rs.getString(3) + " | "+ rs.getString(4) +" |    "+ rs.getString(5)+" |\n");

        String forOutput = "|+----+----------+------+-------+-------+\n" +
                "| ID | Name     | age  | Total | Point |\n" +
                "+----+----------+------+-------+-------+\n";



        while(rs.next()){
            System.out.print("|  "+rs.getInt(1)+" | "+rs.getString(2)+" |   "+rs.getString(3) + " | "+ rs.getString(4) +" |    "+ rs.getString(5)+" |\n");
            forOutput += "|  "+rs.getInt(1)+" | "+rs.getString(2)+" |   "+rs.getString(3) + " | "+ rs.getString(4) +" |    "+ rs.getString(5)+" |\n";
        }

        FileWriter myWriter = new FileWriter("/home/ahmad/DANA/datatransaksi.txt");
        myWriter.write(forOutput);
        myWriter.close();

        System.out.println("-----------------------------");

        FTPUploader ftpUploader = new FTPUploader("ftp.myth.co.id", " ftpuser@myth.co.id", "P@ssw0rd12345");
        ftpUploader.uploadFile("/home/ahmad/DANA/datatransaksi.txt", "datatransaksi.txt", "/AhmadYazid/");
        System.out.println("FTP File Uploaded successfully");

        ftpUploader.disconnect();
    }
}