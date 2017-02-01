package dddeurope.processmanager;

import dddeurope.Order;
import dddeurope.Publisher;
import dddeurope.message.CookFood;
import dddeurope.message.CookingTimedOut;
import dddeurope.message.DoubleCooked;
import dddeurope.message.MsgBase;
import dddeurope.message.OrderCooked;
import dddeurope.message.OrderPlaced;
import dddeurope.message.OrderPriced;
import dddeurope.message.PriceOrder;
import dddeurope.message.PublishAt;
import dddeurope.message.TakePayment;

import java.time.LocalDateTime;

public class NormalProcessManager<T extends MsgBase> implements ProcessManager<T> {

  private Publisher topicBasedPublishSubscribe;
  private static final int COOKING_TIMEOUT_SEC = 2;
  private boolean isCooked;

  NormalProcessManager(Publisher topicBasedPublishSubscribe) {
    this.topicBasedPublishSubscribe = topicBasedPublishSubscribe;
  }

  @Override
  public void handle(T msg) {
    if (msg instanceof OrderPlaced) {
      tryToCook(msg, ((OrderPlaced) msg).getOrder());
    }
    if (msg instanceof OrderCooked) {
      if (this.isCooked) {
        topicBasedPublishSubscribe.publish(new DoubleCooked(msg, ((OrderCooked) msg).getOrder()));
      }
      this.isCooked = true;
      topicBasedPublishSubscribe.publish(new PriceOrder(msg, ((OrderCooked) msg).getOrder()));

    }
    if (msg instanceof OrderPriced) {
      topicBasedPublishSubscribe.publish(new TakePayment(msg, ((OrderPriced) msg).getOrder()));
    }
    if (msg instanceof CookingTimedOut) {
      if (!isCooked) {
        System.out.println("TIMEOUT - RETRY COOKING");
        tryToCook(msg, ((CookingTimedOut) msg).getOrder());
      }
    }
  }

  private void tryToCook(T msg, Order order) {
    topicBasedPublishSubscribe.publish(new CookFood(msg, order));
    topicBasedPublishSubscribe
        .publish(new PublishAt(msg, new CookingTimedOut(msg, order), LocalDateTime.now().plusSeconds(COOKING_TIMEOUT_SEC)));
  }

  @Override
  public void setPublisher(Publisher publisher) {
    this.topicBasedPublishSubscribe = publisher;
  }
}
