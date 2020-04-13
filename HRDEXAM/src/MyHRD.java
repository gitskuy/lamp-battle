import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MyHRD {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);


        System.out.print("Username:");
        String username = in.nextLine();
        System.out.print("Password:");
        String password = in.nextLine();

        String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{6,}$";
        String regexEmail = "^(.+)@(.+)$";

        if (Pattern.matches(regexEmail, username) && Pattern.matches(regexPassword, password) ) {
            System.out.println("Welcome " + username + " to MyStaff hrd solutions");
            System.out.println("You last loged in at" + "placeholder" );

            int Flag = 0;

            while(Flag == 0){
                System.out.println("1. Proses Laporan");
                System.out.println("2. Proses Pengajian");
                System.out.println("3. Exit");

                Integer pilihan = Integer.parseInt(in.nextLine());
                if(pilihan == 1){
                    prosesLaporan(in);
                }if(pilihan == 2){
                    prosesPengajian(in);
                }if(pilihan == 3){
                    Flag = 1;
                }if(pilihan == 4){

                }if(pilihan == 5){
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
    public static void prosesPengajian(Scanner in) throws ClassNotFoundException, SQLException {
        System.out.println("Employee Id?");
        String id = in.nextLine();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
        //here sonoo is the database name, root is the username and root is the password
        Statement stmt = con.createStatement();




        ResultSet rs = stmt.executeQuery("SET sql_mode = ''");

        rs = stmt.executeQuery("select e.e_name,(count(a.tanggal) / 20) * e.salary from absen a,employee e where e.e_id = "+id+";");

        System.out.println("-----------------------------");


        if(rs.next()){
            String name = rs.getString(1);
            Integer salary = rs.getInt(2);
            System.out.println("Gaji  " + name + " Sampai sekarang " + salary);
        }else{
            System.out.println("Username or password not found");
        }

    }
    public static void prosesLaporan(Scanner in) throws Exception {
        String pathconfig = "/home/ahmad/DANA/belajarbareng/ftplearn/src/Hello/config.properties";
        Properties prop = new Properties();
        InputStream inputb = null;
        inputb = new FileInputStream(pathconfig);
        prop.load(inputb);
        FTPDownloader ftpDownloader =
                new FTPDownloader(prop.getProperty("ftpserver"),"ftpuser@myth.co.id","P@ssw0rd12345");
        System.out.println("Proses laporan siapa?");
        String nama = in.nextLine();
        System.out.println("Tanggal Berapa?)(format yyyy-mm-dd)");
        String tanggal = in.nextLine();

        String send = "/AhmadYazid/" + "laporan " + nama + " tanggal " + tanggal + ".txt";
        ftpDownloader.downloadFile(send, "/home/ahmad/DANA/DOWNLOAD/" + send);
        System.out.println("FTP File downloaded successfully");
        showData(send);
    }
    private static void showData(String send) throws FileNotFoundException, ClassNotFoundException, SQLException {

        File myObj = new File("/home/ahmad/DANA/DOWNLOAD/" + send);

        Scanner myReader = new Scanner(myObj);
        String readdata = "";
        while (myReader.hasNextLine()) {
            readdata += myReader.nextLine();
        }
        System.out.println(readdata);



        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
        //here sonoo is the database name, root is the username and root is the password
        Statement stmt = con.createStatement();

        String split[] = readdata.split("/");

        stmt.executeUpdate("insert into laporan(e_id,tanggal,pekerjaan) values ("+split[0]+",\""+split[1]+"\",\""+split[2]+"\");");
        System.out.println("Data berhasil di masukan pada database !");

    }
}
