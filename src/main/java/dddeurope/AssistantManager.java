package dddeurope;

class AssistantManager implements Handler<OrderCooked> {

  private final Publisher publisher;

  AssistantManager(Publisher publisher) {
    this.publisher = publisher;
  }

  public void handle(OrderCooked orderCooked) {
    System.out.println("Calculating prices");
    sleep();
    orderCooked.getOrder().getItems()
        .forEach(item -> orderCooked.getOrder().setSubtotal(orderCooked.getOrder().getSubtotal() + item.calculateTotalPrice()));
    publisher.publish(new OrderPriced(orderCooked.getOrder()));
  }

  private void sleep() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
