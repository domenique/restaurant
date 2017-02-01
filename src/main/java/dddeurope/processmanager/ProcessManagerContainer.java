package dddeurope.processmanager;

import dddeurope.Handler;
import dddeurope.Order;
import dddeurope.ThreadedHandler;
import dddeurope.TopicBasedPublishSubscribe;
import dddeurope.message.MsgBase;
import dddeurope.message.OrderPlaced;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by tomdewolf on 01/02/2017.
 */
public class ProcessManagerContainer implements Handler<OrderPlaced> {

    HashMap<UUID, ProcessManager> processManagers = new HashMap<>();
    TopicBasedPublishSubscribe topicBasedPublishSubscribe;
    private ThreadedHandler threadedCorrelationIdHandler = new ThreadedHandler(new CorrelationIdSubscriber(), "Correlation id threaded handler");

    public ProcessManagerContainer(TopicBasedPublishSubscribe topicBasedPublishSubscribe) {
        this.topicBasedPublishSubscribe = topicBasedPublishSubscribe;
    }

    @Override
    public void handle(OrderPlaced msg) {
        topicBasedPublishSubscribe.subscribe(msg.getCorrelationId().toString(), threadedCorrelationIdHandler);
        processManagers.put(msg.getCorrelationId(), new ProcessManager(msg.getOrder(), topicBasedPublishSubscribe));
    }

    public void start() {
        threadedCorrelationIdHandler.start();
    }

    class CorrelationIdSubscriber<T extends MsgBase> implements Handler<T> {

        @Override
        public void handle(T msg) {
            processManagers.get(msg.getCorrelationId()).handle(msg);
        }
    }
}
