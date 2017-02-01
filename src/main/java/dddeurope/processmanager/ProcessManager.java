package dddeurope.processmanager;

import dddeurope.Handler;
import dddeurope.Publisher;
import dddeurope.message.MsgBase;

interface ProcessManager<T extends MsgBase> extends Handler<T> {
  void setPublisher(Publisher publisher);
}
