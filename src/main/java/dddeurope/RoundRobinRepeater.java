package dddeurope;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

class RoundRobinRepeater<T extends MsgBase> implements Handler<T> {

  private Queue<Handler<T>> orders;

  public RoundRobinRepeater(List<Handler<T>> handlers) {
    this.orders = new LinkedBlockingDeque<>();
    this.orders.addAll(handlers);

  }


  @Override
  public void handle(T msg) {
    Handler<T> handler = orders.poll();
    try {
      handler.handle(msg);
    } finally {
      orders.add(handler);
    }
  }
}
