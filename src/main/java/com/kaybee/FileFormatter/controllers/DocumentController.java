package com.kaybee.FileFormatter.controllers;

import com.kaybee.FileFormatter.constants.FormatterConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class DocumentController {

    private static final Logger log = LogManager.getLogger(DocumentController.class);
    @PostMapping("/download")
    public ResponseEntity<String> downloadFile(String path)
            throws IOException {

        return ResponseEntity.ok().body("File downloaded successfully: " + path);
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file)
            throws IOException {

        // Check if the file is empty
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        String contentType = file.getContentType();
        // Check the content type to determine the format of the uploaded file
        assert contentType != null;
        String inputFilePath = System.getProperty("user.dir");
        String fileName = file.getOriginalFilename();
        if (contentType.equals("application/json")) {
            File inputJson = new File(inputFilePath+"/"+FormatterConstants.INPUT_JSON_FILE);
            file.transferTo(inputJson);
        }
        else if (contentType.equals("application/xml")) {
            File inputXml = new File(inputFilePath+"/"+FormatterConstants.INPUT_XML_FILE);
            file.transferTo(inputXml);
        }
        else {
            return ResponseEntity.badRequest().body("Uploaded File is Neither XML nor JSON");
        }
        return ResponseEntity.ok().body("File uploaded successfully: " + fileName);
    }
}


