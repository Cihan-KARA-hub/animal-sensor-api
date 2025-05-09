package com.yelman.cloudserver.utils.mail;

import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.util.Map;

public class EmailTemplateUtil {

    public static String getFormattedEmail(Map<String, String> values) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/email-template.html");
        String content = new String(resource.getInputStream().readAllBytes());

        for (Map.Entry<String, String> entry : values.entrySet()) {
            content = content.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return content;
    }

}
