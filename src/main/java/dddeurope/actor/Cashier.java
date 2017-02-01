package dddeurope.actor;

import dddeurope.Handler;
import dddeurope.Publisher;
import dddeurope.message.OrderPaid;
import dddeurope.message.TakePayment;

class Cashier implements Handler<TakePayment> {

  private Publisher publisher;

  Cashier(Publisher publisher) {

    this.publisher = publisher;
  }

  @Override
  public void handle(TakePayment takePayment) {
    System.out.println("Taking payment on " + takePayment.getOrder());
    publisher.publish(new OrderPaid(takePayment.getOrder(), takePayment));

  }
}
