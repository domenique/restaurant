package dddeurope;

import java.util.List;

class Repeater implements HandleOrder {

  private List<HandleOrder> orderHandlers;

  Repeater(List<HandleOrder> orderHandlers) {
    this.orderHandlers = orderHandlers;
  }

  @Override
  public void handle(Order order) {
    for (HandleOrder orderHandler : orderHandlers) {
      orderHandler.handle(order);
    }
  }
}
