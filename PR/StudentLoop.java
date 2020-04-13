import java.util.Scanner;
import java.util.ArrayList;
public class StudentLoop{
   
public static void main(String args[]){
      int flagLoop = 0;
      int idGenerator = 0;
      ArrayList<Student> studentList = new ArrayList<Student>();

      while(flagLoop == 0){
             Scanner in = new Scanner(System.in);
            System.out.println("--------------------");
      	System.out.println("1.Create Student");
      	System.out.println("2.Edit Student ");
      	System.out.println("3.Create Pelajaran");
            System.out.println("4.Edit Pelajaran");
            System.out.println("5.Show Each Student");
            System.out.println("6.Show All Student");
      	System.out.println("7.EXIT");
      	System.out.println("--------------------");
            int pointer = 0;

      	pointer = Integer.parseInt(in.nextLine());
      	
      	if(pointer == 1){      		
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


      	}if(pointer == 2){

                  System.out.println("--------------------");
      		System.out.println("Edit Student");
                  System.out.println("--------------------");


                  System.out.println("--------------------");
                  System.out.println("Which student do you want to edit?");
                  String nameSearch = in.nextLine();

                  for(int i = 0;i < studentList.size();i++){
                        if(studentList.get(i).getName().equals(nameSearch)){
                              System.out.println(studentList.get(i).printDetails());
                              System.out.println("What do you want to edit? (name,gender,age,delete)");
                              String whichEdit = in.nextLine();
                              
                              if(whichEdit.equals("name")){
                                    System.out.println("New Name?");
                                    studentList.get(i).setName(in.nextLine());
                                    System.out.println("New Name Saved :D");
                              }
                               if(whichEdit.equals("gender")){
                                    System.out.println("New gender?");
                                    
                                    studentList.get(i).setGender(in.nextLine());
                                    System.out.println("New gender Saved :D");
                              }
                              if(whichEdit.equals("age")){
                                    System.out.println("New age?");

                                    studentList.get(i).setAge(Integer.parseInt(in.nextLine()));
                                    System.out.println("New age Saved :D");
                              }
                              if(whichEdit.equals("delete")){
                                    System.out.println(studentList.get(i).getName() + " has been deleted :D");
                                    studentList.remove(i);
                              }
                        }

                  }

                  System.out.println("--------------------");


      	}if(pointer == 3){

                  System.out.println("--------------------");
      		System.out.println("Create Pelajaran");
                  System.out.println("--------------------");


                  System.out.println("--------------------");
                  System.out.println("Create pelajaran untuk siapa?");
                  String nameSearch = in.nextLine();
                  
                  for(int i = 0;i < studentList.size();i++){
                        if(studentList.get(i).getName().equals(nameSearch)){
                              System.out.println("Nama pelajaran?");
                              String namePelajaran = in.nextLine();
                              System.out.println("Nilai nya sih " + nameSearch + " ?" );
                              int nilaiPelajaran = Integer.parseInt(in.nextLine());

                              Pelajaran newPelajaran = new Pelajaran(namePelajaran,nilaiPelajaran,idGenerator);
                              studentList.get(i).getPelajaran().add(newPelajaran);
                              System.out.println("Pelajaran added! ");
                              System.out.println(studentList.get(i).printDetails());

                              System.out.println(studentList.get(i).getPelajaran());
                        }
                  }


                  System.out.println("--------------------");

      	}if(pointer == 4){


                  System.out.println("--------------------");
                  System.out.println("Edit Pelajaran");
                  System.out.println("--------------------");


                  System.out.println("--------------------");
      		System.out.println("What is the Student Name?");
                  String nameFind = in.nextLine();
                  for(int i = 0;i < studentList.size();i++){
                        if(studentList.get(i).getName().equals(nameFind)){

                              System.out.println(studentList.get(i).printDetails());

                              System.out.println("Edit pelajaran apa?");
                              String pelajaranSearch = in.nextLine();
                              
                              System.out.println("Mau Diapain?");
                              System.out.print("1.Delete" + "\n" +
                                               "2.Ganti Nama" + "\n" + 
                                                "3.Ganti Nilai" + "\n");
                              
                              String option = in.nextLine();
                              
                              if(option.equals("1")){
                                    studentList.get(i).deletePelajaran(pelajaranSearch);      
                              }if(option.equals("2")){
                                    System.out.println("Nama Baru?");
                                    studentList.get(i).changeNamaPelajaran(pelajaranSearch,in.nextLine());      
                              }if(option.equals("3")){
                                    System.out.println("Nilai Baru?");
                                    studentList.get(i).changeNilaiPelajaran(pelajaranSearch,Integer.parseInt(in.nextLine()));      
                              }
                              System.out.println(studentList.get(i).printDetails());
                        }
                  }

                  System.out.println("--------------------");



      	}if(pointer == 5){

                  System.out.println("--------------------");
                  System.out.println("Showing Each student");
                  System.out.println("--------------------");


                  System.out.println("--------------------");
                  System.out.println("Which Student?");
                  String nameSearch = in.nextLine();
                  for(int i = 0;i < studentList.size();i++){
                        
                        if(studentList.get(i).getName().equals(nameSearch)){
                                System.out.println("--------------------");
                                System.out.println(studentList.get(i).printDetails());
                                System.out.println("--------------------");
                        }
                        
                  }

                  System.out.println("--------------------");

            }
            if(pointer == 6){

                  System.out.println("--------------------");
                  System.out.println("Showing all student!");
                  System.out.println("--------------------");
                  for(int i = 0;i < studentList.size();i++){
                        System.out.println("--------------------");
                        System.out.println(studentList.get(i).printDetails());
                        System.out.println("--------------------");
                  }

            }if(pointer == 7){

                  System.out.println("--------------------");
                  System.out.println("Are you sure? all data will be lost.(y/n)");
                  System.out.println("--------------------");
                  if(in.nextLine().equals("y")){

                        System.out.println("--------------------");
                        System.out.println("Exiting, thank you");
                        System.out.println("--------------------");
                        in.close();
                        flagLoop = 1;
                  }
            }

            idGenerator++;
            //sebuah generator id yang akan mengenerasi id.

      }
   }
}