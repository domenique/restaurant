package dddeurope.message;

import dddeurope.Order;

public class OrderPaid extends MsgBase {

  private Order order;

  public OrderPaid(Order order) {

    this.order = order;
  }

  public Order getOrder() {
    return order;
  }
}
