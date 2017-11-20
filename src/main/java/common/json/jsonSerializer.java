package common.json;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

//общий интерфейс Serializer
public interface jsonSerializer<T> {
    public String serialize(T object) throws JsonProcessingException;
    public T deserialize(String object) throws IOException;
}
