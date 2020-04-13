public class Motor extends Kendaraan implements Runnable {
   public void standing() {
      System.out.println("Wow motor nya standing");
   }

   public void run() {
      for (int i = 0; i < 3; i++) {
         System.out.print("NGENGNGENG");
      }
   }

   public static void main(String args[]) {
      int a = 20, b = 10;
      Motor motor = new Motor();
      motor.maju();
      motor.mundur();
      motor.standing();
   }
}