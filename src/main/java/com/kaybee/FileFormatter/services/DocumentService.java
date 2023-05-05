package com.kaybee.FileFormatter.services;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.io.File;
import java.io.FileNotFoundException;

public class DocumentService {

    public static ResponseEntity<Resource> downloadFile(String path)
    {
        try{
            File file = new File(path);

            if(!file.exists()) {
                throw new FileNotFoundException("File not found");
            }

            Resource resource = new FileSystemResource(file);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }


    }




}
