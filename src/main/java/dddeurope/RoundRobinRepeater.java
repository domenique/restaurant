package dddeurope;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

class RoundRobinRepeater implements OrderHandler {

  private Queue<OrderHandler> orders;

  public RoundRobinRepeater(List<OrderHandler> orderHandlers) {
    this.orders = new LinkedBlockingDeque<>();
    this.orders.addAll(orderHandlers);

  }


  @Override
  public void handle(Order order) {
    OrderHandler handler = orders.poll();
    try {
      handler.handle(order);
    } finally {
      orders.add(handler);
    }
  }
}
