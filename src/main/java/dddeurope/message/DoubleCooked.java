package dddeurope.message;

import dddeurope.Order;

public class DoubleCooked extends MsgBase {

  private final Order order;

  public DoubleCooked(MsgBase cause, Order order) {
    super(cause);
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }

  @Override
  public String toString() {
    return "DoubleCooked{" +
        super.toString() + "," +
        "order=" + order +
        '}';
  }
}
