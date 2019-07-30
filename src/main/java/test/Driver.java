
package test;

import java.util.Collection;

public interface Driver {

  void addConfig(String bucketIdentifier, Config config);

  void updateConfig(String bucketIdentifier,Config config);

  void deleteConfig(String bucketIdentifier,String identifier);

  Config getConfig(String bucketIdentifier,String configIdentifier);

  void subScribe(String bucketIdentifier, Client client);

  void unsubscribe(String bucketIdentifier, Client client);

  void createBucket(String bucketIdentifier);

  Collection<Config> getAllConfigs(String bucketIdentifier);

}
