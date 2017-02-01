package dddeurope.message;

import dddeurope.Order;

public class CookFood extends MsgBase {

  private final Order order;

  public CookFood(MsgBase cause, Order order) {
    super(cause);

    this.order = order;
  }

  public Order getOrder() {
    return order;
  }

  @Override
  public String toString() {
    return "CookFood {" +
            super.toString() + "," +
            "order=" + order +
            '}';
  }
}
