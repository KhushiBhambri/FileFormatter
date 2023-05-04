
package com.kaybee.FileFormatter.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import org.springframework.stereotype.Service;

@Service
public class JsonFormatterService {
    public JsonFormatterService() {
    }

    public static String formatJsonString(String jsonString) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        Object json = objectMapper.readValue(jsonString, Object.class);
        return objectWriter.writeValueAsString(json);
    }

    public static void formatJsonFile(String pathname, String outputPath) throws Exception {
        File inputFile = new File(pathname);
        ObjectMapper objectMapper = new ObjectMapper();
        Object json = objectMapper.readValue(inputFile, Object.class);
        File outputFile = new File(outputPath);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, json);
    }
}
