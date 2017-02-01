package dddeurope.processmanager;

import dddeurope.Handler;
import dddeurope.ThreadedHandler;
import dddeurope.TopicBasedPublishSubscribe;
import dddeurope.message.MsgBase;
import dddeurope.message.OrderPlaced;

import java.util.HashMap;
import java.util.UUID;

public class ProcessManagerContainer implements Handler<OrderPlaced> {

  private ProcessManagerFactory factory = new ProcessManagerFactory();
  private HashMap<UUID, Handler> processManagers = new HashMap<>();
  private TopicBasedPublishSubscribe topicBasedPublishSubscribe;
  int count = 0;

  private ThreadedHandler threadedCorrelationIdHandler = new ThreadedHandler(new CorrelationIdSubscriber(), "Correlation id threaded handler");

  public ProcessManagerContainer(TopicBasedPublishSubscribe topicBasedPublishSubscribe) {
    this.topicBasedPublishSubscribe = topicBasedPublishSubscribe;
  }

  @Override
  public void handle(OrderPlaced msg) {
    topicBasedPublishSubscribe.subscribe(msg.getCorrelationId().toString(), threadedCorrelationIdHandler);
    if (count++ % 2 == 0) {
      processManagers.put(msg.getCorrelationId(), factory.create(msg.getOrder(), topicBasedPublishSubscribe));
    } else {
      processManagers.put(msg.getCorrelationId(), factory.createPayFirst(msg.getOrder(), topicBasedPublishSubscribe));
    }
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
