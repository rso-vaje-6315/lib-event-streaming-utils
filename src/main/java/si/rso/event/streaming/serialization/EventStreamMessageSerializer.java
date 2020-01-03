package si.rso.event.streaming.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import si.rso.event.streaming.EventStreamMessage;

import java.util.Map;

public class EventStreamMessageSerializer implements Serializer<EventStreamMessage> {
    
    private ObjectMapper mapper;
    
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        mapper = new ObjectMapper();
    }
    
    @Override
    public byte[] serialize(String topic, EventStreamMessage data) {
        try {
            return mapper.writeValueAsString(data).getBytes();
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    
    @Override
    public void close() {
    
    }
}
