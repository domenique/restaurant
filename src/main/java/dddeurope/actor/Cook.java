package dddeurope.actor;

import dddeurope.Handler;
import dddeurope.message.OrderCooked;
import dddeurope.message.OrderPlaced;
import dddeurope.Publisher;

class Cook implements Handler<OrderPlaced> {

  private final Publisher publisher;
  private String name;
  private int cookTime;

  Cook(Publisher publisher, String name, int cookTime) {
    this.publisher = publisher;
    this.name = name;
    this.cookTime = cookTime;
  }

  @Override
  public void handle(OrderPlaced orderPlaced) {
    System.out.println("Cook " + name + " is cooking " + orderPlaced.getOrder());
    sleep(cookTime);
    orderPlaced.getOrder().setCookTime(cookTime);
    publisher.publish(new OrderCooked(orderPlaced.getOrder()));
  }

  private void sleep(int cooktime) {
    try {
      Thread.sleep(cooktime);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
