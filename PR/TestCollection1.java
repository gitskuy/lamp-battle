import java.util.*;

public class TestCollection1 {
  public static void main(String args[]) {
    ArrayList<String> list = new ArrayList<String>();
    list.add("Ravi");
    list.add("Vijay");
    list.add("Ravi");
    list.add("Ajay");

    ArrayList<String> list2 = new ArrayList<String>();
    list2.add("Ravi");
    list2.add("Vijay");

    list.retainAll(list2);

    Object temp[] = list.toArray();

    System.out.println("It is now an array the first thing in it is : " + temp[0]);

    Iterator itr = list.iterator();

    while (itr.hasNext()) {
      System.out.println(itr.next());
    }

    System.out.println("Size list:" + list.size());

    LinkedList<String> linklist = new LinkedList<String>();
    linklist.add("ravi");
    linklist.add("yazid");

    Iterator<String> itrs = linklist.iterator();

    while (itrs.hasNext()) {
      System.out.println(itrs.next());
    }

    System.out.println("First " + linklist.getFirst());
    System.out.println("Last " + linklist.getLast());
    System.out.println("Last index of " + linklist.lastIndexOf("ravi"));

    // Binary Search

    /**
     * Binary Search
     */
    int[] a = { 3, 7, 10, 15, 91, 110, 150 }; // a sorted array not containing duplicates
    int target = 91; // the element to be searched

    int left = 0;
    int middle;
    int right = a.length - 1;
    while (left <= right) {
      middle = (left + right) / 2;
      if (a[middle] == target) {
        System.out.println("Element found at index " + middle);
        break;
      } else if (a[middle] < target) {
        left = middle + 1;
      } else if (a[middle] > target) {
        right = middle - 1;
      }
    }

    /**
     * Bubble Sort
     */
    int[] x = { 4, 85, 7, 1, 0, 36, -5, 48 };
    for (int i = 0; i < x.length - 1; i++) {
      for (int j = 0; j < x.length - 1 - i; j++) {
        if (x[j + 1] < x[j]) {
          int simp = x[j];
          x[j] = x[j + 1];
          x[j + 1] = simp;
        }
      }
    }

    Random random = new Random();
    random.ints().limit(3).forEach(System.out::println);

    Random random2 = new Random();
    random2.ints().limit(10).sorted().forEach(System.out::println);

    List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
    // get count of empty string
    int count = strings.stream().filter(string -> string.isEmpty()).count();

    List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

    // get list of unique squares
    List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());

  }
}