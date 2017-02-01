package dddeurope.problems;

import dddeurope.Handler;
import dddeurope.message.CookFood;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class FlakyNetwork implements Handler<CookFood> {

  private Handler<CookFood> next;

  public FlakyNetwork(Handler<CookFood> next) {
    this.next = next;
  }

  @Override
  public void handle(CookFood msg) {
    int randomNumber = generateRandomNumber();
    if (randomNumber == 0) {
      // duplicate
      next.handle(msg);
      next.handle(msg);
    } else {
      // drop
    }
  }

  private int generateRandomNumber() {
    try {
      return SecureRandom.getInstanceStrong().nextInt(2);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return 0;
  }
}
