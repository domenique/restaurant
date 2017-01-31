package dddeurope;

import java.util.Arrays;

class Restaurant {

  public static void main(String[] args) {
    OrderHandlerPrinter orderPrinter = new OrderHandlerPrinter();

    ThreadedHandler assistantManager = new ThreadedHandler(new AssistantManager(orderPrinter));

    ThreadedHandler gordon = new ThreadedHandler(new Cook(assistantManager, "Gordon Ramsy"));
    ThreadedHandler jamie = new ThreadedHandler(new Cook(assistantManager, "Jamie Oliver"));
    ThreadedHandler piet = new ThreadedHandler(new Cook(assistantManager, "Piet Huysentruyt"));

    OrderHandler orderHandler = new RoundRobinRepeater(Arrays.asList(gordon, jamie, piet));

    Waiter waiter = new Waiter(orderHandler);

    assistantManager.start();
    gordon.start();
    jamie.start();
    piet.start();

    int totalTime = 0;
    for (int i = 0; i < 10; i++) {
      Order order = new Order();
      order.addItem(new Item(4, 50, "Razorblade Icecream"));
      waiter.placeOrder(order);

      totalTime += order.getCookTime();
    }

    System.out.println("Total cooktime : " + totalTime);
  }

}
