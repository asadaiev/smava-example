package example.common;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Allow easy access to the config.properties
 */
public class Configuration {
    private static final Logger LOG = Logger.getLogger(Configuration.class.getName());

    private static Map<String, Configuration> configs = new HashMap<String, Configuration>();
    
    private Properties props;

    /**
     * Create instance and load properties on instantiation.
     *
     * @param propFileName is name of the file (without dot and extension) which contains description of
     * environment -- hosts, ports, etc..
     * @throws RuntimeException wrapping the underlying IOException in case config cannot be
     *                          accessed.
     */
    private Configuration(String propFileName) {
        props = new Properties();

        try (InputStream readProperty = Configuration.class.getClassLoader()
                .getResourceAsStream(propFileName + ".properties")) {
            props.load(readProperty);


        } catch (Exception ex) {
            LOG.error("Failed to initialize configuration for " + propFileName, ex);
            throw new RuntimeException(ex);
        }
    }


    public static Configuration getInstance(String propFileName) {
        Configuration config = configs.get(propFileName);
        if (null == config) {
            config = new Configuration(propFileName);
            configs.put(propFileName, config);
        }
        
        return config;
    }

    /**
     * Gets a property from the configuration. In order to allow overriding
     * properties, first checks system properties passed on.
     *
     * @param key - the key to access the property
     * @return the value of the property
     */
    public String getProperty(String key) {
        return System.getProperty(key, props.getProperty(key));
    }


    public Properties getProperties() {
        return new Properties(props);
    }

    /**
     * Gets a property from the configuration and converts it to an integer In
     * order to allow overriding properties, first checks system properties
     * passed on.
     *
     * @param key - the key to access the property
     * @return the value of the property converted to an Integer
     */
    public Integer getIntegerProperty(String key) {
        return Integer.valueOf(getProperty(key));
    }

    @Override
    /** {@inheritDoc} */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String key : props.stringPropertyNames()) {
            sb.append(key).append("=").append(getProperty(key));

            if (!props.getProperty(key).equals(getProperty(key))) {
                sb.append(" ( ").append(props.get(key)).append(" )");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void setProperty(String key, String value) {
        props.setProperty(key, value);
    }
}