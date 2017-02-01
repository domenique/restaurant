package dddeurope;

import dddeurope.message.MsgBase;

class CorrelatedMsgsHandler implements Handler<MsgBase> {


  @Override
  public void handle(MsgBase msg) {
    System.out.println("Received " + msg);
  }
}
