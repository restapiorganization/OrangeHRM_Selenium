package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigUtil 
{

    private static Properties prop = new Properties();

    public static void loadConfig() {
    {
        try 
        {
            FileInputStream fis = new FileInputStream("resources\\config.properties");
            prop.load(fis);
        } 
        catch (Exception e)
        {
            throw new RuntimeException("Unable to load config.properties: " + e.getMessage());
        }
    }
    }

    public static String getProperty(String key)
    {
        return prop.getProperty(key);
        
    }
}
