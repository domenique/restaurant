package dddeurope;

import java.util.List;

class Repeater implements OrderHandler {

  private List<OrderHandler> orderHandlers;

  Repeater(List<OrderHandler> orderHandlers) {
    this.orderHandlers = orderHandlers;
  }

  @Override
  public void handle(Order order) {
    for (OrderHandler orderHandler : orderHandlers) {
      orderHandler.handle(order);
    }
  }
}
