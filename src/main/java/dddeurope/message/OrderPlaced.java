package dddeurope.message;

import dddeurope.Order;

public class OrderPlaced extends MsgBase {

  private Order order;

  public OrderPlaced(Order order) {

    this.order = order;
  }

  public Order getOrder() {
    return order;
  }
}
