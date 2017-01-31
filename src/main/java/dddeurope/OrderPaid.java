package dddeurope;

class OrderPaid extends MsgBase {

  private Order order;

  OrderPaid(Order order) {

    this.order = order;
  }

  public Order getOrder() {
    return order;
  }
}
