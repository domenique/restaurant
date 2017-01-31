package dddeurope;

class Cashier implements OrderHandler {

  public void handle(Order order) {
    System.out.println(order);
  }
}
