package dddeurope;

import java.util.Arrays;

class Restaurant {

  public static void main(String[] args) {
    OrderPrinter orderPrinter = new OrderPrinter();

    AssistantManager assistantManager = new AssistantManager(orderPrinter);

    Cook gordon = new Cook(assistantManager, "Gordon Ramsy");
    Cook jamie = new Cook(assistantManager, "Jamie Oliver");
    Cook piet = new Cook(assistantManager, "Piet Huysentruyt");

    Waiter waiter = new Waiter(new Repeater(Arrays.asList(gordon, jamie, piet)));

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
