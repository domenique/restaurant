package dddeurope.message;

import dddeurope.Order;

public class PriceOrder extends MsgBase {
  private final Order order;

  public PriceOrder(MsgBase cause, Order order) {
    super(cause);
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }
}
