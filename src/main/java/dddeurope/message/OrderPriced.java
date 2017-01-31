package dddeurope.message;

import dddeurope.Order;

public class OrderPriced extends MsgBase {

  private Order order;

  public OrderPriced(Order order) {

    this.order = order;
  }

  public Order getOrder() {
    return order;
  }
}
