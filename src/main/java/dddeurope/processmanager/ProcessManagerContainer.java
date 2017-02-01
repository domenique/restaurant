package dddeurope.processmanager;

import dddeurope.DevNullPublisher;
import dddeurope.Handler;
import dddeurope.ThreadedHandler;
import dddeurope.TopicBasedPublishSubscribe;
import dddeurope.message.MsgBase;
import dddeurope.message.OrderPlaced;

import java.util.HashMap;
import java.util.UUID;

public class ProcessManagerContainer implements Handler<OrderPlaced> {

  private ProcessManagerFactory factory = new ProcessManagerFactory();
  private TopicBasedPublishSubscribe topicBasedPublishSubscribe;
  int count = 0;

  private ThreadedHandler threadedCorrelationIdHandler = new ThreadedHandler(new CorrelationIdSubscriber(), "Correlation id threaded handler");

  public ProcessManagerContainer(TopicBasedPublishSubscribe topicBasedPublishSubscribe) {
    this.topicBasedPublishSubscribe = topicBasedPublishSubscribe;
  }

  @Override
  public void handle(OrderPlaced msg) {
    topicBasedPublishSubscribe.subscribe(msg.getCorrelationId().toString(), threadedCorrelationIdHandler);

  }

  public void start() {
    threadedCorrelationIdHandler.start();
  }

  class CorrelationIdSubscriber<T extends MsgBase> implements Handler<T> {

    @Override
    public void handle(T msg) {
      ProcessManager processManager = factory.create(new DevNullPublisher());
      topicBasedPublishSubscribe.getHistory(msg.getCorrelationId().toString()).stream().forEach(historyMsg -> processManager.handle(historyMsg));
      processManager.setPublisher(topicBasedPublishSubscribe);
      processManager.handle(msg);
    }
  }
}
