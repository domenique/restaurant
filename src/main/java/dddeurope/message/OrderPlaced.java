package dddeurope.message;

import dddeurope.Order;

public class OrderPlaced extends MsgBase {

  private Order order;

  public OrderPlaced(Order order) {
    super(order.getUuid(), null);
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }

  @Override
  public String toString() {
    return "OrderPlaced{" +
        super.toString() + "," +
        "order=" + order +
        '}';
  }
}
