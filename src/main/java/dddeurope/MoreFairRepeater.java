package dddeurope;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

class MoreFairRepeater implements OrderHandler {

  private Queue<ThreadedHandler> handlers;

  public MoreFairRepeater(List<ThreadedHandler> handlers) {
    this.handlers = new LinkedBlockingDeque<>();
    this.handlers.addAll(handlers);
  }

  @Override
  public void handle(Order order) {
    while (true) {
      ThreadedHandler handler = handlers.poll();
      try {
        if (handler.getQueueSize() < 5) {
          handler.handle(order);
          return;
        }
      } finally {
        handlers.add(handler);
      }
      sleep(1000);
    }
  }

  private void sleep(int i) {
    try {
      Thread.sleep(i);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
