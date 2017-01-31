package dddeurope;

class Cook implements OrderHandler {

  private final OrderHandler next;
  private String name;

  public Cook(OrderHandler next, String name) {
    this.next = next;
    this.name = name;
  }

  public void handle(Order order) {
    System.out.println("Cook " + name + " is cooking " + order);
    int cooktime = 2000;
    sleep(cooktime);
    order.setCookTime(cooktime);
    next.handle(order);
  }

  private void sleep(int cooktime) {
    try {
      Thread.sleep(cooktime);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
