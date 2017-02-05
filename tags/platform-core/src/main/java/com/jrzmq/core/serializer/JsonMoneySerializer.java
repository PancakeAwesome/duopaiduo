
package com.jrzmq.core.serializer;

import java.io.IOException;
import java.math.BigDecimal;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * jackson 金额格式化
 * @author wusimai
 *
 */
public class JsonMoneySerializer extends JsonSerializer<BigDecimal>{

    @Override
    public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
            JsonProcessingException {
        // put your desired money style here
        jgen.writeString(value.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    }
    
}
