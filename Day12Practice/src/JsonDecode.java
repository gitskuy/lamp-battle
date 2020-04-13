import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Scanner;

public class JsonDecode {
    public static void main(String[] args){
        JSONParser parser = new JSONParser();

        try{
            Properties prop = new Properties();
            InputStream input = null;
            input = new FileInputStream(args[0]);
            // load a properties file
            prop.load(input);
            Integer socket = Integer.parseInt(prop.getProperty("port"));
            String server = prop.getProperty("server");
            Socket s = new Socket(server, socket);
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            Scanner in = new Scanner(System.in);

            int flag = 0;
            while (flag == 0) {
                String read = in.nextLine();
                if (read.equalsIgnoreCase("exit")) {

                    flag = 1;
                }
                dout.writeUTF(read);
                dout.flush();
            }

            dout.close();
            s.close();


            File myObj = new File("data.json");
            Scanner myReader = new Scanner(myObj);
            String readdata = "";
            while (myReader.hasNextLine()) {
                readdata += myReader.nextLine();
            }
            System.out.println(readdata);

            Object obj = parser.parse(readdata);

            JSONArray array = new JSONArray();
            array.add(obj);

            JSONObject obj2 = (JSONObject)array.get(0);
            System.out.println("Info");
            System.out.println(obj2.get("name"));
            System.out.println(obj2.get("Age"));
            System.out.println(obj2.get("City"));


        }catch(ParseException | FileNotFoundException pe){
            System.out.println("position: " + pe.getMessage());
            System.out.println(pe);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}