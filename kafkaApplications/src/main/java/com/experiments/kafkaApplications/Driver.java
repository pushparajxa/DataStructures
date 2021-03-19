package com.experiments.kafkaApplications;


public class Driver {
    
    private static String bootStrapServers, schemaRegistryUrl, topic;
    public static void main(String[] args)
    {
        bootStrapServers = args[0];
        schemaRegistryUrl = args[1];
        topic = args[2];
        
        if(args[3].equalsIgnoreCase("producer")) {
            AvroProducer avroProducer = new AvroProducer();
            avroProducer.exec();
        }else{
            AvroConsumer avroConsumer = new AvroConsumer();
            avroConsumer.exec(args[4]);
        }
    }
    
    /**
     * Get the bootstrap servers for a given kafka mode.
     * @return
     */
    public static String getBootStrapServers()
    {
      return bootStrapServers;
    }
    
    public static String getSchemaRegistryUrl()
    {
        
        return schemaRegistryUrl;
    }
    
    public static String getTopic() {
        return topic;
    }
}
