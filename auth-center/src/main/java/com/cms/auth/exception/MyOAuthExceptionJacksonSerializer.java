package com.cms.auth.exception;

import com.cms.common.common.ResponseCode;
import com.cms.common.common.ServerResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Map;

public class MyOAuthExceptionJacksonSerializer extends StdSerializer<MyOAuth2Exception> {

    public MyOAuthExceptionJacksonSerializer() {
        super(MyOAuth2Exception.class);
    }

    @Override
    public void serialize(MyOAuth2Exception value, JsonGenerator jgen, SerializerProvider serializerProvider) throws
                                                                                                                IOException {
        jgen.writeStartObject();
        jgen.writeObjectField("code", ServerResponse.createFailureResponse(ResponseCode.LOGIN_FAILURE.getCode(), ResponseCode.LOGIN_FAILURE.getInfo()));
        jgen.writeStringField("msg", value.getMessage());
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jgen.writeStringField(key, add);
            }
        }
        jgen.writeEndObject();
    }
}