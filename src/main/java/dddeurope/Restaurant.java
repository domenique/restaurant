package dddeurope;

class Restaurant {

  public static void main(String[] args) {
    new Waiter(new OrderPrinter()).placeOrder(new Order());
  }
}
