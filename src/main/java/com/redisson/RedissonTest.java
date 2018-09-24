
package com.redisson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.redisson.Redisson;
import org.redisson.api.ExecutorOptions;
import org.redisson.api.RScheduledExecutorService;
import org.redisson.api.RScheduledFuture;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

@Slf4j
public class RedissonTest {
  public static void main(String[] args) {

    RedissonClient redissonClient = Redisson.create(getConfig());
    System.out.println(redissonClient.hashCode());
    RScheduledExecutorService executorService =
        redissonClient.getExecutorService(
            "redissonTest",
            ExecutorOptions.defaults().taskRetryInterval(0, TimeUnit.MINUTES));

    int delay = 60;
    log.info("Scheduling task at Time={}, with delay ={}", DateTime.now(TimeUtils.IST), delay);
    RScheduledFuture<String> schedule =
        executorService.schedule(new MyCallable(), delay, TimeUnit.SECONDS);

    schedule.handleAsync(
        (x, y) -> {
          System.out.println(
              "From callback thread .hello. received this result "
                  + x
                  + " at time="
                  + DateTime.now(TimeUtils.IST));
          return x;
        });


   /* boolean b = executorService.cancelTask(schedule.getTaskId());
    System.out.println("Cancelled the scheduled task="+b);

    try {
      Thread.sleep(2*60*1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Thread sleep is finished");


    schedule =
        executorService.schedule(new MyCallable(), delay, TimeUnit.SECONDS);

    System.out.println("Scheduling another task");

    schedule.handleAsync(
        (x, y) -> {
          System.out.println(
              "From callback thread .world. received this result "
                  + x
                  + " at time="
                  + DateTime.now(TimeUtils.IST));
          return x;
        });*/

    /* RMap<String, RMap<String, DelayedPickupTimerDTO>> map =
        redissonClient.getMap("cpb_summary_test2");
    RMap<String, DelayedPickupTimerDTO> timerDTORMap = map.get("2096151");

    timerDTORMap
        .entrySet()
        .stream()
        .forEach(
            x -> {
              System.out.println("Key=" + x.getKey() + " and Value=" + x.getValue());
            });*/
    //  timerDTORMap.put("TestEntry",timerDTORMap.get("DELIVERY_COOLING_DRS_NOT_CLOSED"));
    // System.out.println("After Inserting");
    // timerDTORMap.entrySet().stream().forEach(x -> System.out.println("Key="+x.getKey()+" and "
    //   + "Value="+x.getValue()));

    /* RMap<String, RMap<String, DeliveryCoolingTimerDTO>> map2 =
        redissonClient.getMap("cpb_summary_test2");
    System.out.println("Received map="+map2.hashCode());
    RMap<String, DeliveryCoolingTimerDTO> map1 = redissonClient.getMap("2096151");
    map1.put("TestEntry4",timerDTORMap.get("DELIVERY_COOLING_DRS_NOT_CLOSED"));
    map2.put("2096151", map1);
    System.out.println("Populated the map");*/
  }

  private static Config getConfig() {
    String[] nodeAddress =
        new String[] {"zoom-cpp-realtime.ongcpp.clustercfg.apse1.cache.amazonaws.com"};
    Config config = new Config();
    config.setCodec(new JsonJacksonCodec(objectMapper()));

    if (nodeAddress.length == 0)
      throw new IllegalStateException("Node address is not defined. Please define redis nodes");

    // log.info("nodeAddress {}", Arrays.asList(nodeAddress));

   /* config
        .useClusterServers()
        .addNodeAddress(
            ("redis://zoom-cpp-realtime.ongcpp.clustercfg.apse1.cache.amazonaws" + ".com:6379"))
        .setTimeout(60 * 60 * 1000)
        .setRetryAttempts(3)
        .setRetryInterval(3000)
        .setConnectTimeout(60 * 1000)
        .setSubscriptionConnectionMinimumIdleSize(2)
        .setSubscriptionConnectionPoolSize(5)
        .setMasterConnectionMinimumIdleSize(2)
        .setMasterConnectionPoolSize(10);*/

     config
    .useSingleServer()
    .setAddress("redis://localhost:6379")
    .setTimeout(3 * 1000)
    .setRetryAttempts(3)
    .setRetryInterval(3000)
    .setConnectTimeout(60 * 1000)
    .setSubscriptionConnectionMinimumIdleSize(2)
    .setSubscriptionConnectionPoolSize(5);

    return config;
  }

  public static ObjectMapper objectMapper() {
    return new ObjectMapper()
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(
            new JavaTimeModule()
                .addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer())
                .addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeSerializer()))
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));
  }

  private static class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeNumber(value.toInstant(ZoneOffset.UTC).toEpochMilli());
    }
  }

  private static class CustomLocalDateTimeDeSerializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      return LocalDateTime.ofInstant(Instant.ofEpochMilli(p.getValueAsLong()), ZoneOffset.UTC);
    }
  }
}
