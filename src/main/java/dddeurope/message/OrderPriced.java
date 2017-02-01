package dddeurope.message;

import dddeurope.Order;

public class OrderPriced extends MsgBase {

  private Order order;

  public OrderPriced(Order order, MsgBase cause) {
    super(cause);
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }

  @Override
  public String toString() {
    return "OrderPriced{" +
        super.toString() + "," +
        "order=" + order +
        '}';
  }
}
