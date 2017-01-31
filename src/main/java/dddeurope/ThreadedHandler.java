package dddeurope;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

class ThreadedHandler implements OrderHandler, Startable {

  private Queue<Order> queue;
  private OrderHandler orderHandler;
  private String name;


  public ThreadedHandler(OrderHandler orderHandler, String name) {
    this.orderHandler = orderHandler;
    this.queue = new LinkedBlockingQueue<>();
    this.name = name;
  }

  @Override
  public void handle(Order order) {
    queue.add(order);
  }

  @Override
  public void start() {
    new Thread(() -> {
      while (true) {
        if (!queue.isEmpty()) {
          orderHandler.handle(queue.poll());
        } else {
          try {
            Thread.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();
  }

  public String getName() {
    return name;
  }

  public int getQueueSize() {
    return queue.size();
  }
}
