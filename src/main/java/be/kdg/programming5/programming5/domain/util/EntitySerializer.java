package be.kdg.programming5.programming5.domain.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Objects;

public class EntitySerializer<T> implements JsonSerializer<T> {
    private final String attributeName;

    public EntitySerializer(String attributeName) {
        this.attributeName = attributeName;
    }

    @Override
    public JsonElement serialize(T entity, Type type, JsonSerializationContext context) {
        if (entity == null) {
            return JsonNull.INSTANCE;
        }

        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(attributeName, getEntityId(entity));
            return jsonObject;
        } catch (Exception e) {
            return JsonNull.INSTANCE;
        }
    }

    private Long getEntityId(T entity) {
        try {
            return (Long) Objects.requireNonNull(entity.getClass().getMethod("getId").invoke(entity));
        } catch (Exception e) {
            throw new RuntimeException("Error getting ID for entity", e);
        }
    }
}
