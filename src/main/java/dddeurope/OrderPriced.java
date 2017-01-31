package dddeurope;

class OrderPriced extends MsgBase {

  private Order order;

  OrderPriced(Order order) {

    this.order = order;
  }

  public Order getOrder() {
    return order;
  }
}
