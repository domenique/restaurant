package dddeurope.message;

import java.time.LocalDateTime;

public class PublishAt extends MsgBase {

  private LocalDateTime publishAt;
  private MsgBase delayedMessage;

  public PublishAt(MsgBase cause, MsgBase delayedMessage, LocalDateTime publishAt) {
    super(cause);
    this.publishAt = publishAt;
    this.delayedMessage = delayedMessage;
  }

  public LocalDateTime getPublishAt() {
    return publishAt;
  }

  public MsgBase getDelayedMessage() {
    return delayedMessage;
  }

  @Override
  public String toString() {
    return "PublishAt{" +
        "publishAt=" + publishAt +
        ", delayedMessage=" + delayedMessage +
        '}';
  }
}
