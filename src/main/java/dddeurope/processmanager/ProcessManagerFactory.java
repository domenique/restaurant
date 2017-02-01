package dddeurope.processmanager;

import dddeurope.Handler;
import dddeurope.Order;
import dddeurope.Publisher;
import dddeurope.message.MsgBase;

class ProcessManagerFactory {

  public <T extends MsgBase> ProcessManager<T> create(Publisher publisher) {
    return new NormalProcessManager(publisher);
  }

  public <T extends MsgBase> ProcessManager<T> createPayFirst(Order order, Publisher publisher) {
    return new PayFirstProcessManager(order, publisher);
  }
}
