package com.example.demo.controller.advice;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class LanguageControllerAdvice {
	
	private static final Logger LOG = LoggerFactory.getLogger(LanguageControllerAdvice.class);
	private static final String LANGUAGE_FILE = "languages.properties";
	private static final String KO_KEY = "ko";
	
	@ModelAttribute("languageMap")
	public Map getLanguageMap() {
		
		
		Properties propertiesLanguage = new Properties();
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(LANGUAGE_FILE)){
			if (inputStream !=null) {
				propertiesLanguage.load(new InputStreamReader(inputStream, "UTF-8"));
			} else {
				LOG.error("File %s does not exist", LANGUAGE_FILE);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String, String> oldLanguage = (Map) propertiesLanguage;
		Map<String, String > newLanguageMap = new LinkedHashMap<>();
		if (oldLanguage.containsKey(KO_KEY));
		String valueKo= oldLanguage.remove(KO_KEY);
		newLanguageMap.put(KO_KEY, valueKo);
		newLanguageMap.putAll(oldLanguage);
		
		
		System.out.println("LanguageControllerAdvice");
		return newLanguageMap;
	}

}
