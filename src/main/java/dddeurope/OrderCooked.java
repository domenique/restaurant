package dddeurope;

class OrderCooked extends MsgBase {

  private Order order;

  OrderCooked(Order order) {

    this.order = order;
  }

  Order getOrder() {
    return order;
  }

}
