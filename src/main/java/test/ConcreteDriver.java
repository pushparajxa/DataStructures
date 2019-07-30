
package test;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import test.Message.ConfigChangeType;

public class ConcreteDriver implements Driver{

  private ConcurrentHashMap<String, Bucket>  buckets = new ConcurrentHashMap<>();

  private ConcurrentLinkedQueue<Message> queue = new ConcurrentLinkedQueue<>();

  public static void main(String[] args) {
    System.out.println("hello");
  }

  @Override
  public void addConfig(String bucketIdentifier, Config config) {
    checkBucketExist(bucketIdentifier);

    Object oldContenst = buckets.get(bucketIdentifier).getConfig(config.identifier()).contents();

    buckets.get(bucketIdentifier).addConfig(config);

    Message message = new Message(new ConcreteDiff(bucketIdentifier, oldContenst,
        config.contents()),System.currentTimeMillis(), ConfigChangeType.ADD);

    queue.add(message);


  }

  private void checkBucketExist(String bucketIdentifier) {
    if (!buckets.containsKey(bucketIdentifier)) {
      throw new RuntimeException("Bucket with name doesn't exist" + bucketIdentifier);
    }
  }

  @Override
  public void updateConfig(String bucketIdentifier, Config config) {
    checkBucketExist(bucketIdentifier);

  /*  if(!buckets.get(bucketIdentifier).containsKey(config.identifier())){
      throw new RuntimeException("Config with name "+bucketIdentifier+" doesn't exist");
    }*/

    buckets.get(bucketIdentifier).updateConfig(config);
    //Create a modficationMessage and put it into the queue.

  }

  @Override
  public void deleteConfig(String bucketIdentifier, String identifier) {

    checkBucketExist(bucketIdentifier);

    buckets.get(bucketIdentifier).deleteConfig(identifier);
    //Create a modficationMessage and put it into the queue.

  }

  @Override
  public Config getConfig(String bucketIdentifier, String configIdentifier) {
    checkBucketExist(bucketIdentifier);

    return buckets.get(bucketIdentifier).getConfig(configIdentifier);
  }

  @Override
  public void subScribe(String bucketIdentifier,Client client) {
    checkBucketExist(bucketIdentifier);

    buckets.get(bucketIdentifier).addSubscriber(client);

  }

  @Override
  public void unsubscribe(String bucketIdentifier, Client client) {

    checkBucketExist(bucketIdentifier);
    buckets.get(bucketIdentifier).removeSubscriber(client);


  }

  @Override
  public void createBucket(String bucketIdentifier) {
    if(buckets.containsKey(bucketIdentifier)){
      throw new RuntimeException("Bucket with identifier"+bucketIdentifier+" already exists");
    }
    buckets.put(bucketIdentifier, new ConcreteBucket(bucketIdentifier));

  }

  @Override
  public Collection<Config> getAllConfigs(String bucketIdentifier) {
    checkBucketExist(bucketIdentifier);
    return buckets.get(bucketIdentifier).getAllConfigs();
  }
}

