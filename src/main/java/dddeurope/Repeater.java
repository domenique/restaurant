package dddeurope;

import java.util.List;

class Repeater<T extends MsgBase> implements Handler<T> {

  private List<Handler<T>> handlers;

  Repeater(List<Handler<T>> handlers) {
    this.handlers = handlers;
  }

  @Override
  public void handle(T msg) {
    for (Handler<T> handler : handlers) {
      handler.handle(msg);
    }
  }
}
