package com.acutest.EmailTestPerfomance;

import java.util.*;
import java.io.*;

public class ReadPropertiesFile {

	private static final String PROP_FILE_NAME = "config.properties";

	private String password;
	private String username;

	public ReadPropertiesFile() {
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROP_FILE_NAME);

			Properties props = new Properties();

			// load properties file
			if (inputStream != null) {
				props.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + PROP_FILE_NAME + "' not found!");
			}

			this.password = props.getProperty("password");
			this.username = props.getProperty("username");

		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}
	
	public String getPassword() {
		return this.password;
	}
	public String getUserName() {
		return this.username;
	}
}
