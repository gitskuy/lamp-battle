import java.util.regex.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class RegexExample {
  public static void main(String args[]) {
    try {
      validateUser(args);
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } finally {
      myReader.close();
      myReader2.close();
      in.close();
    }
  }

  private static void validateUser(String[] args) throws FileNotFoundException {
    String file1 = args[0];
    String file2 = args[1];

    File myObj = new File(file1);
    String userinput = "";
    Scanner myReader = new Scanner(myObj);
    while (myReader.hasNextLine()) {
      userinput = userinput + myReader.nextLine();

    }

    File myObj2 = new File(file2);
    Scanner myReader2 = new Scanner(myObj2);
    String userpassword = "";
    while (myReader2.hasNextLine()) {
      userpassword += myReader2.nextLine();

    }
    myReader2.close();

    Scanner in = new Scanner(System.in);
    System.out.print("Username:");
    String username = in.nextLine();
    System.out.print("Password:");
    String password = in.nextLine();

    String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{6,}$";
    String regexEmail = "^(.+)@(.+)$";

    // System.out.println(Pattern.matches(regexEmail, username));
    // System.out.println(Pattern.matches(regexPassword, password));

    if (Pattern.matches(regexEmail, username) && username.equals(userinput)) {
      System.out.println("Validated username");
    } else {
      System.out.println("There is something wrong with your username");
    }
    if (Pattern.matches(regexPassword, password) && password.equals(userpassword)) {
      System.out.println("Validated password");
    } else {
      System.out.println("There is something wrong with your password");
    }
  }
}
