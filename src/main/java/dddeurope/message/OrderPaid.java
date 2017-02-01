package dddeurope.message;

import dddeurope.Order;

import java.util.UUID;

public class OrderPaid extends MsgBase {

  private Order order;

  public OrderPaid(Order order, MsgBase cause) {
    super(cause);
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }

  @Override
  public String toString() {
    return "OrderPaid{" +
        super.toString() + "," +
        "order=" + order +
        '}';
  }
}
