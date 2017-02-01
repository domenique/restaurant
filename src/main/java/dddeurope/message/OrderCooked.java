package dddeurope.message;

import dddeurope.Order;

public class OrderCooked extends MsgBase {

  private Order order;

  public OrderCooked(Order order, MsgBase cause) {
    super(cause);
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }

  @Override
  public String toString() {
    return "OrderCooked{" +
        super.toString() + "," +
        "order=" + order +
        '}';
  }
}
