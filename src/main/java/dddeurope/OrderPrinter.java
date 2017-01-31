package dddeurope;

class OrderPrinter implements HandleOrder {

  public void handle(Order order) {
    System.out.println(order);
  }
}
