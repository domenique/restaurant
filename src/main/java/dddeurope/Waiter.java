package dddeurope;

import java.util.UUID;

class Waiter {

  private Publisher publisher;

  Waiter(Publisher publisher) {
    this.publisher = publisher;
  }

  UUID placeOrder(Order order) {
    System.out.println("Taking order");
    publisher.publish(new OrderPlaced(order));

    return order.getUuid();
  }
}
