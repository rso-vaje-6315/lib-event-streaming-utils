package si.rso.event.streaming.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import si.rso.event.streaming.EventStreamMessage;

import java.io.IOException;
import java.util.Map;

public class EventStreamMessageDeserializer implements Deserializer<EventStreamMessage> {
    
    private ObjectMapper mapper;
    
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        mapper = new ObjectMapper();
    }
    
    @Override
    public EventStreamMessage deserialize(String topic, byte[] data) {
        try {
            return mapper.readValue(data, EventStreamMessage.class);
        } catch (IOException e) {
            return null;
        }
    }
    
    @Override
    public void close() {
    
    }
}
