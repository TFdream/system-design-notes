package io.dreamstudio.common.util;

import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author Ricky Fung
 */
public class JsonUtils {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Date.class, new JsonDateSerializer())
            .create();

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T parseObject(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }
    public static <T> T parseObject(String json, Type typeOfT) {
        return GSON.fromJson(json, typeOfT);
    }
}

/**
 * gson Date序列化/反序列化
 * @author Ricky Fung
 */
class JsonDateSerializer implements
        JsonSerializer<Date>,JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json==null || json.isJsonNull()) {
            return null;
        }
        String dateStr = json.getAsString();
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        return DateUtils.parseDate(dateStr);
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        if (src==null) {
            return null;
        }
        return new JsonPrimitive(DateUtils.format(src));
    }
}
