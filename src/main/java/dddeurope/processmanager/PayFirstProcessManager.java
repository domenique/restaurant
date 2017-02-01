package dddeurope.processmanager;

import dddeurope.Handler;
import dddeurope.Order;
import dddeurope.Publisher;
import dddeurope.message.CookFood;
import dddeurope.message.MsgBase;
import dddeurope.message.OrderPaid;
import dddeurope.message.OrderPlaced;
import dddeurope.message.OrderPriced;
import dddeurope.message.PriceOrder;
import dddeurope.message.TakePayment;

public class PayFirstProcessManager<T extends MsgBase> implements Handler<T> {

  private Order order;
  private Publisher topicBasedPublishSubscribe;

  public PayFirstProcessManager(Order order, Publisher topicBasedPublishSubscribe) {
    this.order = order;

    this.topicBasedPublishSubscribe = topicBasedPublishSubscribe;
  }

  @Override
  public void handle(T msg) {
    if (msg instanceof OrderPlaced) {
      topicBasedPublishSubscribe.publish(new PriceOrder(msg, order));
    }
    if (msg instanceof OrderPriced) {
      topicBasedPublishSubscribe.publish(new TakePayment(msg, order));
    }
    if (msg instanceof OrderPaid) {
      topicBasedPublishSubscribe.publish(new CookFood(msg, order));
    }
  }
}
