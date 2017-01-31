package dddeurope;

class OrderHandlerPrinter implements OrderHandler {

  public void handle(Order order) {
    System.out.println(order);
  }
}
