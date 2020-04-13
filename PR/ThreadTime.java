class ThreadTime implements Runnable {
  public void run() {
    hashtag();
  }

  public void hashtag() {
    for (int i = 0; i < 3; i++) {
      System.out.print(i);
    }
  }

  public static void main(String args[]) {
    ThreadTime t1 = new ThreadTime();
    ThreadTime t2 = new ThreadTime();
    ThreadTime t3 = new ThreadTime();
    ThreadTime t4 = new ThreadTime();
    Motor m = new Motor();
    m.run();
    t1.run();
    m.run();
    t2.hashtag();
    t3.run();
    t4.run();
  }

}