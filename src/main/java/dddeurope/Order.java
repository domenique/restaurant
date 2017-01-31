package dddeurope;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Order {

  private UUID uuid;
  private int tableNumber;
  private List<Item> items = new ArrayList<>();
  private int subtotal;
  private int taxes;
  private int total;
  private int cookTime;
  private String ingredients;

  Order() {
    uuid = UUID.randomUUID();
  }

  UUID getUuid() {
    return uuid;
  }


  @Override
  public String toString() {
    return "Order{" +
        "uuid=" + uuid +
        ", tableNumber=" + tableNumber +
        ", items=" + items +
        ", subtotal=" + subtotal +
        ", taxes=" + taxes +
        ", total=" + total +
        ", cookTime=" + cookTime +
        ", ingredients='" + ingredients + '\'' +
        '}';
  }

  public void setSubtotal(int subtotal) {
    this.subtotal = subtotal;
  }

  public void setTaxes(int taxes) {
    this.taxes = taxes;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<Item> getItems() {
    return items;
  }

  public int getSubtotal() {
    return subtotal;
  }

  public void addItem(Item item) {
    items.add(item);
  }

  public void setCookTime(int cookTime) {
    this.cookTime = cookTime;
  }

  public int getCookTime() {
    return cookTime;
  }
}
