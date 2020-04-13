import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle
import java.util.Date;
import java.text.SimpleDateFormat;

public class Person implements Runnable {
  private String pesanan;
  private String gender;
  private Date d = new Date();

  public void run() {
    try {
      for (int i = 0; i <= 5; i++) {
        if (i == 0) {
          Date d = new Date();
          SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
          System.out.println("Initialization Time for" + " task name - " + toString() + " = " + ft.format(d));
          // prints the initialization time for every task
        } else {
          Date d = new Date();
          SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
          System.out.println("Executing Time for task name - " + toString() + " = " + ft.format(d));
          // prints the execution time for every task
        }
        Thread.sleep(1000);
      }
      System.out.println(pesanan + " complete");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Person [d=" + d + ", gender=" + gender + ", pesanan=" + pesanan + "]");
  }

  public String getPesanan() {
    return pesanan;
  }

  public void setPesanan(String pesanan) {
    this.pesanan = pesanan;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Date getD() {
    return d;
  }

  public void setD(Date d) {
    this.d = d;
  }

  public Person(String pesanan, String gender, Date d) {
    this.pesanan = pesanan;
    this.gender = gender;
    this.d = d;
  }

  @Override
  public String toString() {
    return "Person [d=" + d + ", gender=" + gender + ", pesanan=" + pesanan + "]";
  }

}