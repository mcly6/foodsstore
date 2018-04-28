package com.shuxin.foodsstore.commons.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;
/**
* @ClassName: Date2LongSerializer
* @Description: TODO(序列化日期数据.)
* @author liu yang  
* @date 2017/12/22 10:26
*/ 
public class Date2LongSerializer extends JsonSerializer<Date> {


    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNumber(date.getTime() / 1000);
    }
}
