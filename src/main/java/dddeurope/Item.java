package dddeurope;

public class Item {

  private int quantity;
  private int price;
  private String name;

  public Item(int quantity, int price, String name) {

    this.quantity = quantity;
    this.price = price;
    this.name = name;
  }

  public int calculateTotalPrice() {
    return quantity * price;
  }

  @Override
  public String toString() {
    return "Item{" +
        "quantity=" + quantity +
        ", price=" + price +
        ", name='" + name + '\'' +
        '}';
  }
}
