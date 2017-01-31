package dddeurope;

class OrderPlaced extends MsgBase {

  private Order order;

  OrderPlaced(Order order) {

    this.order = order;
  }

  public Order getOrder() {
    return order;
  }
}
