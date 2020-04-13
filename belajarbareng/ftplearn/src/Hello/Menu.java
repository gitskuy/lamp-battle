package Hello;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {
    private static MyClient mc;
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
                mc = new MyClient(pathconfig,1);
            }else if(input == 2){
                System.out.println("What would you like to send?");
                String sendchat = in.nextLine();
                mc.sendchat(sendchat);
            }else if(input == 3){
                mc.sendchat("exit");
            }else if(input == 4){
                connectFTP(in, pathconfig);
                continue;
            }else if(input == 5){
                downloadFTP(in);
            }
            else if(input == 6){
                uploadFTP(in);
            }
            else if(input == 7){
                disconnectFTP();
            }
            else if(input == 8){
                flag = exit();
            }
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

    public static void uploadFTP(Scanner in) throws Exception {
        System.out.print("What would you like to upload?:");
        String upload = in.nextLine();
        ftpup.uploadFile("/home/ahmad/DANA/UPLOAD/" + upload, upload, "/AhmadYazid/");
        System.out.println("FTP File Uploaded successfully");
    }

    public static void downloadFTP(Scanner in) {
        System.out.print("What would you like to download?:");
        String send = in.nextLine();
        ftpdown.downloadFile(send, "/home/ahmad/DANA/DOWNLOAD/" + send);
        System.out.println("FTP File downloaded successfully");
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
        System.out.println("1. Connect to Socket Menu");
        System.out.println("2. Send data to Socket Server");
        System.out.println("3. Disconnect from Socket Server");
        System.out.println("4. Connect to FTP Server");
        System.out.println("5. Download From FTP Server");
        System.out.println("6. Upload From FTP Server");
        System.out.println("7. Disconnect FTP Server");
        System.out.println("8. Exit");
        System.out.println("-----------------------------------");
    }
}
