import java.util.ArrayList;
import java.util.*;

public class Student implements Comparable<Student> {
   private String name;
   private int id;
   private String gender;
   private int umur;
   private ArrayList<Pelajaran> pelajaran = new ArrayList<Pelajaran>();

   public String getName() {
      return name;
   }

   @Override
   public int compareTo(Student comparestu) {
      String comparename = ((Student) comparestu).getName();
      /* For Ascending order */
      return this.getName().compareTo(comparename);

      /* For Descending order do like this */
      // return compareage-this.studentage;
   }

   public int getId() {
      return id;
   }

   public String getGender() {
      return gender;
   }

   public int getUmur() {
      return umur;
   }

   public void setName(String newName) {
      name = newName;
   }

   public void setGender(String newGender) {
      gender = newGender;
   }

   public void setAge(int newAge) {
      umur = newAge;
   }

   public void setId(int newId) {
      id = newId;
   }

   public ArrayList<Pelajaran> getPelajaran() {
      return pelajaran;
   }

   public void deletePelajaran(String namapelajaran) {
      for (int i = 0; i < pelajaran.size(); i++) {
         if (pelajaran.get(i).getNamaPelajaran().equals(namapelajaran)) {
            pelajaran.remove(i);
            System.out.println("Pelajaran Deleted");
         }
      }
   }

   public void changeNilaiPelajaran(String namapelajaran, int nilai) {
      for (int i = 0; i < pelajaran.size(); i++) {
         if (pelajaran.get(i).getNamaPelajaran().equals(namapelajaran)) {
            pelajaran.get(i).setNilai(nilai);
            System.out.println("Nilai Pelajaran: " + namapelajaran + " berhasil di edit.");
         }
      }
   }

   public void changeNamaPelajaran(String namapelajaran, String namabaru) {
      for (int i = 0; i < pelajaran.size(); i++) {
         if (pelajaran.get(i).getNamaPelajaran().equals(namapelajaran)) {
            pelajaran.get(i).setNamaPelajaran(namabaru);
            System.out.println(namapelajaran + " berhasil di edit menjadi " + namabaru);
         }
      }
   }

   public String printDetails() {
      String pelajaranprint = "";
      for (int i = 0; i < pelajaran.size(); i++) {
         pelajaranprint = pelajaranprint + pelajaran.get(i).printDetails() + "\n";
      }
      return "nama: " + name + "\n" + "age: " + umur + "\n" + "gender: " + gender + "\n" + " \n" + pelajaranprint;

   }
}