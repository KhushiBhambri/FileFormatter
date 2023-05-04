package com.kaybee.FileFormatter.controllers;

import com.kaybee.FileFormatter.services.JsonFormatterService;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonFormatterController {
    private static final Logger log = LogManager.getLogger(JsonFormatterController.class);

    public JsonFormatterController() {
    }

    @PostMapping({"/jsonFile"})
    public ResponseEntity<?> FormatJsonFile(HttpServletResponse HttpResponse) throws Exception {
        String inputFileName = "src/main/resources/input.json";
        String OutputFileName = "src/main/resources/output.json";
        JsonFormatterService.formatJsonFile(inputFileName, OutputFileName);
        return null;
    }

    @PostMapping({"/jsonString"})
    public String FormatJsonString(@RequestBody String JsonString) throws Exception {
        String formattedJson = JsonFormatterService.formatJsonString(JsonString);
        log.info(formattedJson);
        return formattedJson;
    }
}
