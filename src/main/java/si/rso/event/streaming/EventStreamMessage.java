package si.rso.event.streaming;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventStreamMessage {
    
    /**
     * Type of message. Use this field to determine class to decode to.
     */
    private String type;
    
    /**
     * Stringified data to be sent to event stream.
     */
    private String data;
    
    public EventStreamMessage() {
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
}
