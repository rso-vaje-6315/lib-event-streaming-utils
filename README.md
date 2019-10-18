# Event Streaming Utils library 1.0.0

## Deploy

```bash
mvn clean javadoc:jar source:jar deploy -P rso
```

## Usage

### Creating event streaming message

```java
// 1. Create message
EventStreamMessage message = EventStreamMessageBuilder
    .getInstance()
    .ofType("ORDER_FINISHED")
    .withData(JacksonMapper.stringify(orderDetailsObject))
    .build();

// 2. Encode message
Optional<String> messageToBeSent = EventStreamMessageParser.encodeMessage(message);

// ... Send message

// ... Receive message

// 3. Decode message
Optional<EventStreamMessage> message = EventStreamMessageParser.decodeMessage(rawMessage);
if (message.isPresent()) {
    if (message.get().getType().equals("ORDER_FINISHED")) {
        OrderDetails order = JacksonMapper.toEntity(message.get().getData(), OrderDetails.class);
    }
}
```