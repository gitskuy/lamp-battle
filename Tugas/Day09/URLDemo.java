import java.io.*;
import java.net.*;

public class URLDemo {
  public static void main(String[] args) {
    try {
      URL url = new URL("http://www.javatpoint.com/java-tutorial");
      URL url2 = new URL("http://myth.co.id/portfolio.php");

      System.out.println("Protocol: " + url.getProtocol());
      System.out.println("Host Name: " + url.getHost());
      System.out.println("Port Number: " + url.getPort());
      System.out.println("File Name: " + url.getFile());

      URLConnection urlcon = url.openConnection();
      InputStream stream = urlcon.getInputStream();
      int i;
      while ((i = stream.read()) != -1) {
        System.out.print((char) i);
      }

      HttpURLConnection huc = (HttpURLConnection) url2.openConnection();
      for (int x = 1; x < 8; x++) {
        System.out.println(huc.getHeaderFieldKey(x) + " = " + huc.getHeaderField(x));
      }
      huc.disconnect();

      InetAddress ip = InetAddress.getByName("www.javatpoint.com");
      System.out.println("Host Name: " + ip.getHostName());
      System.out.println("IP Address: " + ip.getHostAddress());

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
