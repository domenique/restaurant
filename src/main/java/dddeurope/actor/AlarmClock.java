package dddeurope.actor;

import dddeurope.Handler;
import dddeurope.Publisher;
import dddeurope.message.PublishAt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlarmClock implements Handler<PublishAt> {

  private List<PublishAt> messages = new ArrayList<>();
  private Publisher publisher;

  public AlarmClock(Publisher publisher) {
    this.publisher = publisher;
  }

  @Override
  public void handle(PublishAt msg) {
    messages.add(msg);
  }

  public void start() {
    new Thread(() -> {
      while (true) {
        List<PublishAt> messagesToPublish = messages.stream()
            .filter(m -> m.getPublishAt().isBefore(LocalDateTime.now()))
            .collect(Collectors.toList());

        messages.removeAll(messagesToPublish);
        messagesToPublish.forEach(publishAtMsg -> publisher.publish(publishAtMsg.getDelayedMessage()));

        sleep();
      }
    }).start();
  }

  private void sleep() {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
