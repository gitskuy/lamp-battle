import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.net.*;
import java.sql.*;
import java.util.Scanner;
import java.io.*;
import java.util.Properties;

public class MenuServer {


    public static void main(String[] args) {
        System.out.println("active");

        System.out.println("Press Control X to Exit");
        readinput("config.properties");
    }

    private static String sendchat(String args, String read) {
        try {
            Properties prop = new Properties();
            InputStream input = null;
            input = new FileInputStream(args);
            // load a properties file
            prop.load(input);
            Integer socket = Integer.parseInt("8888");
            String server = prop.getProperty("server");
            Socket s = new Socket(server, socket);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            Scanner in = new Scanner(System.in);
            System.out.println("Sending data");
            int flag = 0;
            while (flag == 0) {

                if (read.equalsIgnoreCase("exit")) {

                    flag = 1;
                }
                dout.writeUTF(read);
                dout.flush();
                return "Verivied";
            }

            dout.close();
            s.close();
            return "Finished";
        } catch (Exception e) {
            System.out.println(e);
            return "Error";
        }
    }

    private static void readinput(String args) {
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
                    System.out.println("It is your turn to talk :D");
                    flag = 1;
                } else {
                    System.out.println("Client says: " + str);
                    System.out.println(MenuServer(str));
                }
            }
            ss.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String MenuServer(String input) throws ParseException, SQLException, ClassNotFoundException {
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(input);

        JSONArray array = new JSONArray();
        array.add(obj);

        JSONObject obj2 = (JSONObject)array.get(0);

        if(obj2.get("type").toString().equalsIgnoreCase("login")){
            System.out.println("Info");
            System.out.println(obj2.get("password"));
            System.out.println(obj2.get("username"));
            login(obj2.get("username").toString(),obj2.get("password").toString());
            return "Finished";
        }if(obj2.get("type").toString().equalsIgnoreCase("readmenu")){
            System.out.println("Info");
            System.out.println(obj2.get("type"));
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
            //here sonoo is the database name, root is the username and root is the password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from menu");

            System.out.println("-----------------------------");

            String mene = "-----Menu----\n";
            while(rs.next()){
                String name = rs.getString(2);
                String price = rs.getString(3);
                mene += "Nama " + name + " Price: " + price + "\n";

            }
            System.out.println(sendchat("config.properties",mene));
            return "Finished";
        }if(obj2.get("type").toString().equalsIgnoreCase("pesanmakan")){
            System.out.println("Info");
            System.out.println(obj2.get("pesanan"));
            System.out.println(obj2.get("name"));
            System.out.println(obj2.get("harga"));



            int id = 1;

            String pesanan = obj2.get("pesanan").toString();

            String harga = obj2.get("harga").toString();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
            //here sonoo is the database name, root is the username and root is the password
            Statement stmt = con.createStatement();


            int querry =  stmt.executeUpdate("insert into transactions(customer_id,item_name,price) values ("+id+",\""+pesanan+"\","+harga+");");

            if(querry == 1){
                System.out.println(sendchat("config.properties","Pesanan Berhasil"));
                System.out.println("Telah Menambahkan pesanan");
                System.out.println("Table Updated");
                return "Pesanan berhasil";
            }else{
                System.out.println("Transaction failed please redo ");
                return "Pesanan Error";
            }


        }

        return " Not in the menu";


    }
    public static void login(String username,String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
        //here sonoo is the database name, root is the username and root is the password
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select e_name,e_id from employee where e_username= \""+username+"\" AND e_password=\""+password+"\";");

        System.out.println("-----------------------------");

        if(rs.next()){
            String name = rs.getString(1);
            System.out.println("Welcome " + name);
            System.out.println(sendchat("config.properties",name + " berhasil login."));
        }else{
            System.out.println("Username or password not found");
        }

    }
}
