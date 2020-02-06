package org.dlese.dpc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertyReader {
  public static final String HANDLE_SERVICE_URL = "handle.service.url";
  private static final String DEFAULT_PROPERTY_FILE = "oai.properties";

  private static Properties properties;
  private static URL solution;

  private PropertyReader() {
    loadProperties();
  }

  private static PropertyReader getInstance() {
    return PropertyReaderHolder.instance;
  }

  public static String getProperty(String key) {
    return PropertyReader.getInstance().doGetProperty(key);
  }

  public static String getProperty(String key, String defaultValue) {
    return PropertyReader.getInstance().doGetProperty(key) != null ? PropertyReader.getInstance().doGetProperty(key) : defaultValue;
  }

  public static Properties getProperties() {
    PropertyReader.getInstance();

    return PropertyReader.properties;
  }

  /**
   * Force the property file to be reloaded into the Properties object
   */
  public static void forceReloadProperties() {
    new PropertyReader();
  }

  /**
   * Gets the value of a property for the given key from the system properties or the PubMan
   * property file. It is always tried to get the requested property value from the system
   * properties. This option gives the opportunity to set a specific property temporary using the
   * system properties. If the requested property could not be obtained from the system properties
   * the PubMan property file is accessed. (For details on access to the properties file see class
   * description.)
   * 
   * @param key The key of the property.
   * @param callingClass Class of the calling class
   * @return The value of the property.
   */
  private String doGetProperty(String key) {
    // First check system properties
    String value = System.getProperty(key);
    if (value != null) {
      return value;
    }

    // Get the property
    value = properties.getProperty(key);

    return value;
  }

  /**
   * Load the properties from the location defined by the system property
   * <code>pubman.properties.file</code>. If this property is not set the default file path
   * <code>pubman.properties</code> is used. If no properties can be loaded, the jvm is terminated.
   */
  private static void loadProperties() {
    String propertiesFile = "";
    Properties solProperties = new Properties();

    solution = PropertyReader.class.getClassLoader().getResource("solution.properties");

    if (solution != null) {
      System.out.println("solution.properties found");
      try {
        InputStream in = getInputStream("solution.properties");
        solProperties.load(in);
        in.close();

        String appname = solProperties.getProperty("appname");
        propertiesFile = appname + ".properties";
      } catch (IOException e) {
        System.out.println("Exception" + e);
      }

    } else {
      System.out.println("solution.properties not found");
      propertiesFile = DEFAULT_PROPERTY_FILE;
    }

    properties = new Properties();
    try {
      InputStream instream = getInputStream(propertiesFile);
      properties.load(instream);
      properties.putAll(solProperties);
      instream.close();
    } catch (IOException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  /**
   * Retrieves the Inputstream of the given file path. First the resource is searched in the file
   * system, if this fails it is searched using the classpath.
   * 
   * @param filepath The path of the file to open.
   * @return The inputstream of the given file path.
   * @throws IOException If the file could not be found neither in the file system nor in the
   *         classpath.
   */
  private static InputStream getInputStream(String filepath) throws IOException {
    return getInputStream(filepath, PropertyReader.class);
  }

  /**
   * Retrieves the Inputstream of the given file path. First the resource is searched in the file
   * system, if this fails it is searched using the classpath.
   * 
   * @param filepath The path of the file to open.
   * @param callingClass Class of the calling class
   * @return The inputstream of the given file path.
   * @throws IOException If the file could not be found neither in the file system nor in the
   *         classpath.
   */
  private static InputStream getInputStream(String filepath, Class<PropertyReader> callingClass) throws IOException {
    InputStream instream = null;
    // First try to search in file system
    try {
      System.out.println("load " + filepath);
      instream = new FileInputStream(filepath);
      new File(filepath).getAbsolutePath();
    } catch (Exception e) {
      // try to get resource from classpath
      System.out.println("load " + filepath);
      URL url = callingClass.getClassLoader().getResource(filepath);
      if (url != null) {
        instream = url.openStream();
        url.getFile();
      }
    }
    if (instream == null) {
      System.out.println("not loaded " + filepath);
      throw new FileNotFoundException(filepath);
    }
    return instream;
  }

  private static class PropertyReaderHolder {
    private static final PropertyReader instance = new PropertyReader();
  }
}
