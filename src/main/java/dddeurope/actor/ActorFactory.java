package dddeurope.actor;

import dddeurope.Handler;
import dddeurope.ThreadedHandler;
import dddeurope.message.OrderCooked;
import dddeurope.message.OrderPlaced;
import dddeurope.message.OrderPriced;
import dddeurope.Publisher;
import dddeurope.ttl.TimeToLiveFactory;

public class ActorFactory {

  TimeToLiveFactory timeToLiveFactory = new TimeToLiveFactory();

  public Waiter createWaiter(Publisher publisher) {
    return new Waiter(publisher);
  }

  public Handler<OrderPriced> createCashier() {
    return new Cashier();
  }

  public ThreadedHandler<OrderPlaced> createCook(Publisher publisher, String name, int cookTime) {
    return new ThreadedHandler<>(timeToLiveFactory.create(new Cook(publisher, name, cookTime)), "Threaded " + name);
  }

  public ThreadedHandler<OrderCooked> createAssistantManager(Publisher publisher, String name) {
    return new ThreadedHandler<>(new AssistantManager(publisher), name);
  }
}
