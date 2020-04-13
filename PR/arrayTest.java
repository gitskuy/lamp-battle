public class arrayTest {
   public static void main(String[] args) {
    //original array
 	int[] myArray = { 1, 2, 3, 4, 5, 6 };

 	// new larger array
 	int[] hold = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

 	System.out.println("--- Hold sebelum copy---");

 	for(int i = 0;i < hold.length;i++){         
         System.out.println(hold[i]);
    };


 	System.out.println("--- Hold sebelum copy---");


    System.out.println("--- Hold sesudah copy ---");

 	// copy all of the myArray array to the hold
 	// array, starting with the 0th index
 	System.arraycopy(myArray, 0, hold, 0, myArray.length);

 	for(int i = 0;i < hold.length;i++){         
         System.out.println(hold[i]);
    };

    System.out.println("--- Hold sesudah copy ---");
   
   }
}