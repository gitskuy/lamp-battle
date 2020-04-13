public class Mobil extends Kendaraan {
   public void terbang() {
      System.out.println("Wow mobil nya terbang");
   }
   public static void main(String args[]) {
      Mobil mobil = new Mobil();
      mobil.maju();
      mobil.mundur();
      mobil.terbang();
     
   }
}