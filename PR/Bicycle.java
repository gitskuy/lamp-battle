public class Bicycle {
    private int cadence = 0;
    private int speed = 0;
    private int gear = 1;

    //setter buat cadance 
    public void changeCadence(int newValue) {
        cadence = newValue;
    }

    //setter buat gear 
    public void changeGear(int newValue) {
        gear = newValue;
    }

    //setter buat speed
    public void speedUp(int increment) {
        speed = speed + increment;
    }

    //sebuah method untuk ngerem mengurangi kecepatan
    public void applyBrakes(int decrement) {
        speed = speed - decrement;
    }

    //nge print state sepeda sekarang 
    public void printStates() {
        System.out.println("cadence:" +
                cadence + " speed:" +
                speed + " gear:" + gear);
    }

    // method baru ngepot mengurangi kecepatan sedikit 
    public void ngepot(){
        System.out.println("Ngepot");
        speed = speed - 1;
    }
}