package com.yelman.cloudserver.utils.mail;

import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class EmailTemplateUtil {

    private static final String TEMPLATE_PATH = "src/main/resources/static/email-template.html";

    public static String getFormattedEmail(Map<String, String> values) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(new ClassPathResource(TEMPLATE_PATH).getURI())));

        for (Map.Entry<String, String> entry : values.entrySet()) {
            content = content.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return content;
    }
}
