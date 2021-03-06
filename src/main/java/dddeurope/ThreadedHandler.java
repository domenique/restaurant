package dddeurope;

import dddeurope.message.MsgBase;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadedHandler<T extends MsgBase> implements Handler<T> {

  private Queue<T> queue;
  private Handler<T> handler;
  private String name;


  public ThreadedHandler(Handler<T> handler, String name) {
    this.handler = handler;
    this.queue = new LinkedBlockingQueue<>();
    this.name = name;
  }

  @Override
  public void handle(T msg) {
    queue.add(msg);
  }

  public void start() {
    new Thread(() -> {
      while (true) {
        if (!queue.isEmpty()) {
          handler.handle(queue.poll());
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
