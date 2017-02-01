package dddeurope.message;

import dddeurope.Order;

public class CookingTimedOut<T extends MsgBase> extends MsgBase {
  private Order order;

  public  CookingTimedOut(T cause, Order order) {
    super(cause);

    this.order = order;
  }

  public Order getOrder() {
    return order;
  }

  @Override
  public String toString() {
    return "CookingTimedOut{" +
        super.toString() + "," +
        "order=" + order +
        '}';
  }
}
