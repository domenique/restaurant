package dddeurope.processmanager;

import dddeurope.Handler;
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

public class ProcessManager<T extends MsgBase> implements Handler<T> {

  private Order order;
  private Publisher topicBasedPublishSubscribe;
  private static final int COOKING_TIMEOUT_SEC = 2;
  private boolean isCooked;

  ProcessManager(Order order, Publisher topicBasedPublishSubscribe) {
    this.order = order;

    this.topicBasedPublishSubscribe = topicBasedPublishSubscribe;
  }

  @Override
  public void handle(T msg) {
    if (msg instanceof OrderPlaced) {
      tryToCook(msg);
    }
    if (msg instanceof OrderCooked) {
      if (this.isCooked) {
        topicBasedPublishSubscribe.publish(new DoubleCooked(msg, order));
      }
      this.isCooked = true;
      topicBasedPublishSubscribe.publish(new PriceOrder(msg, order));

    }
    if (msg instanceof OrderPriced) {
      topicBasedPublishSubscribe.publish(new TakePayment(msg, order));
    }
    if (msg instanceof CookingTimedOut) {
      if (!isCooked) {
        System.out.println("TIMEOUT - RETRY COOKING");
        tryToCook(msg);
      }
    }
  }

  private void tryToCook(T msg) {
    topicBasedPublishSubscribe.publish(new CookFood(msg, order));
    topicBasedPublishSubscribe.publish(new PublishAt(msg, new CookingTimedOut(msg, order), LocalDateTime.now().plusSeconds(COOKING_TIMEOUT_SEC)));
  }
}
