package dddeurope;

import dddeurope.message.MsgBase;

public interface Publisher {


  <T extends MsgBase> void publish(T msg);
}
