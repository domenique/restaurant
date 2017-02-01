package dddeurope.actor;

import dddeurope.Order;
import dddeurope.Publisher;
import dddeurope.message.OrderPlaced;

import java.util.UUID;

public class Waiter {

  private Publisher publisher;

  public Waiter(Publisher publisher) {
    this.publisher = publisher;
  }

  public UUID placeOrder(Order order) {
    System.out.println("Taking order");
    publisher.publish(new OrderPlaced(order));

    return order.getUuid();
  }
}
