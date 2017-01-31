package dddeurope;

class Cook implements OrderHandler {

  private static final int COOKTIME = 2000;
  private final OrderHandler next;
  private String name;

  public Cook(OrderHandler next, String name) {
    this.next = next;
    this.name = name;
  }

  public void handle(Order order) {
    System.out.println("Cook " + name + " is cooking " + order);
    sleep(COOKTIME);
    order.setCookTime(COOKTIME);
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
