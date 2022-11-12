package config;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static InputStream CONFIG_LOCATION=Config.class.getClassLoader()
            .getResourceAsStream("config.properties");
    public static Properties getProperties() {
        Properties properties=new Properties();
        try {
            properties.load(CONFIG_LOCATION);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot load config properties");
        }
    }
}
