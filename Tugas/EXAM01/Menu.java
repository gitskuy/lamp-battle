import java.util.Arrays;

public class Menu {
  private String menuname;
  private int id;
  private float price;
  private InventoriItems Ingridients[] = new InventoriItems[100];

  public String getMenuname() {
    return menuname;
  }

  public void setMenuname(String menuname) {
    this.menuname = menuname;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public InventoriItems[] getIngridients() {
    return Ingridients;
  }

  public void setIngridients(InventoriItems[] ingridients) {
    Ingridients = ingridients;
  }

  @Override
  public String toString() {
    return "Menu [Ingridients=" + Arrays.toString(Ingridients) + ", id=" + id + ", menuname=" + menuname + ", price="
        + price + "]";
  }

  public Menu(String menuname, int id, float price, InventoriItems[] ingridients) {
    this.menuname = menuname;
    this.id = id;
    this.price = price;
    Ingridients = ingridients;
  }

}