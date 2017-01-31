package dddeurope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class Restaurant {

  public static void main(String[] args) {
    List<ThreadedHandler> threadedHandlers = new ArrayList<>();
    OrderHandlerPrinter orderPrinter = new OrderHandlerPrinter();

    ThreadedHandler assistantManager = new ThreadedHandler(new AssistantManager(orderPrinter), "Threaded John The Manager");
    ThreadedHandler gordon = new ThreadedHandler(new Cook(assistantManager, "Gordon Ramsy"), "Threaded Gordon");
    ThreadedHandler jamie = new ThreadedHandler(new Cook(assistantManager, "Jamie Oliver"), "Threaded Jamiee");
    ThreadedHandler piet = new ThreadedHandler(new Cook(assistantManager, "Piet Huysentruyt"), "Threaded Piet");

    threadedHandlers.add(assistantManager);
    threadedHandlers.add(gordon);
    threadedHandlers.add(jamie);
    threadedHandlers.add(piet);

    startMonitoring(threadedHandlers);

    OrderHandler orderHandler = new RoundRobinRepeater(Arrays.asList(gordon, jamie, piet));
    Waiter waiter = new Waiter(orderHandler);

    for (ThreadedHandler handler : threadedHandlers) {
      handler.start();
    }

    int totalTime = order(waiter, 100);
    System.out.println("Total cooktime : " + totalTime);
  }

  private static int order(Waiter waiter, int numberOfOrders) {
    int totalTime = 0;
    for (int i = 0; i < numberOfOrders; i++) {
      Order order = new Order();
      order.addItem(new Item(4, 50, "Razorblade Icecream"));
      waiter.placeOrder(order);
      totalTime += order.getCookTime();
    }
    return totalTime;
  }

  private static void startMonitoring(final List<ThreadedHandler> handlersToMonitor) {
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        for (ThreadedHandler handler:handlersToMonitor) {
          System.out.println("Queue size of handler " + handler.getName() + " : " + handler.getQueueSize());
        }
      }
    }, 0, 1000);
  }

}
