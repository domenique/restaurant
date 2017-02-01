package dddeurope.actor;

import dddeurope.Handler;
import dddeurope.Publisher;
import dddeurope.ThreadedHandler;
import dddeurope.message.CookFood;
import dddeurope.message.PriceOrder;
import dddeurope.message.TakePayment;
import dddeurope.ttl.TimeToLiveFactory;

public class ActorFactory {

  TimeToLiveFactory timeToLiveFactory = new TimeToLiveFactory();

  public Waiter createWaiter(Publisher publisher) {
    return new Waiter(publisher);
  }

  public Handler<TakePayment> createCashier(Publisher publisher) {
    return new Cashier(publisher);
  }

  public ThreadedHandler<CookFood> createCook(Publisher publisher, String name, int cookTime) {
    return new ThreadedHandler<>(timeToLiveFactory.create(new Cook(publisher, name, cookTime)), "Threaded " + name);
  }

  public ThreadedHandler<PriceOrder> createAssistantManager(Publisher publisher, String name) {
    return new ThreadedHandler<>(new AssistantManager(publisher), name);
  }
}
