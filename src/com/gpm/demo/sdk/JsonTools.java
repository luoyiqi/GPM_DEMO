package com.gpm.demo.sdk;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonTools {
    private static ObjectMapper mObjectMapper = new ObjectMapper();

    /**
     * 将json装换成对应的对象
     * 
     * @param json
     * @param objClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T jsonToObject(String json, Class<T> objClass) {
        Object obj = null;
        try {
            obj = mObjectMapper.readValue(json, objClass);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (T) obj;
    }

    /**
     * 将对象装换成json
     * 
     * @param object
     * @return
     */
    public static String objectToJson(Object object) {
        String result = "";
        try {
            result = mObjectMapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
