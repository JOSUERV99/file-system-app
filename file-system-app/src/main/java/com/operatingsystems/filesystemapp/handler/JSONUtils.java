package com.operatingsystems.filesystemapp.handler;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JSONUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String mapObjectToJsonString(Object obj)
    {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> castJsonStringToHashMap(String jsonString) {

        Map<String, Object> map = new HashMap<String, Object>();

        try
        {
            map = mapper.readValue(jsonString, new TypeReference<HashMap<String, Object>>(){});
        }
        catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
