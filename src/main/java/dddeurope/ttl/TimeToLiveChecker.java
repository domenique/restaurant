package dddeurope.ttl;

import dddeurope.Handler;
import dddeurope.message.OrderPlaced;

class TimeToLiveChecker implements Handler<OrderPlaced> {

  private Handler<OrderPlaced> next;

  TimeToLiveChecker(Handler<OrderPlaced> next) {

    this.next = next;
  }

  @Override
  public void handle(OrderPlaced msg) {
    long timeInFlight = System.currentTimeMillis() - msg.getOrder().getCreationTime();
    if (timeInFlight > 5000) {
      System.out.println("DROPPING ORDER " + timeInFlight);
      return;
    }
    next.handle(msg);
  }
}
