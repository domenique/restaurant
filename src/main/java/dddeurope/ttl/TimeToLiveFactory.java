package dddeurope.ttl;

import dddeurope.Handler;
import dddeurope.message.CookFood;

public class TimeToLiveFactory {

  public Handler<CookFood> create(Handler<CookFood> child) {
    return new TimeToLiveChecker(child);
  }
}
