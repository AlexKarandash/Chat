package common.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

//базовый класс для имплементации Serializer
public abstract class BaseJsonSerializer<T> implements jsonSerializer<T> {

    private Class<T> classType;

    public BaseJsonSerializer(Class<T> classType) {
        this.classType = classType;
    }

    @Override
    public String serialize(T object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Override
    public T deserialize(String object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(object, classType);
    }
}
