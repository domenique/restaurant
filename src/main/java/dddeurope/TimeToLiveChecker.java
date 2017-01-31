package dddeurope;

class TimeToLiveChecker implements OrderHandler {

  private OrderHandler next;

  TimeToLiveChecker(OrderHandler next) {

    this.next = next;
  }
  @Override
  public void handle(Order order) {
    long timeInFlight = System.currentTimeMillis() - order.getCreationTime();
    if (timeInFlight > 5000) {
      System.out.println("DROPPING ORDER " + timeInFlight);
      return;
    }
    next.handle(order);
  }
}
