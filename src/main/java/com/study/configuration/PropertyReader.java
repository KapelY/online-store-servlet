package com.study.configuration;

import com.study.Starter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class PropertyReader {
    private final String path;
    private Properties properties = new Properties();

    public PropertyReader(String path) {
        this.path = path;
    }

    public Properties getProperties() {
        Properties lineProperties = readCommandLineArguments();
        Map<String, String> envProperties = getEnvProperties();
        if (lineProperties != null && allArgumentsPresent(lineProperties)) {
            properties = lineProperties;
        } else if (allArgumentsPresent(envProperties)) {
            envProperties.forEach((k, v) -> properties.setProperty(k, v));
        } else {
            readClassPathProperties();
        }
        return properties;
    }

    private void readClassPathProperties() {
        try (InputStream input = Starter.class.getClassLoader().getResourceAsStream(path)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties readCommandLineArguments() {
        return System.getProperties();
    }

    private Map<String, String> getEnvProperties() {
        return System.getenv();
    }

    private boolean allArgumentsPresent(Map<String, String> args) {
        return args.get("driver") != null && args.get("user") != null
                && args.get("password") != null && args.get("url") != null;
    }

    private boolean allArgumentsPresent(Properties args) {
        return args.getProperty("driver") != null && args.getProperty("user") != null
                && args.getProperty("password") != null && args.getProperty("url") != null;
    }
}
