package dddeurope.message;

import java.util.UUID;

public abstract class MsgBase {

  private UUID id;
  private UUID correlationId;
  private UUID causeId;

  public MsgBase(UUID correlationId, UUID causeId) {
    this.id = UUID.randomUUID();
    this.correlationId = correlationId;
    this.causeId = causeId;
  }

  public MsgBase(MsgBase cause) {
    this(cause.getCorrelationId(), cause.getId());
  }

  @Override
  public String toString() {
    return "MsgBase{" +
        "id=" + id +
        ", correlationId=" + correlationId +
        ", causeId=" + causeId +
        '}';
  }

  public UUID getCorrelationId() {
    return correlationId;
  }

  public UUID getId() {
    return id;
  }
}
