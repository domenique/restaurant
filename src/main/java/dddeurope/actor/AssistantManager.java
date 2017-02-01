package dddeurope.actor;

import dddeurope.Handler;
import dddeurope.Publisher;
import dddeurope.message.OrderCooked;
import dddeurope.message.OrderPriced;
import dddeurope.message.PriceOrder;

class AssistantManager implements Handler<PriceOrder> {

  private final Publisher publisher;

  AssistantManager(Publisher publisher) {
    this.publisher = publisher;
  }

  @Override
  public void handle(PriceOrder priceOrder) {
    System.out.println("Calculating prices");
    sleep();
    priceOrder.getOrder().getItems()
        .forEach(item -> priceOrder.getOrder().setSubtotal(priceOrder.getOrder().getSubtotal() + item.calculateTotalPrice()));
    publisher.publish(new OrderPriced(priceOrder.getOrder(), priceOrder));
  }

  private void sleep() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
