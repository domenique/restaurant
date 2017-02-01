package dddeurope.message;

import dddeurope.Order;

import java.util.UUID;

public class OrderPlaced extends MsgBase {

  private Order order;

  public OrderPlaced(Order order) {
    super(UUID.randomUUID(), null);
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }
}
