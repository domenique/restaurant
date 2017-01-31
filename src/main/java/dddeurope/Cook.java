package dddeurope;

class Cook implements HandleOrder {

  private final HandleOrder next;

  public Cook(HandleOrder next) {
    this.next = next;
  }

  public void handle(Order order) {
    int cooktime = 2000;
    System.out.println("Cooking");
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
