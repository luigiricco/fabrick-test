package com.fabrick.rs.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class FabrickTransactionTypeEnumDeserializer extends StdDeserializer<FabrickTransactionTypeEnum> {

    public FabrickTransactionTypeEnumDeserializer() {
        super(FabrickTransactionTypeEnum.class);
    }

    @Override
    public FabrickTransactionTypeEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String enumeration = node.get("enumeration").asText();
        String value = node.get("value").asText();

        for (FabrickTransactionTypeEnum type : FabrickTransactionTypeEnum.values()) {

            if (type.getEnumeration().equals(enumeration) && type.getValue().equals(value)) {
                return type;
            }
        }

        return FabrickTransactionTypeEnum.NOT_SUPPORTED;
    }
}
