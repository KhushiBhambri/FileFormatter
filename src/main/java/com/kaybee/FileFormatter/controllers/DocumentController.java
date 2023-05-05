package com.kaybee.FileFormatter.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class DocumentController {
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        // Check if the file is empty
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        String contentType = file.getContentType();

        // Check the content type to determine the format of the uploaded file
        assert contentType != null;
        String fileName = file.getOriginalFilename();
        if (contentType.equals("application/json")) {
            File outputFile = new File("src/main/resources/input1.json");
            file.transferTo(outputFile);
        }
        else if (contentType.equals("application/xml")) {
            File outputFile = new File("src/main/resources/input1.xml");
            file.transferTo(outputFile);
        }
        else {
            return ResponseEntity.badRequest().body("Uploaded File is Neither XML nor JSON");
        }
        return ResponseEntity.ok().body("File uploaded successfully: " + fileName);
    }
}
