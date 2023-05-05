
package com.kaybee.FileFormatter.controllers;

import com.kaybee.FileFormatter.constants.FormatterConstants;
import com.kaybee.FileFormatter.services.DocumentService;
import com.kaybee.FileFormatter.services.XmlFormatterService;
import java.io.File;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XmlFormatterController {
    private static final Logger log = LogManager.getLogger(XmlFormatterController.class);

    public XmlFormatterController() {
    }

    @PostMapping({"/xmlFile"})
    public ResponseEntity<?> FormatJsonFile(HttpServletResponse HttpResponse) throws Exception {
        String inputFilePath = FormatterConstants.INPUT_XML_FILE;
        String OutputFilePath = FormatterConstants.OUTPUT_XML_FILE;
        XmlFormatterService.formatXmlFile(inputFilePath, OutputFilePath);
        return DocumentService.downloadFile(OutputFilePath);
    }

    @PostMapping({"/xmlString"})
    public String FormatJsonString(@RequestBody String XmlString) throws Exception {
        return XmlFormatterService.formatXmlString(XmlString);
    }
}
