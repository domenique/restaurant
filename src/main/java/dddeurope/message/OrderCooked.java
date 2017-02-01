package dddeurope.message;

import dddeurope.Order;

import java.util.UUID;

public class OrderCooked extends MsgBase {

  private Order order;

  public OrderCooked(Order order, MsgBase cause) {
    super(cause);
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }

}
