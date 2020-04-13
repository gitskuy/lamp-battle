public class Pelanggan {
  private String namapelangan;
  private int poin;
  private int id;

  public Pelanggan(String nama, int nilai, int id) {
    this.namapelangan = nama;
    this.poin = nilai;
    this.id = id;
  }

  public void setId(int newId) {
    id = newId;
  }

  public int getId() {
    return id;
  }

  public String printDetails() {
    return "ID: " + id + " Nama Pelajaran: " + namapelangan + " Nilai: " + poin;
  }

  public String getNamapelangan() {
    return namapelangan;
  }

  public void setNamapelangan(String namapelangan) {
    this.namapelangan = namapelangan;
  }

  public int getPoin() {
    return poin;
  }

  public void setPoin(int poin) {
    this.poin = poin;
  }

}