package dddeurope;

class AssistantManager implements OrderHandler {

  private final OrderHandler next;

  public AssistantManager(OrderHandler next) {
    this.next = next;
  }

  public void handle(Order order) {
    System.out.println("Calculating prices");
    sleep();
    order.getItems().forEach(item -> order.setSubtotal(order.getSubtotal() + item.calculateTotalPrice()));
    next.handle(order);
  }

  private void sleep() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
