package dddeurope;

class Cashier implements Handler<OrderPriced> {

  public void handle(OrderPriced orderPriced) {
    System.out.println(orderPriced.getOrder());
  }
}
