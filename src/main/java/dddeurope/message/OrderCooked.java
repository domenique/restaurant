package dddeurope.message;

import dddeurope.Order;

public class OrderCooked extends MsgBase {

  private Order order;

  public OrderCooked(Order order) {

    this.order = order;
  }

  public Order getOrder() {
    return order;
  }

}
