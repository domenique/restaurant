package dddeurope.actor;

import dddeurope.Handler;
import dddeurope.message.TakePayment;

class Cashier implements Handler<TakePayment> {

  @Override
  public void handle(TakePayment takePayment) {
    System.out.println("Taking payment on " + takePayment.getOrder());
  }
}
