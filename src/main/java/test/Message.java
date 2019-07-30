
package test;

public class Message {
  public enum ConfigChangeType { ADD, UPDATE, DELETE};

  private Diff diff;
  private long timeStamp;
  private ConfigChangeType configChangeType;

  public Message(Diff diff, long timeStamp, ConfigChangeType configChangeType) {
    this.diff = diff;
    this.timeStamp = timeStamp;
    this.configChangeType = configChangeType;
  }

  public Diff getDiff() {
    return diff;
  }

  public long getTimeStamp() {
    return timeStamp;
  }

  public ConfigChangeType getConfigChangeType() {
    return configChangeType;
  }


}
