package com.eric.dairy.utils;

import com.eric.dairy.model.Dairy;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;
import java.text.ParseException;

// Works for deserialize json with date string

public class DairyDeserialization extends StdDeserializer<Dairy> {


        public DairyDeserialization() {
            this(null);
        }

        public DairyDeserialization(Class<?> vc) {
            super(vc);
        }

        @Override
        public Dairy deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            JsonNode node = jp.getCodec().readTree(jp);
            String dateString = node.get("date").asText();
            System.out.println(dateString);
            String description = node.get("description").asText();
            int calories = (Integer) ((IntNode) node.get("calories")).numberValue();
            Dairy dairy = null;
            try {
               dairy = new Dairy(dateString, description, calories);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return dairy;

        }

}
