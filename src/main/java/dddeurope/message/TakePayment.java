package dddeurope.message;

import dddeurope.Order;

public class TakePayment extends MsgBase {

  private final Order order;

  public TakePayment(MsgBase cause, Order order) {
    super(cause);
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }

  @Override
  public String toString() {
    return "TakePayment {" +
            super.toString() + "," +
            "order=" + order +
            '}';
  }
}
