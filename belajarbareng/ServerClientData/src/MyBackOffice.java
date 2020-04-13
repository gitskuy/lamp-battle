import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MyBackOffice {

    private static FTPUploader ftpup;
    private static FTPDownloader ftpdown;

    public static void main(String[] args) throws Exception {
        menu();
    }

    public static void menu() throws Exception {

        int flag = 0;
        while(flag == 0){
            printMenu();
            Scanner in = new Scanner(System.in);
            int input = Integer.parseInt(in.nextLine());
            String pathconfig = "/home/ahmad/DANA/belajarbareng/ftplearn/src/Hello/config.properties";
            if(input == 1){
                connectFTP(in, pathconfig);
                continue;
            }else if(input == 2){
                downloadFTP(in);

            }
            else if(input == 3){
                disconnectFTP();
            }
            else if(input == 4){
                flag = exit();
            }
        }
    }

    private static void showData() throws FileNotFoundException {
        File myObj = new File("/home/ahmad/DANA/DOWNLOAD/AhmadYazid/datatransaksi.txt" );

        Scanner myReader = new Scanner(myObj);
        String readdata = "";
        while (myReader.hasNextLine()) {
            System.out.println(myReader.nextLine());
        }
    }

    public static int exit() {
        int flag;
        System.out.println("Thank you for coming :D");
        System.out.println("Program Terminated");
        flag = 1;
        return flag;
    }

    public static void disconnectFTP() {
        ftpup.disconnect();
        ftpdown.disconnect();
    }



    public static void downloadFTP(Scanner in) throws FileNotFoundException {
        String send = "/AhmadYazid/datatransaksi.txt";
        ftpdown.downloadFile(send, "/home/ahmad/DANA/DOWNLOAD/" + send);
        System.out.println("FTP File downloaded successfully");
        showData();
    }

    public static void connectFTP(Scanner in, String pathconfig) throws Exception {
        System.out.print("Username:");
        String username = in.nextLine();
        System.out.print("Password:");
        String password = in.nextLine();

        String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{6,}$";
        String regexEmail = "^(.+)@(.+)$";

        // System.out.println(Pattern.matches(regexEmail, username));
        // System.out.println(Pattern.matches(regexPassword, password));

        if (Pattern.matches(regexEmail, username) && Pattern.matches(regexPassword, password) ) {
            System.out.println("Validated username");
            Properties prop = new Properties();
            InputStream inputb = null;
            inputb = new FileInputStream(pathconfig);
            prop.load(inputb);
            ftpup = new FTPUploader(prop.getProperty("ftpserver"),username,password);
            ftpdown = new FTPDownloader(prop.getProperty("ftpserver"),username,password);
            return;
        } else {
            System.out.println("Username or password does not meet our standards");
            System.out.println("Password  has a minimum of 6 char with special and one capital character");
            System.out.println("Username must be in an email template");
            return;
        }
    }

    public static void printMenu() {
        System.out.println("-----------------------------------");
        System.out.println("1. Connect to FTP");
        System.out.println("2. Get & Show Data");
        System.out.println("3. Disconnect from ftp");
        System.out.println("4. Exit");
        System.out.println("-----------------------------------");
    }
}
