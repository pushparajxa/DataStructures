
package test;

import java.util.Collection;

public interface Bucket{

  String identifier();

  void addConfig(Config config);

  void updateConfig(Config config);

  void deleteConfig(String identifier);

  Config getConfig(String identifier);

  void addSubscriber(Client client);

  void removeSubscriber(Client client);

  Collection<Config> getAllConfigs();

}