package dddeurope;

import dddeurope.actor.ActorFactory;
import dddeurope.actor.Waiter;
import dddeurope.message.MsgBase;
import dddeurope.message.OrderCooked;
import dddeurope.message.OrderPlaced;
import dddeurope.message.OrderPriced;
import dddeurope.monitoring.MonitoringDaemon;
import dddeurope.repeater.RepeaterFactory;

import static java.util.Arrays.asList;

class Restaurant {

  public static void main(String[] args) {
    // create
    ActorFactory actorFactory = new ActorFactory();
    RepeaterFactory repeaterFactory = new RepeaterFactory();
    MonitoringDaemon monitoringDaemon = new MonitoringDaemon();
    TopicBasedPublishSubscribe topicBasedPublishSubscribe = new TopicBasedPublishSubscribe();

    Handler<OrderPriced> cashier = actorFactory.createCashier();

    ThreadedHandler<OrderCooked> johnTheManager = actorFactory.createAssistantManager(topicBasedPublishSubscribe, "John The Manager");
    ThreadedHandler<OrderPlaced> gordon = actorFactory.createCook(topicBasedPublishSubscribe, "Gordon Ramsy", 1000);
    ThreadedHandler<OrderPlaced> jamie = actorFactory.createCook(topicBasedPublishSubscribe, "Jamie Oliver", 1303);
    ThreadedHandler<OrderPlaced> piet = actorFactory.createCook(topicBasedPublishSubscribe, "Piet Huysentruyt", 2500);

    monitoringDaemon.monitor(johnTheManager);
    monitoringDaemon.monitor(gordon);
    monitoringDaemon.monitor(jamie);
    monitoringDaemon.monitor(piet);

    ThreadedHandler<OrderPlaced> kitchen = repeaterFactory
        .createThreadedMoreFairRepeater(asList(gordon, jamie, piet), "Kitchen repeater");
    monitoringDaemon.monitor(kitchen);

    Waiter waiter = actorFactory.createWaiter(topicBasedPublishSubscribe);

    //subscribe
    topicBasedPublishSubscribe.subscribe(cashier, OrderPriced.class);
    topicBasedPublishSubscribe.subscribe(johnTheManager, OrderCooked.class);
    topicBasedPublishSubscribe.subscribe(kitchen, OrderPlaced.class);

    //start
    monitoringDaemon.start();

    johnTheManager.start();
    gordon.start();
    jamie.start();
    piet.start();
    kitchen.start();

    // stable - feed in orders
    order(waiter, 100, topicBasedPublishSubscribe);
  }

  private static void order(Waiter waiter, int numberOfOrders, TopicBasedPublishSubscribe topicBasedPublishSubscribe) {
    for (int i = 0; i < numberOfOrders; i++) {
      Order order = new Order();
      order.addItem(new Item(4, 50, "Razor-blade ice cream"));

      topicBasedPublishSubscribe.subscribe(order.getUuid().toString(), new CorrelatedMsgsHandler());

      waiter.placeOrder(order);
    }
  }
}
