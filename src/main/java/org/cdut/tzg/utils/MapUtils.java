package org.cdut.tzg.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class MapUtils {
    public static Map getMap(String data){
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = null;
        try{
            map = objectMapper.readValue(data,Map.class);
        }catch(IOException e){
            e.printStackTrace();
        }
        return map;
    }

}
