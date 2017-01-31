package dddeurope;

interface Handler<T extends MsgBase> {

  void handle(T msg);
}
