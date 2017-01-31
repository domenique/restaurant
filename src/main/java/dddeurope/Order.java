package dddeurope;

import java.util.List;
import java.util.UUID;

class Order {

  private UUID uuid;
  private int tableNumber;
  private List<Item> items;
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
}
