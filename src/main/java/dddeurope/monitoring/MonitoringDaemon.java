package dddeurope.monitoring;

import dddeurope.ThreadedHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MonitoringDaemon {

  private List<ThreadedHandler> handlersToMonitor = new ArrayList<>();

  public void monitor(ThreadedHandler handler) {
    this.handlersToMonitor.add(handler);
  }

  public void start() {
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        for (ThreadedHandler handler : handlersToMonitor) {
          System.out.println("Queue size of handler " + handler.getName() + " : " + handler.getQueueSize());
        }
      }
    }, 0, 1000);
  }
}
