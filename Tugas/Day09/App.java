import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class App {
  public static void main(String[] args) {
    Properties prop = new Properties();
    OutputStream output = null;
    try {
      output = new FileOutputStream("config.properties");
      prop.setProperty("server", "localhost");
      // set the properties value
      prop.setProperty("port", "666");
      // save properties to project root folder
      prop.store(output, null);

    } catch (IOException io) {
      io.printStackTrace();
    } finally {
      if (output != null) {
        try {
          output.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
