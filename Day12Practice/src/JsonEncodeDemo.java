import org.json.simple.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class JsonEncodeDemo {
    public static void main(String[] args) throws IOException {

        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("config.properties");
        // load a properties file
        prop.load(input);
        Integer socket = Integer.parseInt(prop.getProperty("port"));
        String server = prop.getProperty("server");
        Socket s = new Socket(server, socket);
        System.out.println("Connection Established");


        DataOutputStream dout = new DataOutputStream(s.getOutputStream());


        JSONObject obj = new JSONObject();
        obj.put("name", "Jhon");
        obj.put("Age", 31);
        obj.put("City", "New York");
        System.out.print(obj);
        System.out.println("Reciept has been printed :D");
        FileWriter myWriter = new FileWriter("data.json");
        myWriter.write(obj.toString());
        myWriter.close();
        dout.writeUTF(obj.toString());
        dout.flush();
        System.out.println("Cek my Server.java for what you sent :D");
        dout.close();
        s.close();






    }
}