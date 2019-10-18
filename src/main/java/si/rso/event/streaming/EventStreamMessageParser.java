package si.rso.event.streaming;

import java.util.Optional;

public class EventStreamMessageParser {
    
    /**
     * Decodes JSON string to EventStreamMessage
     *
     * @param rawMessage JSON string to be processed
     * @return instance of object EventStreamMessage, or null if message is not in expected format
     */
    public static Optional<EventStreamMessage> decodeMessage(String rawMessage) {
        return Optional.ofNullable(JacksonMapper.toEntity(rawMessage, EventStreamMessage.class));
    }
    
    /**
     * Encodes instance of EventStreamMessage to JSON string
     *
     * @param message Entity to be processed
     * @return JSON string, or null if there was error during stringifying
     */
    public static Optional<String> encodeMessage(EventStreamMessage message) {
        return Optional.ofNullable(JacksonMapper.stringify(message));
    }
    
}
