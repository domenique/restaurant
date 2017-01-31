package dddeurope;

import java.util.UUID;

class Waiter {

  private final OrderHandler orderHandler;

  Waiter(OrderHandler orderHandler) {
    this.orderHandler = orderHandler;
  }

  UUID placeOrder(Order order) {
    System.out.println("Taking order");
    orderHandler.handle(order);
    return order.getUuid();
  }
}
