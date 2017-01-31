package dddeurope;

class Cook implements OrderHandler {

  private final Publisher publisher;
  private String name;
  private int cookTime;

  public Cook(Publisher publisher, String name, int cookTime) {
    this.publisher = publisher;
    this.name = name;
    this.cookTime = cookTime;
  }

  public void handle(Order order) {
    System.out.println("Cook " + name + " is cooking " + order);
    sleep(cookTime);
    order.setCookTime(cookTime);
    publisher.publish("FoodCooked", order);
  }

  private void sleep(int cooktime) {
    try {
      Thread.sleep(cooktime);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
