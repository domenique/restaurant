package dddeurope;

class Restaurant {

  public static void main(String[] args) {
    OrderPrinter orderPrinter = new OrderPrinter();

    AssistantManager assistantManager = new AssistantManager(orderPrinter);

    Cook cook = new Cook(assistantManager);

    Waiter waiter = new Waiter(cook);

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
