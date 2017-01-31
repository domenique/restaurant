package dddeurope;

class AssistantManager implements OrderHandler {

  private final Publisher publisher;

  public AssistantManager(Publisher publisher) {
    this.publisher = publisher;
  }

  public void handle(Order order) {
    System.out.println("Calculating prices");
    sleep();
    order.getItems().forEach(item -> order.setSubtotal(order.getSubtotal() + item.calculateTotalPrice()));
    publisher.publish("BillCalculated", order);
  }

  private void sleep() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
