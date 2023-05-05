
package com.kaybee.FileFormatter.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

@Service
public class XmlFormatterService {
    public XmlFormatterService() {
    }

    public static void formatXmlFile(String inputPath, String outputPath) throws Exception {
        File input = new File(inputPath);
        File output = new File(outputPath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(input.getAbsolutePath()));
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("indent", "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(document);
        FileOutputStream fileOutputStream = new FileOutputStream(output);
        StreamResult result = new StreamResult(fileOutputStream);
        transformer.transform(source, result);
        fileOutputStream.close();
    }

    public static String formatXmlString(String input) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number", 2);
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("indent", "yes");
        StreamSource inputSource = new StreamSource(new StringReader(input));
        StringWriter output = new StringWriter();
        StreamResult outputResult = new StreamResult(output);
        transformer.transform(inputSource, outputResult);
        return output.toString();
    }
}
