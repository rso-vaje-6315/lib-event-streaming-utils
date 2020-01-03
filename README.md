# Event Streaming Utils library 1.0.0

![status](https://api.travis-ci.org/rso-vaje-6315/lib-event-streaming-utils.svg)

## Usage

### Convention

All **event IDs** and **channels** need to be put into Java file into **lib** module, so it can be imported into other projects! 

### Configuration

Serializers and deserializers need to be specified in `config.yaml`:

```yaml
streaming:
  kafka:
    consumer:
      bootstrap-servers: localhost:9092
      group-id: group1
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: si.rso.event.streaming.serialization.EventStreamMessageDeserializer
    producer:
      bootstrap-servers: localhost:9092
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: si.rso.event.streaming.serialization.EventStreamMessageSerializer
```

### Producing message

All messages sent to kafka need to be of type `EventStreamMessage`. You can create message like this:

```java
import si.rso.event.streaming.EventStreamMessage;
import si.rso.event.streaming.EventStreamMessageBuilder;
import si.rso.event.streaming.JacksonMapper;

@Inject
@StreamProducer
private Producer<String, EventStreamMessage> producer;

public void sendToKafka() {
    Order order = service.getOrderToBeSent();
    String dataToBeSent = JacksonMapper.stringify(order);
    
    EventStreamMessage message = EventStreamMessageBuilder
        .getInstance()
        .ofType(ServiceStreamConfig.EVENT_ID)
        .withData(dataToBeSent)
        .build();

    ProducerRecord<String, EventStreamMessage> record = new ProducerRecord<>(ServiceStreamConfig.CHANNEL_ID, "key", message);
            
    producer.send(record, (meta, exc) -> {
        if (exc != null) {
            exc.printStackTrace();
        }
    });
}
```

### Consuming message

```java
@StreamListener(topics = {ServiceStreamConfig.CHANNEL_ID})
public void onMessage(ConsumerRecord<String, EventStreamMessage> record) {
    EventStreamMessage message = record.value();
    LOG.info("Consumed Kafka record of type " + message.getType());
    Order receivedOrder = JacksonMapper.toEntity(message.getData(), Order.class);
}
```

## Development

### Deployment

To deploy snapshot version, simply execute:

```bash
mvn clean deploy -P rso
```
