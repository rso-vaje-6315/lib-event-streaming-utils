package si.rso.event.streaming;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JacksonMapper {
    
    /**
     * Transforms given Jackson entity to JSON string
     *
     * @param entity entity to be transformed
     * @return JSON string or null if entity is not processable
     */
    public static String stringify(Object entity) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Transformes given JSON string to Jackson entity
     *
     * @param stringifiedEntity JSON string to be transformed
     * @param entityType        Class to be transformed into
     * @param <T>               Class to be transformed into
     * @return Transformed entity or null if JSON string is not processable
     */
    public static <T> T toEntity(String stringifiedEntity, Class<T> entityType) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(stringifiedEntity, entityType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
