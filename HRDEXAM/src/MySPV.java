import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MySPV {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner in = new Scanner(System.in);

        System.out.print("Username:");
        String username = in.nextLine();
        System.out.print("Password:");
        String password = in.nextLine();

        String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{6,}$";
        String regexEmail = "^(.+)@(.+)$";

        if (Pattern.matches(regexEmail, username) && Pattern.matches(regexPassword, password) ) {
            System.out.println("Welcome " + username + " to MyStaff hrd solutions");


            int Flag = 0;

            while(Flag == 0){
                System.out.println("1. Cek Absen");
                System.out.println("2. Cek Laporan");
                System.out.println("3. Exit");
                Integer pilihan = Integer.parseInt(in.nextLine());
                if(pilihan == 1){
                    System.out.println("1. Cek All");
                    System.out.println("2. Cek One");
                    String pilihan1 = in.nextLine();
                    if(pilihan1.equalsIgnoreCase("1")){
                        cekabsen("1",in);
                    }if(pilihan1.equalsIgnoreCase("2")){
                        cekabsen("2",in);
                    }
                }if(pilihan == 2){
                    ceklaporan(in);
                }if(pilihan == 3){
                    System.out.println("Thank you for using MySpv");
                    Flag = 1;

                }
            }



        } else {
            System.out.println("Username or password does not meet our standards");
            System.out.println("Password  has a minimum of 6 char with special and one capital character");
            System.out.println("Username must be in an email template");
            return;
        }
    }
    public static void cekabsen(String pilihan,Scanner in) throws SQLException, ClassNotFoundException {
        if(pilihan.equalsIgnoreCase("1")){
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
            //here sonoo is the database name, root is the username and root is the password
            Statement stmt = con.createStatement();




            ResultSet rs = stmt.executeQuery("SET sql_mode = ''");

            rs = stmt.executeQuery("select * from absen;");

            System.out.println("-----Absensi All-----");


            while(rs.next()){
                String name = rs.getString(1);
                String tanggal = rs.getString(2);
                String jam = rs.getString(3);
                System.out.println("Name: " + name + " Date:" + tanggal + " Time:" + jam);
            }
        } if(pilihan.equalsIgnoreCase("2")){
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
            //here sonoo is the database name, root is the username and root is the password
            Statement stmt = con.createStatement();




            ResultSet rs = stmt.executeQuery("SET sql_mode = ''");
            System.out.println("ID EMPLOYEE?");
            rs = stmt.executeQuery("select * from absen where e_id="+in.nextLine()+";");

            System.out.println("-----Absensi Individu-----");


            while(rs.next()){
                String name = rs.getString(1);
                String tanggal = rs.getString(2);
                String jam = rs.getString(3);
                System.out.println("Name: " + name + " Date:" + tanggal + " Time:" + jam);
            }
        }
    }
    public static void ceklaporan(Scanner in) throws ClassNotFoundException, SQLException {
        System.out.println("1.Cek One");
        System.out.println("2.Cek All");
        Integer input = Integer.parseInt(in.nextLine());

        if(input == 1){
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
            //here sonoo is the database name, root is the username and root is the password
            Statement stmt = con.createStatement();




            ResultSet rs = stmt.executeQuery("SET sql_mode = ''");
            System.out.println("ID EMPLOYEE?");
            rs = stmt.executeQuery("select * from laporan where e_id="+in.nextLine()+";");

            System.out.println("-----Laporan Individu-----");


            while(rs.next()){
                String name = rs.getString(1);
                String tanggal = rs.getString(2);
                String jam = rs.getString(3);
                System.out.println("Name: " + name + " Date:" + tanggal + " Melakukan:" + jam);
            }
        }if(input == 2){
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
            //here sonoo is the database name, root is the username and root is the password
            Statement stmt = con.createStatement();




            ResultSet rs = stmt.executeQuery("SET sql_mode = ''");

            rs = stmt.executeQuery("select * from laporan;");

            System.out.println("-----Laporan  All-----");


            while(rs.next()){
                String name = rs.getString(1);
                String tanggal = rs.getString(2);
                String jam = rs.getString(3);
                System.out.println("Name: " + name + " Date:" + tanggal + " Melakukan:" + jam);
            }
        }
    }


}
