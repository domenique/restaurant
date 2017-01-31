package dddeurope;

import dddeurope.message.MsgBase;

public interface Handler<T extends MsgBase> {

  void handle(T msg);
}
