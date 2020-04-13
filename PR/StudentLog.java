import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.*;

public class StudentLog {

      public static void main(String args[]) {
            try {

                  int flagLoop = 0;
                  int idGenerator = 0;
                  ArrayList<Student> studentList = new ArrayList<Student>();

                  int validateLoop = 0;

                  while (validateLoop != 2) {
                        validateLoop = validateUser(args);
                  }
                  ;

                  while (flagLoop == 0) {
                        Scanner in = new Scanner(System.in);
                        System.out.println("--------------------");
                        System.out.println("1.Create Student");
                        System.out.println("2.Show Student ");
                        System.out.println("3.Create Pelajaran");
                        System.out.println("4.Show Pelajaran");
                        System.out.println("5.EXIT");
                        System.out.println("--------------------");
                        int pointer = 0;

                        pointer = Integer.parseInt(in.nextLine());

                        if (pointer == 1) {
                              createStudent(idGenerator, studentList, in);
                        }
                        if (pointer == 2) {
                              showAllStudent(studentList);
                        }
                        if (pointer == 3) {
                              createPelajaran(idGenerator, studentList, in);
                        }
                        if (pointer == 4) {
                              showEachStudent(studentList, in);
                        }
                        if (pointer == 5) {
                              flagLoop = exit(flagLoop, in);
                        }
                        idGenerator++;
                        // sebuah generator id yang akan mengenerasi id.

                  }
            } catch (FileNotFoundException e) {
                  System.out.println("An error occurred.");
                  e.printStackTrace();
            } // catch number format exception
      }

      private static int validateUser(String[] args) throws FileNotFoundException {
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
            int outFlag = 0;

            if (Pattern.matches(regexEmail, username) && username.equals(userinput)) {
                  System.out.println("Validated username");
                  outFlag += 1;
            } else {
                  System.out.println("There is something wrong with your username");
            }
            if (Pattern.matches(regexPassword, password) && password.equals(userpassword)) {
                  System.out.println("Validated password");
                  outFlag += 1;
            } else {
                  System.out.println("There is something wrong with your password");
            }
            return outFlag;
      }

      private static int exit(int flagLoop, Scanner in) {
            System.out.println("--------------------");
            System.out.println("Are you sure? all data will be lost.(y/n)");
            System.out.println("--------------------");
            if (in.nextLine().equals("y")) {

                  System.out.println("--------------------");
                  System.out.println("Exiting, thank you");
                  System.out.println("--------------------");
                  in.close();
                  flagLoop = 1;
            }
            return flagLoop;
      }

      private static void showAllStudent(ArrayList<Student> studentList) {
            System.out.println("--------------------");
            System.out.println("Showing all student!");
            System.out.println("--------------------");
            Collections.sort(studentList);
            for (int i = 0; i < studentList.size(); i++) {
                  System.out.println("--------------------");
                  System.out.println(studentList.get(i).printDetails());
                  System.out.println("--------------------");
            }
      }

      private static void showEachStudent(ArrayList<Student> studentList, Scanner in) {
            System.out.println("--------------------");
            System.out.println("Showing Each student");
            System.out.println("--------------------");

            System.out.println("--------------------");
            System.out.println("Which Student?");
            String nameSearch = in.nextLine();
            for (int i = 0; i < studentList.size(); i++) {

                  if (studentList.get(i).getName().equals(nameSearch)) {
                        Collections.sort(studentList.get(i).getPelajaran());
                        System.out.println("--------------------");
                        System.out.println(studentList.get(i).printDetails());
                        System.out.println("--------------------");
                  }

            }

            System.out.println("--------------------");
      }

