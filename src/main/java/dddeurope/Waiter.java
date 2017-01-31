package dddeurope;

import java.util.UUID;

class Waiter {

  private final HandleOrder orderHandler;

  Waiter(HandleOrder orderHandler) {
    this.orderHandler = orderHandler;
  }

  UUID placeOrder(Order order) {
    System.out.println("Taking order");
    orderHandler.handle(order);
    return order.getUuid();
  }
}
