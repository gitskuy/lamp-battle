import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MyStaff {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int id;

    public String getLaporan() {
        return laporan;
    }

    public void setLaporan(String laporan) {
        this.laporan = laporan;
    }

    public String laporan;


    public static void main(String[] args) throws Exception {
        MyStaff staff = new MyStaff();

        Scanner in = new Scanner(System.in);

        String date = getDate();
        System.out.println(date);

        String time = getTime();
        System.out.println(time);

        System.out.print("Username:");
        String username = in.nextLine();
        System.out.print("Password:");
        String password = in.nextLine();

        String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{6,}$";
        String regexEmail = "^(.+)@(.+)$";

        if (Pattern.matches(regexEmail, username) && Pattern.matches(regexPassword, password) ) {
                login(username,password,staff);
                System.out.println("Welcome " + username + " to MyStaff hrd solutions");
                System.out.println("You last loged in at" + "placeholder" );

                int Flag = 0;

                while(Flag == 0){
                    System.out.println("1. Absen");
                    System.out.println("2. Buat Laporan");
                    System.out.println("3. Kirim Laporan");
                    System.out.println("4. Slip Gaji");
                    System.out.println("5. Exit");
                    Integer pilihan = Integer.parseInt(in.nextLine());
                    if(pilihan == 1){
                        String toSend = staff.getId() + "," + getTime() + "," + getDate();
                        sendAbsen("/home/ahmad/DANA/belajarbareng/ServerClientData/src/config.properties",toSend);

                    }if(pilihan == 2){
                        buatLaporan(staff,in);
                    }if(pilihan == 3){
                        kirimLaporan(staff);
                    }if(pilihan == 4){
                        slipgaji(staff);
                    }if(pilihan == 5){
                        Flag = 1;
                    }
                }



        } else {
            System.out.println("Username or password does not meet our standards");
            System.out.println("Password  has a minimum of 6 char with special and one capital character");
            System.out.println("Username must be in an email template");

        }
    }
    public static void slipgaji(MyStaff staff) throws SQLException {

        String id = "" + staff.getId();
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
        //here sonoo is the database name, root is the username and root is the password
        Statement stmt = con.createStatement();




        ResultSet rs = stmt.executeQuery("SET sql_mode = ''");

        rs = stmt.executeQuery("select e.e_name,((count(a.tanggal) / 20) * e.salary)_ from absen a,employee e where e.e_id = "+id+";");

        System.out.println("-----------------------------");


        if(rs.next()){
            String name = rs.getString(1);
            Integer salary = rs.getInt(2);
            System.out.println("Gaji  " + name + " Sampai sekarang " + salary);
        }else{
            System.out.println("Username or password not found");
        }
        System.out.println("-----------------------------");
    }
    public static void buatLaporan(MyStaff staff,Scanner in) throws IOException {
        String filename = "laporan " + staff.getName() + " tanggal " + getDate() + ".txt";
        System.out.println("Pekerjaan Hari ini?");
        String isilaporan = staff.getId() + "/" + getDate() + "/" + "Hari ini " + staff.getName() + " " + in.nextLine();
        FileWriter myWriter = new FileWriter("/home/ahmad/DANA/" + filename);
        myWriter.write(isilaporan);
        myWriter.close();
        staff.setLaporan(filename);
    }

    public static void kirimLaporan(MyStaff staff) throws Exception {
        String pathconfig = "/home/ahmad/DANA/belajarbareng/ftplearn/src/Hello/config.properties";
        Properties prop = new Properties();
        InputStream inputb = null;
        inputb = new FileInputStream(pathconfig);
        prop.load(inputb);
        FTPUploader ftpup = new FTPUploader(prop.getProperty("ftpserver"),"ftpuser@myth.co.id","P@ssw0rd12345");
        ftpup.uploadFile("/home/ahmad/DANA/" + staff.getLaporan(), staff.getLaporan(), "/AhmadYazid/");
        ftpup.disconnect();
    }

    public static void login(String username,String password,MyStaff staff) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
        //here sonoo is the database name, root is the username and root is the password
        Statement stmt = con.createStatement();



        ResultSet rs = stmt.executeQuery("select e_name,e_id from employee where e_username= \""+username+"\" AND e_password=\""+password+"\";");




        System.out.println("-----------------------------");


        if(rs.next()){
            String name = rs.getString(1);
            Integer id = rs.getInt(2);
            staff.setName(name);
            staff.setId(id);
            stmt.executeUpdate("insert into sistemlog(e_id,tanggal,jam) values ("+rs.getString(2)+",\""+getDate()+"\","+getTime()+");");
            System.out.println("Welcome " + name);


        }else{
            System.out.println("Username or password not found");
        }

    }
    private static String getTime() {
        String timepat = " HHmmss";
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(timepat);
        return simpleTimeFormat.format(new Date());
    }
    private static String getDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }



    public static void sendAbsen(String args,String absen) {
        try {
            Properties prop = new Properties();
            InputStream input = null;
            input = new FileInputStream(args);
            // load a properties file
            prop.load(input);
            Integer socket = Integer.parseInt(prop.getProperty("port"));
            String server = prop.getProperty("server");
            Socket s = new Socket(server, socket);
            System.out.println("Connection Established");
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            if (args.equalsIgnoreCase("exit")) {
                dout.close();
                s.close();
                System.out.println("My Cashier Terminated. Be sure to turn  on before next connection :D");
            } else {
                String read = absen;
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
