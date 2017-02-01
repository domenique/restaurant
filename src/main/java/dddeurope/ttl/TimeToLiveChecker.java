package dddeurope.ttl;

import dddeurope.Handler;
import dddeurope.message.CookFood;

class TimeToLiveChecker implements Handler<CookFood> {

  private Handler<CookFood> next;

  TimeToLiveChecker(Handler<CookFood> next) {
    this.next = next;
  }

  @Override
  public void handle(CookFood msg) {
    long timeInFlight = System.currentTimeMillis() - msg.getOrder().getCreationTime();
    if (timeInFlight > 5000) {
      System.out.println("DROPPING ORDER " + timeInFlight);
      return;
    }
    next.handle(msg);
  }
}
