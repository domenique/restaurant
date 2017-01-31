package dddeurope.ttl;

import dddeurope.Handler;
import dddeurope.message.OrderPlaced;

public class TimeToLiveFactory {

  public Handler<OrderPlaced> create(Handler<OrderPlaced> child) {
    return new TimeToLiveChecker(child);
  }
}
