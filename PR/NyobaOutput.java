public class NyobaOutput {
   public static String peter = "Peter";
   public static String joan = "Joan";
   public static String steve = "Steve";
   public static String tom = "Tom";
	
   public static void main(String args[]){
      //saving the input from the command line to a different array so we 
      // can manipulate it later
      String[] foroutput = new String[args.length];
      for(int i = 0;i < args.length;i++){         
         foroutput[i] = args[i];
      };


      if(foroutput[0].equals("hitung")){
         System.out.println(output1(foroutput[1],Integer.parseInt(foroutput[2])));
      }

      if(foroutput[0].equals("pertemanan")){
         String[] pertemanan = new String[foroutput.length - 2];

         for(int i = 1;i < pertemanan.length + 1;i++){
               pertemanan[i - 1] = args[i];

         }; 

 
         System.out.println(output2(pertemanan,Integer.parseInt(foroutput[args.length - 1])));
      }


   }
   public static String output1(String input, int nilai){
      if(nilai <= 100 && nilai >= 90){
         return input + " " + nilai + " A";
      }if(nilai < 90 && nilai >= 60){
         return input + " " + nilai + " B";
      }if(nilai < 60 && nilai >= 50){
         return input + " " + nilai + " C";
      }if(nilai < 100 && nilai >= 90){
         return input + " " + nilai + " D";
      }
      return input + " " + nilai + " F";
   }
   public static String output2(String[] pertemanan,int nilai){
      String output = "";
      String pertemananOut = "";

      for (int i = 0; i < pertemanan.length; i++) { 
         if(i == (pertemanan.length - 1)){
         pertemananOut = pertemananOut + pertemanan[i];   
         }else{
         pertemananOut = pertemanan[i] + "," + pertemananOut;
         }
       } 

      if(nilai == 1){
         output += "January";
      }else if(nilai == 2){
         output += "February";
      }else if(nilai == 3){
         output += "March";
      }else if(nilai == 4){
         output += "April";
      }else if(nilai == 5){
         output += "May";
      }else if(nilai == 6){
         output += "June";
      }else if(nilai == 7){
         output += "July";
      }else if(nilai == 8){
         output += "August";
      }else if(nilai == 9){
         output += "September";
      }else if(nilai == 10){
         output += "Oktober";
      }else if(nilai == 11){
         output += "November";
      }else if(nilai == 12){
         output += "Desember";
      }else{
         output += "gak ada bulan segitu bro";
      }

      return pertemananOut + " goes to Tokyo on " + output;
   }
}