      private static void editPelajaran(ArrayList<Student> studentList, Scanner in) {
            System.out.println("--------------------");
            System.out.println("Edit Pelajaran");
            System.out.println("--------------------");

            System.out.println("--------------------");
            System.out.println("What is the Student Name?");
            String nameFind = in.nextLine();
            for (int i = 0; i < studentList.size(); i++) {
                  if (studentList.get(i).getName().equals(nameFind)) {

                        System.out.println(studentList.get(i).printDetails());

                        System.out.println("Edit pelajaran apa?");
                        String pelajaranSearch = in.nextLine();

                        System.out.println("Mau Diapain?");
                        System.out.print("1.Delete" + "\n" + "2.Ganti Nama" + "\n" + "3.Ganti Nilai" + "\n");

                        String option = in.nextLine();

                        if (option.equals("1")) {
                              studentList.get(i).deletePelajaran(pelajaranSearch);
                        }
                        if (option.equals("2")) {
                              System.out.println("Nama Baru?");
                              studentList.get(i).changeNamaPelajaran(pelajaranSearch, in.nextLine());
                        }
                        if (option.equals("3")) {
                              System.out.println("Nilai Baru?");
                              studentList.get(i).changeNilaiPelajaran(pelajaranSearch, Integer.parseInt(in.nextLine()));
                        }
                        System.out.println(studentList.get(i).printDetails());
                  }
            }

            System.out.println("--------------------");
      }

      private static void createPelajaran(int idGenerator, ArrayList<Student> studentList, Scanner in) {
            System.out.println("--------------------");
            System.out.println("Create Pelajaran");
            System.out.println("--------------------");

            System.out.println("--------------------");
            System.out.println("Create pelajaran untuk siapa?");
            String nameSearch = in.nextLine();

            for (int i = 0; i < studentList.size(); i++) {
                  if (studentList.get(i).getName().equals(nameSearch)) {
                        System.out.println("Nama pelajaran?");
                        String namePelajaran = in.nextLine();
                        System.out.println("Nilai nya sih " + nameSearch + " ?");
                        int nilaiPelajaran = Integer.parseInt(in.nextLine());

                        Pelajaran newPelajaran = new Pelajaran(namePelajaran, nilaiPelajaran, idGenerator);
                        studentList.get(i).getPelajaran().add(newPelajaran);
                        System.out.println("Pelajaran added! ");
                        System.out.println(studentList.get(i).printDetails());
                  }
            }

            System.out.println("--------------------");
      }

      private static void editStudent(ArrayList<Student> studentList, Scanner in) {
            System.out.println("--------------------");
            System.out.println("Edit Student");
            System.out.println("--------------------");

            System.out.println("--------------------");
            System.out.println("Which student do you want to edit?");
            String nameSearch = in.nextLine();

            for (int i = 0; i < studentList.size(); i++) {
                  if (studentList.get(i).getName().equals(nameSearch)) {
                        System.out.println(studentList.get(i).printDetails());
                        System.out.println("What do you want to edit? (name,gender,age,delete)");
                        String whichEdit = in.nextLine();

                        if (whichEdit.equals("name")) {
                              System.out.println("New Name?");
                              studentList.get(i).setName(in.nextLine());
                              System.out.println("New Name Saved :D");
                        }
                        if (whichEdit.equals("gender")) {
                              System.out.println("New gender?");

                              studentList.get(i).setGender(in.nextLine());
                              System.out.println("New gender Saved :D");
                        }
                        if (whichEdit.equals("age")) {
                              System.out.println("New age?");

                              studentList.get(i).setAge(Integer.parseInt(in.nextLine()));
                              System.out.println("New age Saved :D");
                        }
                        if (whichEdit.equals("delete")) {
                              System.out.println(studentList.get(i).getName() + " has been deleted :D");
                              studentList.remove(i);
                        }
                  }

            }

            System.out.println("--------------------");
      }

      private static void createStudent(int idGenerator, ArrayList<Student> studentList, Scanner in) {
            Student newStudent = new Student();

            System.out.println("--------------------");
            System.out.println("Create Student");
            System.out.println("--------------------");

            System.out.println("--------------------");
            System.out.println("Student name?");

            newStudent.setName(in.nextLine());
            System.out.println("Student age?");
            newStudent.setAge(Integer.parseInt(in.nextLine()));
            System.out.println("Student Gender?");
            newStudent.setGender(in.nextLine());
            newStudent.setId(idGenerator);
            studentList.add(newStudent);
            System.out.println("Student Created!");

            System.out.println("--------------------");
      }
}