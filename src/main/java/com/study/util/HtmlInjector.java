package com.study.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;

import java.io.StringWriter;
import java.util.Map;

public class HtmlInjector {

    @SneakyThrows
    public static String buildPage(String path, Map<String, Object> data) {
        StringWriter stream = new StringWriter();
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_21);
        configuration.setClassForTemplateLoading(HtmlInjector.class, "/templates/");
        Template template = configuration.getTemplate(path);
        template.process(data, stream);

        return stream.toString();
    }
}
