package dddeurope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class TopicBasedPublishSubscribe implements Publisher {

  private Map<String, List<Handler<MsgBase>>> topics = new HashMap<>();

  public <T extends MsgBase> void subscribe(Handler<T> handler, Class<T> clazz) {
    subscribe(clazz.getName(), handler);
  }

  @Override
  public <T extends MsgBase> void publish(T msg) {
    publish(msg.getClass().getName(), msg);
  }


  private void subscribe(String topic, Handler handler) {
    List<Handler<MsgBase>> handlers = topics.get(topic);
    if (handlers == null) {
      handlers = new ArrayList<>();
    } else {
      handlers = new ArrayList<>(handlers);
    }

    handlers.add(handler);
    topics.put(topic, handlers);
  }

  private <T extends MsgBase> void publish(String topic, T msg) {
    Optional.ofNullable(topics.get(topic))
        .orElse(Collections.emptyList())
        .forEach(o -> o.handle(msg));
  }
}
