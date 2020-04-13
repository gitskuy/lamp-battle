public class InventoriItems {
  private String name;
  private int id;
  private int stock;

  public InventoriItems(String name, int id, int stock) {
    this.name = name;
    this.id = id;
    this.stock = stock;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  @Override
  public String toString() {
    return "InvetoriItems [id=" + id + ", name=" + name + ", stock=" + stock + "]";
  }

  public String forData() {
    return "id/" + id + "name/" + name + "/" + stock;
  }

}