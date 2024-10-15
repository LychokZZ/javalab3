package com.example;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Animal lion = new Animal("Lion", "Panthera leo", 5);
        Animal elephant = new Animal("Elephant", "Loxodonta", 10);

        Map<String, EntitySerializer<Animal>> serializers = new HashMap<>();
        serializers.put("json", new JsonEntitySerializer<>(Animal.class));
        serializers.put("xml", new XmlEntitySerializer<>(Animal.class));
        serializers.put("yaml", new YamlEntitySerializer<>(Animal.class));

        String format = "json";
        EntitySerializer<Animal> serializer = serializers.get(format);

        try {
            String serializedData = serializer.serialize(lion);
            System.out.println("Serialized data: " + serializedData);

            Animal deserializedLion = serializer.deserialize(serializedData);
            System.out.println("Deserialized animal: " + deserializedLion);
        } catch (Exception e) {
            System.err.println("Serialization error: " + e.getMessage());
        }
    }
}
