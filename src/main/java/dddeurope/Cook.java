package dddeurope;

class Cook implements OrderHandler {

  private final OrderHandler next;
  private String name;
  private int cookTime;

  public Cook(OrderHandler next, String name, int cookTime) {
    this.next = next;
    this.name = name;
    this.cookTime = cookTime;
  }

  public void handle(Order order) {
    System.out.println("Cook " + name + " is cooking " + order);
    sleep(cookTime);
    order.setCookTime(cookTime);
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
