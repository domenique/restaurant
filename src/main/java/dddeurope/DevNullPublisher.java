package dddeurope;

import dddeurope.message.MsgBase;

public class DevNullPublisher implements Publisher {


  @Override
  public <T extends MsgBase> void publish(T msg) {

  }
}
