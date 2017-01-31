package dddeurope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class TopicBasedPublishSubscribe implements Publisher {

  private Map<String, List<OrderHandler>> topics = new HashMap<>();

  public void subscribe(String topic, OrderHandler orderHandler) {
    List<OrderHandler> orderHandlers = topics.get(topic);
    if (orderHandlers == null) {
      orderHandlers = new ArrayList<>();
    } else {
      orderHandlers = new ArrayList<>(orderHandlers);
    }

    orderHandlers.add(orderHandler);
    topics.put(topic, orderHandlers);
  }

  @Override
  public void publish(String topic, Order order) {
    Optional.ofNullable(topics.get(topic))
        .orElse(Collections.emptyList())
        .forEach(o -> o.handle(order));
  }
}
