package config;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static String CONFIG_LOCATION=Config.class.getClassLoader()
            .getResource("config.properties").getFile();
    public static Properties getProperties() {
        Properties properties=new Properties();
        try {
            properties.load(new FileReader(CONFIG_LOCATION));
            return properties;
        } catch (IOException e) {
            //e.printStackTrace();
            //throw new RuntimeException("Cannot load config properties");
            properties.setProperty("file.useri", "./data/useri.ser");
            properties.setProperty("file.prietenii", "./data/prietenii.ser");
        }
        return properties;
    }
}
