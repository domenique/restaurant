package dddeurope;

import java.util.List;

class MoreFairRepeater implements OrderHandler {

  private List<ThreadedHandler> handlers;

  public MoreFairRepeater(List<ThreadedHandler> handlers) {
    this.handlers = handlers;
  }

  @Override
  public void handle(Order order) {
    while (true) {
      for (ThreadedHandler handler : handlers) {
        if (handler.getQueueSize() < 5) {
          handler.handle(order);
          return;
        }
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
