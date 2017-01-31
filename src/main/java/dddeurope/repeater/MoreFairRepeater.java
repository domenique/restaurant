package dddeurope.repeater;

import dddeurope.Handler;
import dddeurope.ThreadedHandler;
import dddeurope.message.MsgBase;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

class MoreFairRepeater<T extends MsgBase> implements Handler<T> {

  private Queue<ThreadedHandler> handlers;

  MoreFairRepeater(List<ThreadedHandler> handlers) {
    this.handlers = new LinkedBlockingDeque<>(handlers);
  }

  @Override
  public void handle(T msg) {
    while (true) {
      ThreadedHandler handler = handlers.poll();
      try {
        if (handler.getQueueSize() < 5) {
          handler.handle(msg);
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
