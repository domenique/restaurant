package dddeurope;

interface Publisher {


  <T extends MsgBase>  void publish(T msg);
}
