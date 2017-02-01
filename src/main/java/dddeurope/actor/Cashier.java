package dddeurope.actor;

import dddeurope.Handler;
import dddeurope.message.OrderPriced;

class Cashier implements Handler<OrderPriced> {

  @Override
  public void handle(OrderPriced orderPriced) {
    System.out.println(orderPriced.getOrder());
  }
}
