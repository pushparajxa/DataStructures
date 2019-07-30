
package test;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class ConcreteBucket implements  Bucket {

  private final String idetifier ;

  private ConcurrentHashMap<String,Config>  configs = new ConcurrentHashMap<>();

  private ConcurrentHashMap<String, Client> subscriptions = new ConcurrentHashMap<>();

  public ConcreteBucket(String idetifier){
    this.idetifier = idetifier;
  }

  @Override
  public String identifier() {
    return idetifier;
  }

  @Override
  public void addConfig(Config config) {
     checkConfigExistAlready(config.identifier());
     configs.put(config.identifier(),config);
  }

  @Override
  public void updateConfig(Config config) {
    checkConfigExistAlready(config.identifier());
    configs.put(config.identifier(),config);

  }

  @Override
  public void deleteConfig(String identifier) {
    checkConfigExistAlready(identifier);
    configs.remove(identifier);

  }

  @Override
  public Config getConfig(String identifier) {
    return configs.get(identifier);
  }

  @Override
  public void addSubscriber(Client client) {
      if(subscriptions.containsKey(client.identifier())){
        throw new RuntimeException("A client already subscriberd with identifer"+idetifier);
      }

      subscriptions.put(client.identifier(),client);
  }

  @Override
  public void removeSubscriber(Client client) {
    if(!subscriptions.containsKey(client.identifier())){
      throw new RuntimeException("A client is  not subscriberd with identifier"+idetifier);
    }
  }

  @Override
  public Collection<Config> getAllConfigs() {
    return configs.values();
  }

  private void checkConfigExistAlready(String configIdentifer){
    if(!configs.containsKey(configIdentifer)){
      throw new RuntimeException("Config with name "+configIdentifer+" already exist");
    }
  }
}
