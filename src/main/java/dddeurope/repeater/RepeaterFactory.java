package dddeurope.repeater;

import dddeurope.ThreadedHandler;
import dddeurope.message.OrderPlaced;

import java.util.List;

public class RepeaterFactory {

  public ThreadedHandler<OrderPlaced> createThreadedMoreFairRepeater(List<ThreadedHandler> childs, String name) {
    return new ThreadedHandler<>(new MoreFairRepeater<>(childs), name);
  }
}
