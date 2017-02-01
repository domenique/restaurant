package dddeurope.processmanager;

import dddeurope.Handler;
import dddeurope.Order;
import dddeurope.TopicBasedPublishSubscribe;
import dddeurope.message.*;

/**
 * Created by tomdewolf on 01/02/2017.
 */
public class ProcessManager<T extends MsgBase> implements Handler<T> {

    private Order order;
    private TopicBasedPublishSubscribe topicBasedPublishSubscribe;

    ProcessManager(Order order, TopicBasedPublishSubscribe topicBasedPublishSubscribe) {
        this.order = order;

        this.topicBasedPublishSubscribe = topicBasedPublishSubscribe;
    }
    @Override
    public void handle(T msg) {
        if (msg instanceof OrderPlaced) {
            topicBasedPublishSubscribe.publish(new CookFood(msg, order));
        }
        if (msg instanceof OrderCooked) {
            topicBasedPublishSubscribe.publish(new PriceOrder(msg, order));
        }
        if (msg instanceof OrderPriced) {
            topicBasedPublishSubscribe.publish(new TakePayment(msg, order));
        }
    }
}
