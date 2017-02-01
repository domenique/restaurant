package dddeurope;

import dddeurope.actor.ActorFactory;
import dddeurope.actor.Waiter;
import dddeurope.message.CookFood;
import dddeurope.message.OrderPlaced;
import dddeurope.message.PriceOrder;
import dddeurope.message.TakePayment;
import dddeurope.monitoring.MonitoringDaemon;
import dddeurope.processmanager.ProcessManagerContainer;
import dddeurope.repeater.RepeaterFactory;

import static java.util.Arrays.asList;

class Restaurant {

  public static void main(String[] args) {
    // create
    ActorFactory actorFactory = new ActorFactory();
    RepeaterFactory repeaterFactory = new RepeaterFactory();
    MonitoringDaemon monitoringDaemon = new MonitoringDaemon();
    TopicBasedPublishSubscribe topicBasedPublishSubscribe = new TopicBasedPublishSubscribe();
    ProcessManagerContainer processManagerContainer = new ProcessManagerContainer(topicBasedPublishSubscribe);

    Handler cashier = actorFactory.createCashier(topicBasedPublishSubscribe);

    ThreadedHandler johnTheManager = actorFactory.createAssistantManager(topicBasedPublishSubscribe, "John The Manager");
    ThreadedHandler gordon = actorFactory.createCook(topicBasedPublishSubscribe, "Gordon Ramsy", 1000);
    ThreadedHandler jamie = actorFactory.createCook(topicBasedPublishSubscribe, "Jamie Oliver", 1303);
    ThreadedHandler piet = actorFactory.createCook(topicBasedPublishSubscribe, "Piet Huysentruyt", 2500);

    monitoringDaemon.monitor(johnTheManager);
    monitoringDaemon.monitor(gordon);
    monitoringDaemon.monitor(jamie);
    monitoringDaemon.monitor(piet);

    ThreadedHandler kitchen = repeaterFactory.createThreadedMoreFairRepeater(asList(gordon, jamie, piet), "Kitchen repeater");
    monitoringDaemon.monitor(kitchen);

    Waiter waiter = actorFactory.createWaiter(topicBasedPublishSubscribe);

    //subscribe
    topicBasedPublishSubscribe.subscribe(cashier, TakePayment.class);
    topicBasedPublishSubscribe.subscribe(johnTheManager, PriceOrder.class);
    topicBasedPublishSubscribe.subscribe(kitchen, CookFood.class);
    topicBasedPublishSubscribe.subscribe(processManagerContainer, OrderPlaced.class);

    //start
    monitoringDaemon.start();

    johnTheManager.start();
    gordon.start();
    jamie.start();
    piet.start();
    kitchen.start();
    processManagerContainer.start();

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
