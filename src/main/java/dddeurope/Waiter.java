package dddeurope;

import java.util.UUID;

class Waiter {

  private Publisher publisher;

  public Waiter(Publisher publisher) {
    this.publisher = publisher;
  }

  UUID placeOrder(Order order) {
    System.out.println("Taking order");
    publisher.publish("OrderPlaced", order);

    return order.getUuid();
  }
}
