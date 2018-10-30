package gov.uscis.odds.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {
	static private Properties testProperties = null;

	public static String getProperty(String key) {
		if (testProperties == null) {
			LoadProperties.loadProperties();
		}
		return testProperties.getProperty(key);
	}

	/**
	 * Read configuration values from the Application.properties and driver.properties file
	 */
	public static void loadProperties() {
		try {
			InputStream inputStream = LoadProperties.class.getClassLoader()
					.getResourceAsStream("config/Application.properties");

			InputStream driverProp = LoadProperties.class.getClassLoader()
					.getResourceAsStream("driver.properties");
			testProperties = new Properties();
			testProperties.load(driverProp);
			testProperties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getApplicationURL() {
		return (System.getProperty("web.url") == null ? 
				LoadProperties.getProperty("web.url") : System.getProperty("web.url"));
	}
	
	public static String getApplicationURL(String URLname) {
		return (System.getProperty(URLname) == null ? 
				LoadProperties.getProperty(URLname) : System.getProperty(URLname));
	}
}
