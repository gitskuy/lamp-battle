import java.lang.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class MySqlCon{
    public static void main(String args[]){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","okeh1234");
            //here sonoo is the database name, root is the username and root is the password
            Statement stmt=con.createStatement();
            Scanner in = new Scanner(System.in);
            int flag = 0;
            while (flag == 0){
                printmenu();
                int input = Integer.parseInt(in.nextLine());
                if(input == 1){
                    insertData(stmt, in);
                }else if(input == 2){
                    updateData(con, stmt, in);
                }else if(input == 3){
                    deleteData(con, stmt, in);
                }else if(input == 4){
                    show(stmt);
                }else if(input == 5){
                    flag = exit();
                }
            }


            con.close();

        }catch(Exception e){ System.out.println(e);}

    }

    private static int exit() {
        int flag;
        System.out.println("Thank you for using my sql connection :D");
        flag = 1;
        return flag;
    }

    private static void deleteData(Connection con, Statement stmt, Scanner in) throws SQLException {
        System.out.println("Delete type (name,id,both)?");
        String choose = in.nextLine();

        if(choose.equals("name")){
            System.out.println("Delete Who?");
            String deletename = in.nextLine();


            String query = " delete from emp where name =  ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, deletename);
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            System.out.println(deletename + " Removed From Table!");
            show(stmt);
        }
        if(choose.equalsIgnoreCase("id")){
            System.out.println("Delete Which ID?");
            String deleteid = in.nextLine();

            String query = " delete from emp where id =  ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Integer.parseInt(deleteid));
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            System.out.println("ID:" + deleteid + " Removed From Table!");
            show(stmt);
        }
        if(choose.equalsIgnoreCase("both")){
            System.out.println("Delete Which ID?");
            String deleteid = in.nextLine();
            System.out.println("Delete What Name?");
            String deletename = in.nextLine();

            String query = " delete from emp where id =  ? and name = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, Integer.parseInt(deleteid));
            preparedStmt.setString(2, deletename);
            // execute the java preparedstatement

            int output = preparedStmt.executeUpdate();

            if(output == 1){
                System.out.println(deletename + " with id " + deleteid + " Removed From Table!");
                show(stmt);
            }else{
                System.out.println("------------------------------------------------------");
                System.out.println("There is something wrong with the daya you have given");
                System.out.println("Database Unchanged");
                System.out.println("------------------------------------------------------");
            }


        }
    }

    private static void updateData(Connection con, Statement stmt, Scanner in) throws SQLException {
        // create the java mysql update preparedstatement
        System.out.println("Update type (name,id)?");
        String choose = in.nextLine();

        if(choose.equals("name")){
            System.out.println("Old Name?");
            String oldname = in.nextLine();

            System.out.println("New Name?");
            String newname = in.nextLine();
            System.out.println("New Age?");
            String newAge = in.nextLine();

            String query = "update emp set name = ? , age = ? where name = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(3, oldname);
            preparedStmt.setString(1, newname);
            preparedStmt.setInt(2, Integer.parseInt(newAge));
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            System.out.println("Table Updated");
            show(stmt);
        }
        if(choose.equalsIgnoreCase("id")){
            System.out.println("Old ID?");
            String oldID = in.nextLine();
            System.out.println("New Name?");
            String newname = in.nextLine();
            System.out.println("New Age?");
            String newAge = in.nextLine();

            String query = "update emp set name = ? , age = ? where id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(3, Integer.parseInt(oldID));
            preparedStmt.setString(1, newname);
            preparedStmt.setInt(2, Integer.parseInt(newAge));
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            System.out.println("Table Updated");
            show(stmt);
        }
    }

    private static void insertData(Statement stmt, Scanner in) throws SQLException {
        System.out.println("ID");
        int id = Integer.parseInt(in.nextLine());
        System.out.println("Name?");
        String name = in.nextLine();
        System.out.println("Age?");
        int age = Integer.parseInt(in.nextLine());

        stmt.executeUpdate("insert into emp(id,name,age) values ("+id+",\""+name+"\","+age+");");
        System.out.println(name + " Has Been Succesfully Inserted to Table");
        System.out.println("Table Updated");
        show(stmt);
    }

    private static void printmenu() {
        System.out.println("-----------------------------");
        System.out.println("1. Insert ");
        System.out.println("2. Update ");
        System.out.println("3. Delete ");
        System.out.println("4. Show ");
        System.out.println("5. Exit ");
        System.out.println("-----------------------------");
    }

    private static void show(Statement stmt) throws SQLException {
        ResultSet rs=stmt.executeQuery("select * from emp");
        System.out.println("-----------------------------");
        while(rs.next())
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

        System.out.println("-----------------------------");
    }
}