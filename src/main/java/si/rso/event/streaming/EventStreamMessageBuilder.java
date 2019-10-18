package si.rso.event.streaming;

public class EventStreamMessageBuilder {
    
    private EventStreamMessage message;
    
    public static EventStreamMessageBuilder getInstance() {
        EventStreamMessageBuilder builder = new EventStreamMessageBuilder();
        builder.message = new EventStreamMessage();
        return builder;
    }
    
    public EventStreamMessageBuilder ofType(String type) {
        this.message.setType(type);
        return this;
    }
    
    public EventStreamMessageBuilder withData(String data) {
        this.message.setData(data);
        return this;
    }
    
    public EventStreamMessage build() {
        return this.message;
    }
    
}
