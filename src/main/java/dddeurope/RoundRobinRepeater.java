package dddeurope;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

class RoundRobinRepeater implements HandleOrder{

  private Queue<HandleOrder> orders;

  public RoundRobinRepeater(List<HandleOrder> orderHandlers) {
    this.orders = new LinkedBlockingDeque<>();
    this.orders.addAll(orderHandlers);

  }


  @Override
  public void handle(Order order) {
    HandleOrder handler = orders.poll();
    try {
      handler.handle(order);
    } finally {
      orders.add(handler);
    }
  }
}
