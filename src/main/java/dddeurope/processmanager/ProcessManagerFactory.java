package dddeurope.processmanager;

import dddeurope.Handler;
import dddeurope.Order;
import dddeurope.Publisher;
import dddeurope.message.MsgBase;

class ProcessManagerFactory {

  public <T extends MsgBase> Handler<T> create(Order order, Publisher publisher) {
    return new ProcessManager(order, publisher);
  }

  public <T extends MsgBase> Handler<T> createPayFirst(Order order, Publisher publisher) {
    return new PayFirstProcessManager(order, publisher);
  }
}
