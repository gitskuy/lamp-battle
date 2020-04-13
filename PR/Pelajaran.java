public class Pelajaran implements Comparable<Pelajaran> {
   private String namapelajaran;
   private int nilai;
   private int id;

   public Pelajaran(String nama, int nilai, int id) {
      this.namapelajaran = nama;
      this.nilai = nilai;
      this.id = id;
   }

   @Override
   public int compareTo(Pelajaran comparestu) {
      String comparename = ((Pelajaran) comparestu).getNamaPelajaran();
      /* For Ascending order */
      return this.getNamaPelajaran().compareTo(comparename);

      /* For Descending order do like this */
      // return compareage-this.studentage;
   }

   public void setId(int newId) {
      id = newId;
   }

   public int getId() {
      return id;
   }

   public String getNamaPelajaran() {
      return namapelajaran;
   }

   public int getNilai() {
      return nilai;
   }

   public void setNilai(int newNilai) {
      nilai = newNilai;
   }

   public void setNamaPelajaran(String newName) {
      namapelajaran = newName;
   }

   public String printDetails() {
      return "Nama Pelajaran: " + namapelajaran + " Nilai: " + nilai;
   }

}